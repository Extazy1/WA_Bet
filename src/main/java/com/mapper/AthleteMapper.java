package com.mapper;

import com.model.entity.Athlete;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Admin
* @description 针对表【athlete】的数据库操作Mapper
* @createDate 2024-05-08 07:23:11
* @Entity com.model.entity.Athlete
*/
@Mapper
public interface AthleteMapper extends BaseMapper<Athlete> {

}




