package  ma.izicap.izicap_test_younes_elhakimi.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ma.izicap.izicap_test_younes_elhakimi.models.Question;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {
    String path = "answers.csv";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Boolean storeQuestionAndAnswerInCSV(Question question) {
        try {
            File answersFile = new File(path);

            // create CSV File if Not exists
            if (!answersFile.exists()) {
                System.out.println("==================  create file ========================");
                answersFile.createNewFile();

                //FileWriter writer = new FileWriter(answersFile, true);

                //writer.write("Question;answer\n");
               // writer.close();
                //
                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(answersFile);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile);

                // adding header to csv
                String[] header = { "Question", "Answer"};
                writer.writeNext(header);
                writer.close();

            }

            // create CSVWriter object filewriter object as parameter
            FileWriter outputfile = new FileWriter(answersFile);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            String[] data = {question.getQuestion(), question.getAnswer() };
            writer.writeNext(data);
            //FileWriter writer = new FileWriter(answersFile, true);
           // writer.write(question + ";" + answer + "\n");
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: Unable to store answer in the CSV file." + e.toString());
            return false;
        }
    }

    @Override
    public List<Question> getAllQuestionAndAnswerFromCSV() {
        List<Question> questionList= new ArrayList<>();

        File file = new File(path);

        if (!file.exists()) return questionList;

        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(path);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                questionList.add(new Question(nextRecord[0],nextRecord[1]));
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();}

        return questionList;

    }
}
