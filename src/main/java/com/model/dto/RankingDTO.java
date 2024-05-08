package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {
    private Integer rank;
    private Integer votes;
    private Integer athleteId;
    private String athleteName;
    private String sport;
    private String link;
}
