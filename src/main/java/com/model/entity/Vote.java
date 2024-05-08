package com.model.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote implements Serializable {
    /**
     * 投票记录id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 运动员id
     */
    private Integer athleteId;

    /**
     * 投票活动id
     */
    private Integer voteEventId;

    /**
     * 投票数量
     */
    private Integer votes;

    private static final long serialVersionUID = 1L;
}
