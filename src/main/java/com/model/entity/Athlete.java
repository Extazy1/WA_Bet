package com.model.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Athlete implements Serializable {
    /**
     * 运动员id
     */
    private Integer id;

    /**
     * 运动员姓名
     */
    private String name;

    /**
     * 运动类型
     */
    private String sport;

    /**
     * 运动员信息链接
     */
    private String link;

    private static final long serialVersionUID = 1L;
}