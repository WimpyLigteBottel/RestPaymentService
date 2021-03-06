package nel.marco.restservice;

import com.google.gson.Gson;
import nel.marco.controller.model.Response;
import nel.marco.controller.model.UserDetail;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@Component
public class RestService {

    private final RestTemplate restTemplate;

    public RestService() {
        this.restTemplate = new RestTemplate();
    }


    public void helloWorld() {

        HttpEntity<String> request = new HttpEntity(new String("randomJunk"));

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/hello", HttpMethod.GET, request, String.class);


        JOptionPane.showMessageDialog(null, exchange.getBody());

    }


    public void repeat(String word) {

        HttpEntity<String> request = new HttpEntity<>(word);

        String url = "http://localhost:8080/repeat?text=" + word;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);


        JOptionPane.showMessageDialog(null, exchange.getBody());

    }


    public void login(String username, String password) {

        HttpEntity<String> request = new HttpEntity<>("");

        String url = "http://localhost:8080/login?username=" + username + "&password=" + password;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);


        JOptionPane.showMessageDialog(null, exchange.getBody());

    }


    public void login(UserDetail userDetail) {

        HttpHeaders httpHeaders = new HttpHeaders();
        String headerValue = new Gson().toJson(userDetail, UserDetail.class);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("userdetail", headerValue);

        HttpEntity<String> entity = new HttpEntity<>(headerValue, httpHeaders);

        String url = "http://localhost:8080/loginUserDetails";
        ResponseEntity<Response> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Response.class);


        JOptionPane.showMessageDialog(null, exchange.getBody().getMessage());

    }
}
