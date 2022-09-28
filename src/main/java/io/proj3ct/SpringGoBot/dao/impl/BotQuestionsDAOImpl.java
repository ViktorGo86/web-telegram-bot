package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.dao.BotQuestionsDAO;
import io.proj3ct.SpringGoBot.model.BotQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BotQuestionsDAOImpl implements BotQuestionsDAO {

    String SELECT_BOTQUESTIONS_SQL="SELECT * FROM bot_questions WHERE enabled = true";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BotQuestions> selectBotQuestions() {
        List<BotQuestions> botQuestions = jdbcTemplate.query(SELECT_BOTQUESTIONS_SQL, new BotQuestionsRowMapper());
        return botQuestions;

    }



}
