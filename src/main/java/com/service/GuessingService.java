package com.service;

import com.model.entity.Guessing;

import java.util.List;

public interface GuessingService {
    public List<Guessing> selectGuessInfo();
    public List<Integer> selectMatchId(Integer id);
    public void insertChoice(int uid,int rid,int choose);
    public List<Guessing> getAllGuessing();

}
