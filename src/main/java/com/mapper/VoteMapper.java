package com.mapper;

import com.model.entity.Vote;
import com.model.entity.Athlete;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteMapper extends BaseMapper<Vote> {
    List<Integer> selectVotedAthleteIdsByUserAndEvent(Integer userId, Integer voteEventId);
    List<Athlete> selectUnvotedAthletesByUserAndEvent(Integer userId, Integer voteEventId);
}
