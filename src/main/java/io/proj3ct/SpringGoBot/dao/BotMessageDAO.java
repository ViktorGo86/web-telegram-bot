package io.proj3ct.SpringGoBot.dao;

import io.proj3ct.SpringGoBot.model.BotMessage;

import java.util.List;

public interface BotMessageDAO {

    public abstract void insertBotMessage(BotMessage botmessage);
    public abstract List<BotMessage> selectBotMessage();

}
