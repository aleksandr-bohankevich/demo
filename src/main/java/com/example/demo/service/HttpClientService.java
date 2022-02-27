package com.example.demo.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class HttpClientService {
    @Value("${service.smartym.payment-url}")
    private String paymentUrl;

    public int sendPayment(String payment, String token) {
        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                CloseableHttpResponse response = sendRequest(httpClient, payment, token)
        ) {
            HttpEntity httpEntity = response.getEntity();

            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
    }

    private CloseableHttpResponse sendRequest(CloseableHttpClient httpClient, String payment, String token) throws IOException {

        StringEntity entity = new StringEntity(payment);

        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        HttpPost request = new HttpPost(paymentUrl);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        request.setEntity(entity);
        return httpClient.execute(request);
    }

}
