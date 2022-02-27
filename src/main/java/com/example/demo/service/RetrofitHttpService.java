package com.example.demo.service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import com.example.demo.dto.PaymentDto;
import com.example.demo.dto.PaymentResponseDto;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Service
public class RetrofitHttpService {
    private static final Logger logger = LogManager.getLogger(RetrofitHttpService.class);

    @Value("${service.smartym.payment-base-url}")
    private String paymentUrl;

    private final PaymentStorageService paymentStorageService;

    public RetrofitHttpService(PaymentStorageService paymentStorageService) {
        this.paymentStorageService = paymentStorageService;
    }

    public void sendPaymentAsync(PaymentDto payment, String token, CompletableFuture<Response<PaymentResponseDto>> cf) {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(paymentUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        RetrofitSmartymService service = retrofit.create(RetrofitSmartymService.class);

        Call<PaymentResponseDto> response = service.request(payment);
        response.enqueue(new RfCallbackToCompletableFuture<>(cf, payment.getPayment().getId(), paymentStorageService));
    }

    public int sendPaymentSync(PaymentDto payment, String token) {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(paymentUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        RetrofitSmartymService service = retrofit.create(RetrofitSmartymService.class);

        Call<PaymentResponseDto> call = service.request(payment);
        try {
            Response<PaymentResponseDto> response = call.execute();
            return response.code();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }
}
