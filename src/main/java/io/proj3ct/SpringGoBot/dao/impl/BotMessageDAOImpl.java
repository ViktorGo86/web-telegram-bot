package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.dao.BotMessageDAO;
import io.proj3ct.SpringGoBot.model.BotMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BotMessageDAOImpl implements BotMessageDAO {

    String INSERT_BOTMESSAGE_SQL = "INSERT INTO bot_messages(user_id, message_id, name_user, name_full, message) VALUES(?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBotMessage(BotMessage botmessage) {
        int update = jdbcTemplate.update(INSERT_BOTMESSAGE_SQL,
                botmessage.getUserId(),
                botmessage.getMessageId(),
                botmessage.getNameUser(),
                botmessage.getNameFull(),
                botmessage.getMessageName() );

        if(update == 1){
            System.out.println("BotMessage is created..");
        }
    }

}
