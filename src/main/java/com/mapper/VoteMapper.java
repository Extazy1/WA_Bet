package com.mapper;

import com.model.entity.Vote;
import com.model.entity.Athlete;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoteMapper extends BaseMapper<Vote> {
    List<Integer> selectVotedAthleteIdsByUserAndEvent(Integer userId, Integer voteEventId);
    List<Athlete> selectUnvotedAthletesByUserAndEvent(Integer userId, Integer voteEventId);
    Vote selectVoteByUserAndEvent(@Param("userId") Integer userId, @Param("athleteId") Integer athleteId, @Param("voteEventId") Integer voteEventId);
    void incrementVote(@Param("userId") Integer userId, @Param("athleteId") Integer athleteId, @Param("voteEventId") Integer voteEventId, @Param("votes") Integer votes);
}
