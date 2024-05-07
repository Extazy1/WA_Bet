package com.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guessing {
    private long id;          // 主键，自增
    private String name;      // 比赛名称
    private String player1;  // 比赛人1名称
    private int choose1;     // 队伍1被选择的人数
    private String player2;  // 比赛人2名称
    private int choose2;     // 队伍2被选择的人数
    private Date startTime;   // 开始时间
    private Date endTime;     // 结束时间
}
