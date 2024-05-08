package com.model.dto;

import com.model.entity.Athlete;
import com.model.entity.User;
import com.model.entity.VoteEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotesDTO {
    private Integer id;
    private Integer votes;
    private User user;
    private Athlete athlete;
    private VoteEvent voteEvent;
}
