package io.proj3ct.SpringGoBot.dao;

import io.proj3ct.SpringGoBot.model.BotQuestions;

import java.util.List;

public interface BotQuestionsDAO {

    public abstract List<BotQuestions> selectBotQuestions();
    public abstract List<BotQuestions> getBotQuestions(Integer id);

}
