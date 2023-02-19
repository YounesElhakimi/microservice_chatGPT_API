package ma.izicap.izicap_test_younes_elhakimi.services;

import ma.izicap.izicap_test_younes_elhakimi.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CSVServiceTest {
    @Autowired
    CSVService csvService;

    String fileName = "testFile.csv";


    Boolean deleteTestFile(){
        File file = new File(this.fileName);
        return file.delete();
    }

    public CSVServiceTest() {
        // delete the csv test file if exists
        this.deleteTestFile();
    }

    @Test
    void storeQuestionAndAnswerInCSV() {
        this.csvService.setPath(this.fileName);
        Question question = new Question();
        question.setQuestion("how are you?" );
        question.setAnswer("Fine.");
        Boolean isQuestionStored =  this.csvService.storeQuestionAndAnswerInCSV(question);
        File file = new File(this.fileName);
        assertTrue(file.exists(),"the csv file Creation Test passed");
        assertTrue(isQuestionStored, "store Question And Answer In CSV test passed");


        this.deleteTestFile();
    }

    @Test
    void getAllQuestionAndAnswerFromCSV() {
        this.csvService.setPath(this.fileName);
        Question question1 = new Question();
        Question question2 = new Question();
        question1.setQuestion("how are you?");
        question2.setQuestion("are you fine?");
        question1.setAnswer("I'm Fine.");
        question2.setAnswer("Yes .");

        this.csvService.storeQuestionAndAnswerInCSV(question1);
        this.csvService.storeQuestionAndAnswerInCSV(question2);

        List<Question> questionList = this.csvService.getAllQuestionAndAnswerFromCSV();

        assertTrue(questionList.get(1).getQuestion().equals(question1.getQuestion()));
        assertTrue(questionList.get(1).getAnswer().equals(question1.getAnswer()));

        assertTrue(questionList.get(2).getQuestion().equals(question2.getQuestion()));
        assertTrue(questionList.get(2).getAnswer().equals(question2.getAnswer()));


        this.deleteTestFile();



    }
}