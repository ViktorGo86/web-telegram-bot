package io.proj3ct.SpringGoBot.model;

public class BotQuestions {

    private Integer id;
    private String nameQuestion;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BotQuestions{" +
                "id=" + id +
                ", nameQuestion='" + nameQuestion + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
