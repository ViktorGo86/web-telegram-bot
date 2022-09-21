package io.proj3ct.SpringGoBot.dao;


import io.proj3ct.SpringGoBot.model.BotAnswers;

import java.util.List;

public interface BotAnswersDAO {


    public abstract List<BotAnswers> getBotAnswersQuestionId(Long questionId);

}
