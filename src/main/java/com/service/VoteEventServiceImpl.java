package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.entity.VoteEvent;
import com.mapper.VoteEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Admin
 * @description 针对表【vote_event】的数据库操作Service实现
 * @createDate 2024-05-08 07:41:47
 */
@Service
public class VoteEventServiceImpl extends ServiceImpl<VoteEventMapper, VoteEvent>
        implements VoteEventService{
    private VoteEventMapper voteEventMapper;

    @Override
    public List<VoteEvent> getAllVoteEvents() {
        return voteEventMapper.selectList(new QueryWrapper<>());
    }
}
