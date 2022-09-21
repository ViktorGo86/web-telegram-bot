package io.proj3ct.SpringGoBot.model;

public class BotMessage {

    private Integer id;
    private Long userId;
    private Integer messageId;
    private String nameUser;
    private String nameFull;
    private String messageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    @Override
    public String toString() {
        return "BotMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", messageId=" + messageId +
                ", nameUser='" + nameUser + '\'' +
                ", nameFull='" + nameFull + '\'' +
                ", messageName='" + messageName + '\'' +
                '}';
    }


}
