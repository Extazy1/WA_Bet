package com.controller;

import com.annotation.RequiresAuthority;
import com.model.dto.RankingDTO;
import com.model.dto.VoteDTO;
import com.model.dto.VotesDTO;
import com.model.entity.Athlete;
import com.model.entity.User;
import com.model.entity.Vote;
import com.model.entity.VoteEvent;
import com.service.AthleteService;
import com.service.UserService;
import com.service.VoteEventService;
import com.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "投票控制器")
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteEventService voteEventService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加或更新投票记录", notes = "新增或更新投票记录信息")
    @PostMapping("/add")
    public Map<String, Object> addVote(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
            @ApiParam(value = "运动员ID", required = true) @RequestParam Integer athleteId,
            @ApiParam(value = "投票活动ID", required = true) @RequestParam Integer voteEventId,
            @ApiParam(value = "投票数量", required = true) @RequestParam Integer votes) {
        voteService.addOrUpdateVote(userId, athleteId, voteEventId, votes);

        Vote vote = voteService.getOne(new QueryWrapper<Vote>()
                .eq("user_id", userId)
                .eq("athlete_id", athleteId)
                .eq("vote_event_id", voteEventId));

        VoteDTO voteDTO = new VoteDTO(vote.getId(), vote.getUserId(), vote.getAthleteId(), vote.getVoteEventId(), vote.getVotes());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "添加或更新成功");
        result.put("data", voteDTO);
        return result;
    }

    @ApiOperation(value = "获取所有投票记录", notes = "获取所有投票记录信息 需要权限")
    @RequiresAuthority(value = 1) // 需要管理员权限
    @GetMapping("/all")
    public Map<String, Object> getAllVotes() {
        // 获取所有投票记录
        List<Vote> votes = voteService.list();

        // 获取所有用户信息
        List<User> users = userService.getAllUsers();
        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(
                        user -> (int) user.getId(), // Cast long to int
                        user -> user
                ));

        // 获取所有运动员信息
        List<Athlete> athletes = athleteService.list();
        Map<Integer, Athlete> athleteMap = athletes.stream()
                .collect(Collectors.toMap(Athlete::getId, athlete -> athlete));

        // 获取所有投票活动信息
        List<VoteEvent> voteEvents = voteEventService.list();
        Map<Integer, VoteEvent> voteEventMap = voteEvents.stream()
                .collect(Collectors.toMap(VoteEvent::getId, voteEvent -> voteEvent));

        // 构造包含用户和运动员详细信息的投票记录
        List<VotesDTO> voteDTOs = votes.stream()
                .map(vote -> {
                    User user = userMap.get(vote.getUserId());
                    Athlete athlete = athleteMap.get(vote.getAthleteId());
                    VoteEvent voteEvent = voteEventMap.get(vote.getVoteEventId());
                    return new VotesDTO(vote.getId(), vote.getVotes(), user, athlete, voteEvent);
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", voteDTOs);
        return result;
    }


    @ApiOperation(value = "根据ID获取投票记录信息", notes = "通过ID获取特定投票记录信息")
    @GetMapping("/get/{id}")
    public Map<String, Object> getVoteById(
            @ApiParam(value = "投票记录ID", required = true) @PathVariable Integer id) {
        Vote vote = voteService.getById(id);
        VoteDTO voteDTO = vote != null ? new VoteDTO(vote.getId(), vote.getUserId(), vote.getAthleteId(), vote.getVoteEventId(), vote.getVotes()) : null;

        Map<String, Object> result = new HashMap<>();
        result.put("code", vote != null ? 0 : 1);
        result.put("msg", vote != null ? "查询成功" : "投票记录不存在");
        result.put("data", voteDTO != null ? voteDTO : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "更新投票记录信息", notes = "更新投票记录信息 需要权限")
    @RequiresAuthority(value = 1) // 需要管理员权限
    @PutMapping("/update/{id}")
    public Map<String, Object> updateVote(
            @ApiParam(value = "投票记录ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
            @ApiParam(value = "运动员ID", required = true) @RequestParam Integer athleteId,
            @ApiParam(value = "投票活动ID", required = true) @RequestParam Integer voteEventId,
            @ApiParam(value = "投票数量", required = true) @RequestParam Integer votes) {
        Vote vote = voteService.getById(id);

        Map<String, Object> result = new HashMap<>();
        if (vote == null) {
            result.put("code", 1);
            result.put("msg", "投票记录不存在");
            result.put("data", new HashMap<>());
            return result;
        }

        vote.setUserId(userId);
        vote.setAthleteId(athleteId);
        vote.setVoteEventId(voteEventId);
        vote.setVotes(votes);

        boolean success = voteService.updateById(vote);

        VoteDTO voteDTO = new VoteDTO(vote.getId(), vote.getUserId(), vote.getAthleteId(), vote.getVoteEventId(), vote.getVotes());

        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "更新成功" : "更新失败");
        result.put("data", success ? voteDTO : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "删除投票记录信息", notes = "通过ID删除特定投票记录信息 需要权限")
    @RequiresAuthority(value = 1) // 需要管理员权限
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteVote(
            @ApiParam(value = "投票记录ID", required = true) @PathVariable Integer id) {
        boolean success = voteService.removeById(id);

        Map<String, Object> result = new HashMap<>();
        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "删除成功" : "删除失败或投票记录不存在");
        result.put("data", new HashMap<>());
        return result;
    }

    @ApiOperation(value = "统计所有运动员的得票数并排名", notes = "统计所有运动员的得票数并进行排名")
    @GetMapping("/rankings")
    public Map<String, Object> getAthleteRankings() {
        List<Vote> votes = voteService.list();
        Map<Integer, Integer> athleteVotesMap = new HashMap<>();

        // 统计每个运动员的总得票数
        for (Vote vote : votes) {
            athleteVotesMap.merge(vote.getAthleteId(), vote.getVotes(), Integer::sum);
        }

        // 获取所有运动员信息并构建 Map
        List<Athlete> athletes = athleteService.list();
        Map<Integer, Athlete> athleteMap = athletes.stream()
                .collect(Collectors.toMap(Athlete::getId, athlete -> athlete));

        // 对运动员得票数进行降序排序
        List<Map.Entry<Integer, Integer>> sortedAthleteVotes = athleteVotesMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());

        // 构造返回结果
        List<RankingDTO> rankings = new ArrayList<>();
        for (int i = 0; i < sortedAthleteVotes.size(); i++) {
            Map.Entry<Integer, Integer> entry = sortedAthleteVotes.get(i);
            Athlete athlete = athleteMap.get(entry.getKey());
            if (athlete != null) {
                RankingDTO rankingDTO = new RankingDTO(i + 1, entry.getValue(), athlete.getId(), athlete.getName(), athlete.getSport(), athlete.getLink());
                rankings.add(rankingDTO);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", rankings);
        return result;
    }

    @ApiOperation(value = "根据投票活动ID统计运动员得票数并排名", notes = "根据特定投票活动ID统计运动员得票数并进行排名")
    @GetMapping("/rankings/event/{voteEventId}")
    public Map<String, Object> getAthleteRankingsByEvent(
            @ApiParam(value = "投票活动ID", required = true) @PathVariable Integer voteEventId) {
        List<Vote> votes = voteService.list();
        Map<Integer, Integer> athleteVotesMap = new HashMap<>();

        // 统计每个运动员在特定投票活动中的得票数
        for (Vote vote : votes) {
            if (vote.getVoteEventId().equals(voteEventId)) {
                athleteVotesMap.merge(vote.getAthleteId(), vote.getVotes(), Integer::sum);
            }
        }

        // 获取所有运动员信息并构建 Map
        List<Athlete> athletes = athleteService.list();
        Map<Integer, Athlete> athleteMap = athletes.stream()
                .collect(Collectors.toMap(Athlete::getId, athlete -> athlete));

        // 构造返回结果
        List<RankingDTO> rankings = new ArrayList<>();
        int rank = 1;

        for (Map.Entry<Integer, Athlete> entry : athleteMap.entrySet()) {
            Integer athleteId = entry.getKey();
            Athlete athlete = entry.getValue();
            int votesForAthlete = athleteVotesMap.getOrDefault(athleteId, 0);
            RankingDTO rankingDTO = new RankingDTO(rank, votesForAthlete, athleteId, athlete.getName(), athlete.getSport(), athlete.getLink());
            rankings.add(rankingDTO);
            rank++;
        }

        // 按得票数降序排序
        rankings.sort((r1, r2) -> Integer.compare(r2.getVotes(), r1.getVotes()));

        // 更新排名
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", rankings);
        return result;
    }

    @ApiOperation(value = "获取某个活动中用户未投票的运动员信息", notes = "获取某个投票活动中用户尚未投票的运动员信息")
    @GetMapping("/unvoted-athletes")
    public Map<String, Object> getUnvotedAthletesByEvent(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
            @ApiParam(value = "投票活动ID", required = true) @RequestParam Integer voteEventId) {
        List<Athlete> unvotedAthletes = voteService.getUnvotedAthletesByUserAndEvent(userId, voteEventId);

        List<Map<String, Object>> unvotedAthleteInfoList = unvotedAthletes.stream()
                .map(athlete -> {
                    Map<String, Object> athleteInfo = new HashMap<>();
                    athleteInfo.put("id", athlete.getId());
                    athleteInfo.put("name", athlete.getName());
                    athleteInfo.put("sport", athlete.getSport());
                    athleteInfo.put("link", athlete.getLink());
                    return athleteInfo;
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", unvotedAthleteInfoList);
        return result;
    }
}