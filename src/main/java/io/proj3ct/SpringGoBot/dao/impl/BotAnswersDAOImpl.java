package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.dao.BotAnswersDAO;
import io.proj3ct.SpringGoBot.model.BotAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BotAnswersDAOImpl implements BotAnswersDAO {

    String GET_BOTANSWERSS_SQL="SELECT * FROM bot_answers where enabled = true and question_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BotAnswers> getBotAnswersQuestionId(Long questionId) {
        List<BotAnswers> botAnswers = jdbcTemplate.query(GET_BOTANSWERSS_SQL, new BotAnswersRowMapper(), questionId);
        return botAnswers;

    }



}
