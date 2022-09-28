package io.proj3ct.SpringGoBot.service;

import io.proj3ct.SpringGoBot.config.BotConfig;

import io.proj3ct.SpringGoBot.dao.BotAnswersDAO;
import io.proj3ct.SpringGoBot.dao.BotMessageDAO;
import io.proj3ct.SpringGoBot.dao.BotQuestionsDAO;
import io.proj3ct.SpringGoBot.model.BotAnswers;
import io.proj3ct.SpringGoBot.model.BotMessage;
import io.proj3ct.SpringGoBot.model.BotQuestions;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    static  final String HELP_TEXT = "Этот бот создал @ViktorGnidenko\n\n"+
            "Вы можете выполнять команды из главного меню или набрав команду:\n\n"+
            "Выберите /start, чтобы увидеть приветственное сообщение\n\n"+
            "Выберите /set_questions, чтобы пройти тестирование на знание основ SQL\n\n"+
            "Выберите /help, чтобы снова увидеть это сообщение";


    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> ListofCommands = new ArrayList<>();
        ListofCommands.add(new BotCommand("/start", "Приветственное сообщение"));
        ListofCommands.add(new BotCommand("/set_questions","Тест на знание основ SQL"));
        ListofCommands.add(new BotCommand("/help","Справка"));
        try {
            this.execute(new SetMyCommands(ListofCommands, new BotCommandScopeDefault(),null));
        } catch (TelegramApiException e) {
        }

    }

    @Autowired
    private BotMessageDAO botMessageDAO;

    @Autowired
    private BotAnswersDAO botAnswersDAO;

    @Autowired
    private BotQuestionsDAO botQuestionsDAO;

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        List<BotQuestions> botQues = botQuestionsDAO.selectBotQuestions();
        Collections.shuffle(botQues);
        for (BotQuestions recQues : botQues) {
            String NameQuestion = recQues.getNameQuestion();
            Long pAnswersId = Long.valueOf(recQues.getId());
            if (update.hasMessage()) {
                handleQuestions(update.getMessage(), pAnswersId, NameQuestion);
                insertBD(update);
            }
        }

        if (update.hasCallbackQuery()) {
            callbackQuestions(update.getCallbackQuery());
        }

        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    sendMessage(chatId, "Какой я классный бот!");
                    break;
                case "/set_questions":
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                default:
                    sendMessage(chatId, "Извините, нет такой команды. Откройте справку /help");
            }
        }

    }

    private void insertBotMessage(Long userId, Integer messageId,  String nameUser, String nameFull, String messageName) {
        BotMessage botmessage = new BotMessage();
        botmessage.setUserId(userId);
        botmessage.setMessageId(messageId);
        botmessage.setNameUser(nameUser);
        botmessage.setNameFull(nameFull);
        botmessage.setMessageName(messageName);
        botMessageDAO.insertBotMessage(botmessage);
    }

    private void startCommandReceived(long chatId, String name){
        String answer = "Привет, " + name +", меня зовут SpringGoBot, рад знакомству!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend)  {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        System.out.println(message.getText());
        try{
            execute(message);
        }
        catch (TelegramApiException e) {

        }
    }

    private void insertBD(Update update) {
        System.out.println(update);
        Message mes = update.getMessage();
        Integer messageId = mes.getMessageId();
        String text = mes.getText();
        User user = mes.getFrom();
        System.out.println("user_id: " + user.getId() + " messageId: " + messageId + " User_Name: " + user.getFirstName() + " User_username: " + user.getUserName() + " message: " + text);
        insertBotMessage(user.getId(), messageId, user.getFirstName(), user.getUserName(), text);
    }

    @SneakyThrows
    private void handleQuestions(Message message, Long pAnswersId, String NameQuestion) {
        if (message.hasText() && message.hasEntities()) {
            List<BotAnswers> botAns = botAnswersDAO.getBotAnswersQuestionId(pAnswersId);
            Collections.shuffle(botAns);
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message
                                .getText()
                                .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/set_questions":
                        List<InlineKeyboardButton> buttons = new ArrayList<>();
                        int row = 0;
                        String pNameAnswer = "";
                        for (BotAnswers record : botAns) {
                            row = row + 1;
                            System.out.println(row);
                            pNameAnswer = pNameAnswer + "\n" + row+". "+record.getNameAnswer();
                            System.out.println(record.getNameAnswer());
                            System.out.println(record.isCorrectAnswer());
                            System.out.println("ANSWERS_" + record.isCorrectAnswer());
                            buttons.addAll(
                                    Arrays.asList(
                                            InlineKeyboardButton
                                                    .builder()
                                                    .text(String.valueOf(row))
                                                    .callbackData("ANSWERS_" + record.isCorrectAnswer())
                                                    .build()
                                    )
                            );
                        }
                        System.out.println(pNameAnswer);
                        execute(
                                SendMessage.builder()
                                        .text(NameQuestion + "\n" + pNameAnswer)
                                        .chatId(message.getChatId().toString())
                                        .replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(buttons).build())
                                        .build());
                        return;
                }
            }
        }
    }
    @SneakyThrows
    private void callbackQuestions(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        System.out.println(data);
        if (data.equals("ANSWERS_true")) {
            System.out.println("✅ Правильный ответ!");
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setText("✅ Правильный ответ!");
            editMessageText.setChatId(String.valueOf(message.getChatId()));
            editMessageText.setMessageId(message.getMessageId());
            try {
                execute(editMessageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (data.equals("ANSWERS_false")) {
            System.out.println("❌ Неправильный ответ!");
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setText("❌ Неправильный ответ!");
            editMessageText.setChatId(String.valueOf(message.getChatId()));
            editMessageText.setMessageId(message.getMessageId());
            try {
                execute(editMessageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



}


