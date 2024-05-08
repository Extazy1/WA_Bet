package com.mapper;

import com.model.entity.Vote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Admin
* @description 针对表【vote】的数据库操作Mapper
* @createDate 2024-05-08 07:41:22
* @Entity com.model.entity.Vote
*/
@Mapper
public interface VoteMapper extends BaseMapper<Vote> {

}




