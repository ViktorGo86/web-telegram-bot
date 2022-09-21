package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.model.BotQuestions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BotQuestionsRowMapper implements RowMapper<BotQuestions> {

    @Override
    public BotQuestions mapRow(ResultSet rs, int rowNum) throws SQLException {
        BotQuestions botQuestions = new BotQuestions();
        botQuestions.setId(rs.getInt("id"));
        botQuestions.setNameQuestion(rs.getString("name_question"));
        botQuestions.setDescription(rs.getString("description"));
        return botQuestions;
    }
}

