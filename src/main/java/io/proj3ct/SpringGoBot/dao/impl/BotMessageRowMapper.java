package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.model.BotMessage;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BotMessageRowMapper implements RowMapper<BotMessage> {

    @Override
    public BotMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
        BotMessage botMessage = new BotMessage();
        botMessage.setId(rs.getInt("id"));
        botMessage.setUserId(rs.getLong("user_id"));
        botMessage.setMessageId(rs.getInt("message_id"));
        botMessage.setNameUser(rs.getString("name_user"));
        botMessage.setNameFull(rs.getString("name_full"));
        botMessage.setMessageName(rs.getString("message"));
        return botMessage;
    }
}

