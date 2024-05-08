package com.controller;

import com.model.entity.Vote;
import com.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "投票控制器")
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @ApiOperation(value = "添加投票记录", notes = "新增投票记录信息")
    @PostMapping("/add")
    public Map<String, Object> addVote(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
            @ApiParam(value = "运动员ID", required = true) @RequestParam Integer athleteId,
            @ApiParam(value = "投票活动ID", required = true) @RequestParam Integer voteEventId,
            @ApiParam(value = "投票数量", required = true) @RequestParam Integer votes) {
        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setAthleteId(athleteId);
        vote.setVoteEventId(voteEventId);
        vote.setVotes(votes);

        boolean success = voteService.save(vote);

        Map<String, Object> result = new HashMap<>();
        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "添加成功" : "添加失败");
        result.put("data", success ? vote : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "获取所有投票记录", notes = "获取所有投票记录信息")
    @GetMapping("/all")
    public Map<String, Object> getAllVotes() {
        List<Vote> votes = voteService.list();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", votes);
        return result;
    }

    @ApiOperation(value = "根据ID获取投票记录信息", notes = "通过ID获取特定投票记录信息")
    @GetMapping("/get/{id}")
    public Map<String, Object> getVoteById(
            @ApiParam(value = "投票记录ID", required = true) @PathVariable Integer id) {
        Vote vote = voteService.getById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("code", vote != null ? 0 : 1);
        result.put("msg", vote != null ? "查询成功" : "投票记录不存在");
        result.put("data", vote != null ? vote : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "更新投票记录信息", notes = "更新投票记录信息")
    @PutMapping("/update/{id}")
    public Map<String, Object> updateVote(
            @ApiParam(value = "投票记录ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
            @ApiParam(value = "运动员ID", required = true) @RequestParam Integer athleteId,
            @ApiParam(value = "投票活动ID", required = true) @RequestParam Integer voteEventId,
            @ApiParam(value="投票数量", required = true) @RequestParam Integer votes) {
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

        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "更新成功" : "更新失败");
        result.put("data", success ? vote : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "删除投票记录信息", notes = "通过ID删除特定投票记录信息")
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
}
