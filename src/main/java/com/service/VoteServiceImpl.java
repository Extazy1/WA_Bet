package com.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.entity.Vote;
import com.service.VoteService;
import com.mapper.VoteMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【vote】的数据库操作Service实现
* @createDate 2024-05-08 07:41:22
*/
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote>
    implements VoteService{

}




