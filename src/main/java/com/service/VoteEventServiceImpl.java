package com.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.entity.VoteEvent;
import com.service.VoteEventService;
import com.mapper.VoteEventMapper;
import org.springframework.stereotype.Service;

/**
 * @author Admin
 * @description 针对表【vote_event】的数据库操作Service实现
 * @createDate 2024-05-08 07:41:47
 */
@Service
public class VoteEventServiceImpl extends ServiceImpl<VoteEventMapper, VoteEvent>
        implements VoteEventService{

}
