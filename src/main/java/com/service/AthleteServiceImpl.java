package com.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.entity.Athlete;
import com.mapper.AthleteMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【athlete】的数据库操作Service实现
* @createDate 2024-05-08 07:23:11
*/
@Service
public class AthleteServiceImpl extends ServiceImpl<AthleteMapper, Athlete>
    implements AthleteService{

}




