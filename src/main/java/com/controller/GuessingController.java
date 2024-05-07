package com.controller;

import com.model.entity.Guessing;
import com.service.GuessingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(tags = "竞猜控制器")
@RestController
@RequestMapping("/guessing")
public class GuessingController {
    @Autowired
    private GuessingService guessingService;

    @ApiOperation(value = "获取竞猜信息", notes = "获取所有的竞猜信息并将匹配的比赛标记为已竞猜")
    @PostMapping("guessinfo")
    public List<Map<String, Object>> guessInfo(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer id,
            HttpServletRequest request) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        // 先得到所有的竞猜信息
        List<Guessing> guessingList = guessingService.selectGuessInfo();
        for (Guessing guessing : guessingList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("matchId", guessing.getId());
            map.put("matchname", guessing.getName());
            map.put("player_1", guessing.getPlayer1());
            map.put("player_2", guessing.getPlayer2());
            map.put("already", false);
            mapList.add(map);
        }

        // 获取用户已竞猜的比赛 ID 列表并将其转换为 Long 类型
        List<Integer> matchIdListInt = guessingService.selectMatchId(id);
        Set<Long> matchIdSet = new HashSet<>();
        for (Integer matchIdInt : matchIdListInt) {
            matchIdSet.add(matchIdInt.longValue());
        }

        // 更新地图中对应竞猜比赛的 `already` 字段
        for (Map<String, Object> map : mapList) {
            Long matchId = ((Number) map.get("matchId")).longValue(); // 确保转换为 Long 类型
            if (matchIdSet.contains(matchId)) {
                map.put("already", true);
            }
        }

        return mapList;
    }



    @ApiOperation(value = "选择竞猜", notes = "用户进行竞猜选择")
    @PostMapping("/choose")
    public Map<String, Object> choose(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
            @ApiParam(value = "比赛ID", required = true) @RequestParam Integer rId,
            @ApiParam(value = "选择的ID", required = true) @RequestParam Integer chooseId,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        guessingService.insertChoice(userId, rId, chooseId);
        result.put("code", "1");
        return result;
    }

    @ApiOperation(value = "获取所有竞猜信息", notes = "获取所有竞猜信息")
    @GetMapping("/getAll")
    public List<Guessing> getAllGuessingInfo() {
        List<Guessing> guessingList = guessingService.getAllGuessing();
        //guessingList.forEach(System.out::println); // 打印调试
        return guessingList;
    }
}
