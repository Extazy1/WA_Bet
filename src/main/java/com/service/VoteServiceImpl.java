package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.entity.Athlete;
import com.model.entity.Vote;
import com.model.entity.VoteEvent;
import com.service.VoteService;
import com.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Admin
* @description 针对表【vote】的数据库操作Service实现
* @createDate 2024-05-08 07:41:22
*/
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements VoteService {
    private final VoteMapper voteMapper;

    @Autowired
    public VoteServiceImpl(VoteMapper voteMapper) {
        this.voteMapper = voteMapper;
    }

    @Override
    public List<Integer> getVotedAthleteIdsByUserAndEvent(Integer userId, Integer voteEventId) {
        return voteMapper.selectVotedAthleteIdsByUserAndEvent(userId, voteEventId);
    }

    @Override
    public List<Athlete> getUnvotedAthletesByUserAndEvent(Integer userId, Integer voteEventId) {
        return voteMapper.selectUnvotedAthletesByUserAndEvent(userId, voteEventId);
    }

    @Override
    public void addOrUpdateVote(Integer userId, Integer athleteId, Integer voteEventId, Integer votes) {
        Vote existingVote = voteMapper.selectVoteByUserAndEvent(userId, athleteId, voteEventId);
        if (existingVote != null) {
            voteMapper.incrementVote(userId, athleteId, voteEventId, votes);
        } else {
            Vote newVote = new Vote();
            newVote.setUserId(userId);
            newVote.setAthleteId(athleteId);
            newVote.setVoteEventId(voteEventId);
            newVote.setVotes(votes);
            voteMapper.insert(newVote);
        }
    }
}





