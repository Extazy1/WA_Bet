package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteEventDTO {
    private Integer id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer maxVotesPerUser;
}
