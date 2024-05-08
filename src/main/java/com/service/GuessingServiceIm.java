package com.service;

import com.mapper.GuessingMapper;
import com.model.entity.Guessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessingServiceIm implements GuessingService{
    @Autowired
    GuessingMapper guessingDao;
    @Override
    public List<Guessing> selectGuessInfo() {
        return guessingDao.selectGuessInfo();
    }
    @Override
    public List<Integer> selectMatchId(Integer id) {
        return guessingDao.selectMatchId(id);
    }
    @Override
    public void insertChoice(int uid,int rid,int choose) {
        guessingDao.insertChoice(uid,rid,choose);
    }
    @Override
    public List<Guessing> getAllGuessing() {
        return guessingDao.getAll();
    }


}
