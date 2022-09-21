package io.proj3ct.SpringGoBot.dao.impl;

import io.proj3ct.SpringGoBot.dao.BotMessageDAO;
import io.proj3ct.SpringGoBot.model.BotMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BotMessageDAOImpl implements BotMessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBotMessage(BotMessage botmessage) {
        String INSERT_BOTMESSAGE_SQL = "INSERT INTO bot_messages(user_id, message_id, name_user, name_full, message) VALUES(?,?,?,?,?)";
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

    @Override
    public List<BotMessage> selectBotMessage() {
        String SELECT_BOTMESSAGE_SQL="SELECT * FROM bot_messages";
        List<BotMessage> botMessage = jdbcTemplate.query(SELECT_BOTMESSAGE_SQL, new BotMessageRowMapper());
        return botMessage;

    }
}
