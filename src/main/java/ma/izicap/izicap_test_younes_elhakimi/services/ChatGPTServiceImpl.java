package  ma.izicap.izicap_test_younes_elhakimi.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Value("${chatGPT.API.key}")
    String ChatGPT_API_KEY;
    @Value("${chatGPT.API.endpoint}")
    String ChatGPT_API_URL;


    public String getAnswerFromChatGPT(String question) {

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(ChatGPT_API_URL, getHttpEntity(question), String.class);

            // Get the answer from the response
            JSONObject jsonResponse = new JSONObject(response);
            String answer = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");

            // because \n make a new line in the csv file
            answer = answer.replaceAll("\n", "");

            return answer;

    }

    HttpEntity getHttpEntity(String question){
        String payload = "{\"model\": \"text-davinci-003\",\"prompt\":\"" + question + "\",\"max_tokens\":4000,\"temperature\":1.0}";
        return new HttpEntity<>(payload, getHttpHeaders());
    }

    HttpHeaders  getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + ChatGPT_API_KEY);
        return headers;
    }
}
