package  ma.izicap.izicap_test_younes_elhakimi.services;

import ma.izicap.izicap_test_younes_elhakimi.models.Question;

import java.util.List;

public interface CSVService {
    Boolean storeQuestionAndAnswerInCSV(Question question);
    List<Question> getAllQuestionAndAnswerFromCSV();
    public void setPath(String path);
    public String getPath();
}
