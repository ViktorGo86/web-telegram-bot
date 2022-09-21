package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.dao.BotAnswersDAO;
import io.proj3ct.SpringGoBot.model.BotAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BotAnswersDAOImpl implements BotAnswersDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

  /*  @Override
    public BotAnswers getBotAnswersQuestionId(Long questionId) {
        String GET_BOTANSWERSS_SQL="SELECT * FROM bot_answers where question_id = ?";
        BotAnswers botAnswers = jdbcTemplate.queryForObject(GET_BOTANSWERSS_SQL, new BotAnswersRowMapper(), questionId);
        return botAnswers;
    }*/

    @Override
    public List<BotAnswers> getBotAnswersQuestionId(Long questionId) {
        String GET_BOTANSWERSS_SQL="SELECT * FROM bot_answers where enabled = true and question_id = ?";
        List<BotAnswers> botAnswers = jdbcTemplate.query(GET_BOTANSWERSS_SQL, new BotAnswersRowMapper(), questionId);
        return botAnswers;

    }



}
