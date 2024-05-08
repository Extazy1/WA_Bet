package com.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteEvent implements Serializable {
    /**
     * 投票活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 投票活动名称
     */
    private String name;

    /**
     * 投票活动开始时间
     */
    private Date startTime;

    /**
     * 投票活动截止时间
     */
    private Date endTime;

    /**
     * 每名用户可投票最大数量（0表示不限制）
     */
    private Integer maxVotesPerUser;

    private static final long serialVersionUID = 1L;
}
