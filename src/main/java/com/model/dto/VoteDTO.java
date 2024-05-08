package com.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private Integer id;
    private Integer userId;
    private Integer athleteId;
    private Integer voteEventId;
    private Integer votes;
}
