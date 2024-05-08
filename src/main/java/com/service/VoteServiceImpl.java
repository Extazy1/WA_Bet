package com.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.entity.Athlete;
import com.model.entity.Vote;
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
}





