package ma.izicap.izicap_test_younes_elhakimi.DTO;

import jakarta.validation.constraints.NotEmpty;

public class QuestionDTOReq {

    @NotEmpty(message = "The question is required.")
    private String  question;

    public QuestionDTOReq(String question) {
        this.question = question;
    }

    public QuestionDTOReq() {
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
