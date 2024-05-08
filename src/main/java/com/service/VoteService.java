package com.service;

import com.model.entity.Athlete;
import com.model.entity.Vote;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Admin
* @description 针对表【vote】的数据库操作Service
* @createDate 2024-05-08 07:41:22
*/
public interface VoteService extends IService<Vote> {
    List<Integer> getVotedAthleteIdsByUserAndEvent(Integer userId, Integer voteEventId);
    List<Athlete> getUnvotedAthletesByUserAndEvent(Integer userId, Integer voteEventId);
}

