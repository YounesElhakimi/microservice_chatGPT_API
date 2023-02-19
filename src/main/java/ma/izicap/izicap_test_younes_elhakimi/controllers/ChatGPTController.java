package  ma.izicap.izicap_test_younes_elhakimi.controllers;

import ma.izicap.izicap_test_younes_elhakimi.models.Question;
import ma.izicap.izicap_test_younes_elhakimi.DTO.QuestionDTOReq;
import ma.izicap.izicap_test_younes_elhakimi.services.CSVService;
import ma.izicap.izicap_test_younes_elhakimi.services.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
public class ChatGPTController {
    @Autowired
    ChatGPTService chatGPTService;

    @Autowired
    CSVService csvService;


    @PostMapping("/answer")
    public ResponseEntity getAnswer(@RequestBody() QuestionDTOReq questionDTO) {


        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());

        if (question.getQuestion().trim().length() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message : The question field is required.!");

        try {
            question.setAnswer(chatGPTService.getAnswerFromChatGPT(question.getQuestion()));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message : Unable to retrieve answer from the ChatGPT API | Dev Message: 401 Unauthorized verify the token in application.properties ");
        }

        // Store the Question and answer in the CSV file
        csvService.storeQuestionAndAnswerInCSV(question);

        // Response with the question and it's answer
        System.out.println(question.getQuestion());
        System.out.println(question.getAnswer());
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @GetMapping("/answer/all")
    public ResponseEntity getAll(){
        List<Question> questionList =  this.csvService.getAllQuestionAndAnswerFromCSV();
        return ResponseEntity.status(HttpStatus.OK).body(questionList);
    }

    @GetMapping("/answer/downloadCSV")
    public ResponseEntity downloadCSV(){

        String path = "answers.csv";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=answer.csv");
        File file = new File(path);

        Path absPath = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(absPath));
        } catch (IOException e) {
            return ResponseEntity.status(200).body("You have to use the endpoint api /answer at least once, in order to be able to download the csv file");
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
