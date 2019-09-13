package com.devproject.homegrownmarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
public class HomegrownmarketApplicationTests {

    private RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    HttpHeaders headers = new HttpHeaders();

//    @BeforeClass
//    public void before() throws JSONException {
//        restTemplate = new RestTemplate();
//        headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        user = new JSONObject();
//        user.put("username", "user");
//        user.put("password", "{noop}userPass");
//
//    }

    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    public void testDivide() {
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(42, 0);
        });
    }

    @Test
    public void loginTest() throws IOException, JSONException {
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("username", "user");
        bodyMap.add("password", "userPass");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<HttpResponse> response = restTemplate.postForEntity("http://localhost:8080/login", requestEntity, HttpResponse.class);
        assertEquals(200, response.getStatusCodeValue());

    }

}
