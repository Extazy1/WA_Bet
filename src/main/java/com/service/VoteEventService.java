package com.service;

import com.model.entity.VoteEvent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Admin
 * @description 针对表【vote_event】的数据库操作Service
 * @createDate 2024-05-08 07:41:47
 */
public interface VoteEventService extends IService<VoteEvent> {
    List<VoteEvent> getAllVoteEvents();
}
