package ma.izicap.izicap_test_younes_elhakimi.models;

import jakarta.validation.constraints.NotEmpty;

public class Question {

    @NotEmpty(message = "The question is required.")
    private String  question;
    private String  answer;

    public Question(String question) {
        this.question = question;
    }

    public Question() {
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
