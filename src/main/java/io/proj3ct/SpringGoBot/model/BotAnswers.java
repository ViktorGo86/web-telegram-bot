package io.proj3ct.SpringGoBot.model;

public class BotAnswers {

    private Integer id;
    private Long questionId;
    private String nameAnswer;
    private boolean correctAnswer;
    private String description;
    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getNameAnswer() {
        return nameAnswer;
    }

    public void setNameAnswer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "BotAnswers{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", nameAnswer='" + nameAnswer + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
