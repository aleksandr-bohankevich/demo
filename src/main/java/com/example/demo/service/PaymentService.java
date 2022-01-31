package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.example.demo.dto.PaymentDto;
import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.model.SendTypeEnum;
import com.example.demo.model.payment.*;
import com.example.demo.util.JsonUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);

    @Value("${service.smartym.payment-send-type}")
    private SendTypeEnum sendType;

    private final HttpClientService httpClientService;
    private final RetrofitHttpService retrofitHttpService;

    public PaymentService(
            HttpClientService httpClientService,
            RetrofitHttpService retrofitHttpService) {
        this.httpClientService = httpClientService;
        this.retrofitHttpService = retrofitHttpService;
    }

    public Payment getPaymentForExample() {
        return new Payment(
                new Beneficiary(
                        new Creditor("Test beneficiary"),
                        new CreditorAccount("19831208740321600329487509")),
                LocalDateTime.now(),
                new CreditTransferTransaction(
                        new InstructedAmount(Double.valueOf("100"), "USD"),
                        new RemittanceInformation("Test payment for Smartym"),
                        LocalDate.now()
                ),
                1,
                "1",
                new PaymentTypeInformation("category purpose", "local instrument", "service level")
        );
    }

    public String sendPayment(Payment payment, String token) {
        String redirect;

        switch (sendType) {
            case APACHEHTTP_SYNC:
                redirect = sendApachehttpSync(payment, token);
                break;
            case OKHTTP3_SYNC:
                redirect = sendOkhttpSync(new PaymentDto(payment), token);
                break;
            case OKHTTP3_ASYNC:
                redirect = sendOkhttpAsync(new PaymentDto(payment), token);
                break;
            default:
                redirect = "payment-error";
        }
        return redirect;
    }

    private String sendOkhttpSync(PaymentDto dto, String token) {
        if (retrofitHttpService.sendPaymentSync(dto, token) == HttpStatus.SC_OK) {
            return "payment-success";
        } else {
            return "payment-error";
        }
    }

    private String sendOkhttpAsync(PaymentDto dto, String token) {
        CompletableFuture<Response<PaymentResponseDto>> completableFuture = new CompletableFuture<>();

        retrofitHttpService.sendPaymentAsync(dto, token, completableFuture);

        int result = 500;
        try {
            result = completableFuture.handle(
                    (response, failure) -> {
                        return response.code();
                    }).get();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
        }

        if (result == HttpStatus.SC_OK) {
            return "payment-success";
        } else {
            return "payment-error";
        }
    }

    private String sendApachehttpSync(Payment payment, String token) {
        String json = "";
        try {
            json = JsonUtils.marshal(payment);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

        if (httpClientService.sendPayment(json, token) == HttpStatus.SC_OK) {
            return "payment-success";
        } else {
            return "payment-error";
        }
    }
}
