package com.mapper;

import com.model.entity.Guessing;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GuessingMapper {
    public List<Guessing> selectGuessInfo();
    public List<Integer> selectMatchId(Integer id);
    //添加新选择
    public void insertChoice(int uid,int rid,int choose);
    public List<Guessing> getAll();
}
