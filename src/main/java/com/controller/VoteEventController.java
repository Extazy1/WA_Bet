package com.controller;

import com.model.dto.VoteEventDTO;
import com.model.entity.VoteEvent;
import com.service.VoteEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "投票活动控制器")
@RestController
@RequestMapping("/voteEvent")
public class VoteEventController {

    @Autowired
    private VoteEventService voteEventService;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "添加投票活动", notes = "新增投票活动信息")
    @PostMapping("/add")
    public Map<String, Object> addVoteEvent(
            @ApiParam(value = "投票活动名称", required = true) @RequestParam String name,
            @ApiParam(value = "投票活动开始时间", required = true) @RequestParam String startTime,
            @ApiParam(value = "投票活动结束时间", required = true) @RequestParam String endTime,
            @ApiParam(value = "每名用户可投票最大数量（0表示不限制）", required = true) @RequestParam Integer maxVotesPerUser) {
        Map<String, Object> result = new HashMap<>();
        try {
            Date startDate = DATE_FORMAT.parse(startTime);
            Date endDate = DATE_FORMAT.parse(endTime);

            VoteEvent voteEvent = new VoteEvent();
            voteEvent.setName(name);
            voteEvent.setStartTime(startDate);
            voteEvent.setEndTime(endDate);
            voteEvent.setMaxVotesPerUser(maxVotesPerUser);

            boolean success = voteEventService.save(voteEvent);

            VoteEventDTO voteEventDTO = new VoteEventDTO(voteEvent.getId(), voteEvent.getName(), voteEvent.getStartTime(), voteEvent.getEndTime(), voteEvent.getMaxVotesPerUser());

            result.put("code", success ? 0 : 1);
            result.put("msg", success ? "添加成功" : "添加失败");
            result.put("data", success ? voteEventDTO : new HashMap<>());
        } catch (ParseException e) {
            result.put("code", 1);
            result.put("msg", "日期格式错误");
            result.put("data", new HashMap<>());
        }
        return result;
    }

    @ApiOperation(value = "获取所有投票活动信息", notes = "获取所有投票活动信息")
    @GetMapping("/all")
    public Map<String, Object> getAllVoteEvents() {
        List<VoteEvent> voteEvents = voteEventService.list();
        List<VoteEventDTO> voteEventDTOs = voteEvents.stream()
                .map(voteEvent -> new VoteEventDTO(voteEvent.getId(), voteEvent.getName(), voteEvent.getStartTime(), voteEvent.getEndTime(), voteEvent.getMaxVotesPerUser()))
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", voteEventDTOs);
        return result;
    }

    @ApiOperation(value = "根据ID获取投票活动信息", notes = "通过ID获取特定投票活动信息")
    @GetMapping("/get/{id}")
    public Map<String, Object> getVoteEventById(
            @ApiParam(value = "投票活动ID", required = true) @PathVariable Integer id) {
        VoteEvent voteEvent = voteEventService.getById(id);
        VoteEventDTO voteEventDTO = voteEvent != null ? new VoteEventDTO(voteEvent.getId(), voteEvent.getName(), voteEvent.getStartTime(), voteEvent.getEndTime(), voteEvent.getMaxVotesPerUser()) : null;

        Map<String, Object> result = new HashMap<>();
        result.put("code", voteEvent != null ? 0 : 1);
        result.put("msg", voteEvent != null ? "查询成功" : "投票活动不存在");
        result.put("data", voteEventDTO != null ? voteEventDTO : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "更新投票活动信息", notes = "更新投票活动信息")
    @PutMapping("/update/{id}")
    public Map<String, Object> updateVoteEvent(
            @ApiParam(value = "投票活动ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "投票活动名称", required = true) @RequestParam String name,
            @ApiParam(value = "投票活动开始时间", required = true) @RequestParam String startTime,
            @ApiParam(value = "投票活动结束时间", required = true) @RequestParam String endTime,
            @ApiParam(value = "每名用户可投票最大数量（0表示不限制）", required = true) @RequestParam Integer maxVotesPerUser) {
        Map<String, Object> result = new HashMap<>();
        try {
            Date startDate = DATE_FORMAT.parse(startTime);
            Date endDate = DATE_FORMAT.parse(endTime);

            VoteEvent voteEvent = voteEventService.getById(id);
            if (voteEvent == null) {
                result.put("code", 1);
                result.put("msg", "投票活动不存在");
                result.put("data", new HashMap<>());
                return result;
            }

            voteEvent.setName(name);
            voteEvent.setStartTime(startDate);
            voteEvent.setEndTime(endDate);
            voteEvent.setMaxVotesPerUser(maxVotesPerUser);

            boolean success = voteEventService.updateById(voteEvent);

            VoteEventDTO voteEventDTO = new VoteEventDTO(voteEvent.getId(), voteEvent.getName(), voteEvent.getStartTime(), voteEvent.getEndTime(), voteEvent.getMaxVotesPerUser());

            result.put("code", success ? 0 : 1);
            result.put("msg", success ? "更新成功" : "更新失败");
            result.put("data", success ? voteEventDTO : new HashMap<>());
        } catch (ParseException e) {
            result.put("code", 1);
            result.put("msg", "日期格式错误");
            result.put("data", new HashMap<>());
        }
        return result;
    }

    @ApiOperation(value = "删除投票活动信息", notes = "通过ID删除特定投票活动信息")
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteVoteEvent(
            @ApiParam(value = "投票活动ID", required = true) @PathVariable Integer id) {
        boolean success = voteEventService.removeById(id);

        Map<String, Object> result = new HashMap<>();
        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "删除成功" : "删除失败或投票活动不存在");
        result.put("data", new HashMap<>());
        return result;
    }
}
