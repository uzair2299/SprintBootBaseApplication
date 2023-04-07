package com.example.demo;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        usageOfRestTemplateForGetRequest();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "uzair");
        jsonObject.put("salary", "12345");
        jsonObject.put("age", "20");
        HttpHeaders headers = createHeader();
        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject, headers);
        //postRequest(entity, "https://dummy.restapiexample.com/api/v1/create");

        requestWithExchange("https://dummy.restapiexample.com/api/v1/create", entity);

    }


    public static void usageOfRestTemplateForGetRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://dummyjson.com/products";
        String resourceUrl_ = "https://dummyjson.com/products/1";
        System.out.println("The getForEntity method retrieves resources from the given URI or URL templates.It returns response as ResponseEntity using which we can get response status code, response body");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(resourceUrl, String.class);
        System.out.println("responseEntity.getStatusCode() - " + responseEntity.getStatusCode());
        System.out.println("responseEntity.getBody() - " + responseEntity.getBody());
        System.out.println("responseEntity.getHeader - " + responseEntity.getHeaders());


        System.out.println("The getForObject method fetches the data for the given response type from the given URI or URL template using HTTP GET method.");
        String responseObject = restTemplate.getForObject(resourceUrl_, String.class);
        System.out.println("responseObject - " + responseObject);


    }

    public static void postRequest(HttpEntity<?> entity, String URL) {
        try {
            System.out.println("The postForEntity() method returns a ResponseEntity that can be used to check the response status, headers and body.");
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            System.out.println("responseObject - " + response);
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        } finally {
            //return final response
        }
    }

    private static void requestWithExchange(String URL, HttpEntity<?> httpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        System.out.println("responseObject - " + responseEntity);
    }

    public static HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public class Root {
        public int id;
        public String title;
        public String description;
        public int price;
        public double discountPercentage;
        public double rating;
        public int stock;
        public String brand;
        public String category;
        public String thumbnail;
    }
}
