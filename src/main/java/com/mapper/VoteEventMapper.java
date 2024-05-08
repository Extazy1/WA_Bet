package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.entity.VoteEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Admin
 * @description 针对表【vote_event】的数据库操作Mapper
 * @createDate 2024-05-08 07:41:47
 * @Entity com.model.entity.VoteEvent
 */
@Mapper
public interface VoteEventMapper extends BaseMapper<VoteEvent> {

}
