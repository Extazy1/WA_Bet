package com.controller;

import com.model.dto.AthleteDTO;
import com.model.entity.Athlete;
import com.service.AthleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "运动员控制器")
@RestController
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @ApiOperation(value = "添加运动员", notes = "新增运动员信息")
    @PostMapping("/add")
    public Map<String, Object> addAthlete(
            @ApiParam(value = "运动员姓名", required = true) @RequestParam String name,
            @ApiParam(value = "运动类型", required = true) @RequestParam String sport,
            @ApiParam(value = "运动员信息链接", required = true) @RequestParam String link) {
        Athlete athlete = new Athlete();
        athlete.setName(name);
        athlete.setSport(sport);
        athlete.setLink(link);

        boolean success = athleteService.save(athlete);

        AthleteDTO athleteDTO = new AthleteDTO(athlete.getId(), athlete.getName(), athlete.getSport(), athlete.getLink());

        Map<String, Object> result = new HashMap<>();
        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "添加成功" : "添加失败");
        result.put("data", success ? athleteDTO : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "获取所有运动员信息", notes = "获取所有运动员信息")
    @GetMapping("/all")
    public Map<String, Object> getAllAthletes() {
        List<Athlete> athletes = athleteService.list();
        List<AthleteDTO> athleteDTOs = athletes.stream()
                .map(athlete -> new AthleteDTO(athlete.getId(), athlete.getName(), athlete.getSport(), athlete.getLink()))
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", athleteDTOs);
        return result;
    }

    @ApiOperation(value = "根据ID获取运动员信息", notes = "通过ID获取特定运动员信息")
    @GetMapping("/get/{id}")
    public Map<String, Object> getAthleteById(
            @ApiParam(value = "运动员ID", required = true) @PathVariable Integer id) {
        Athlete athlete = athleteService.getById(id);
        AthleteDTO athleteDTO = athlete != null ? new AthleteDTO(athlete.getId(), athlete.getName(), athlete.getSport(), athlete.getLink()) : null;

        Map<String, Object> result = new HashMap<>();
        result.put("code", athlete != null ? 0 : 1);
        result.put("msg", athlete != null ? "查询成功" : "运动员不存在");
        result.put("data", athleteDTO != null ? athleteDTO : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "更新运动员信息", notes = "更新运动员信息")
    @PutMapping("/update/{id}")
    public Map<String, Object> updateAthlete(
            @ApiParam(value = "运动员ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "运动员姓名", required = true) @RequestParam String name,
            @ApiParam(value = "运动类型", required = true) @RequestParam String sport,
            @ApiParam(value = "运动员信息链接", required = true) @RequestParam String link) {
        Athlete athlete = athleteService.getById(id);

        Map<String, Object> result = new HashMap<>();
        if (athlete == null) {
            result.put("code", 1);
            result.put("msg", "运动员不存在");
            result.put("data", new HashMap<>());
            return result;
        }

        athlete.setName(name);
        athlete.setSport(sport);
        athlete.setLink(link);

        boolean success = athleteService.updateById(athlete);

        AthleteDTO athleteDTO = new AthleteDTO(athlete.getId(), athlete.getName(), athlete.getSport(), athlete.getLink());

        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "更新成功" : "更新失败");
        result.put("data", success ? athleteDTO : new HashMap<>());
        return result;
    }

    @ApiOperation(value = "删除运动员信息", notes = "通过ID删除特定运动员信息")
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteAthlete(
            @ApiParam(value = "运动员ID", required = true) @PathVariable Integer id) {
        boolean success = athleteService.removeById(id);

        Map<String, Object> result = new HashMap<>();
        result.put("code", success ? 0 : 1);
        result.put("msg", success ? "删除成功" : "删除失败或运动员不存在");
        result.put("data", new HashMap<>());
        return result;
    }
}
