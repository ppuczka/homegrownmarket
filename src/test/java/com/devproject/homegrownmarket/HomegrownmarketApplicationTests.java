package com.devproject.homegrownmarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class HomegrownmarketApplicationTests {

    private RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private HttpHeaders headers = new HttpHeaders();
    private String cookie = "";

    @Test
    public void should_receive_200OK_when_POST_to_endpoint() {
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("username", "user");
        bodyMap.add("password", "userPass");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<HttpResponse> response = restTemplate.postForEntity("http://localhost:8081/login", requestEntity, HttpResponse.class);
        HttpHeaders responseHeaders = response.getHeaders();
        cookie = responseHeaders.getFirst(HttpHeaders.SET_COOKIE);
        System.out.println("---------------Cookie received = " + cookie + "---------------");
        assertEquals(200, response.getStatusCodeValue());

    }
}
