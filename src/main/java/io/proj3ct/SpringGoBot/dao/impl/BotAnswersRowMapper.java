package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.model.BotAnswers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BotAnswersRowMapper implements RowMapper<BotAnswers> {

    @Override
    public BotAnswers mapRow(ResultSet rs, int rowNum) throws SQLException {
        BotAnswers botAnswers= new BotAnswers();
        botAnswers.setId(rs.getInt("id"));
        botAnswers.setQuestionId(rs.getLong("question_id"));
        botAnswers.setNameAnswer(rs.getString("name_answer"));
        botAnswers.setCorrectAnswer(rs.getBoolean("correct_answer"));
        botAnswers.setDescription(rs.getString("description"));
        botAnswers.setEnabled(rs.getBoolean("enabled"));
        return botAnswers;
    }
}

