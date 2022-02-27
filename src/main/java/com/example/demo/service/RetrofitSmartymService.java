package com.example.demo.service;

import com.example.demo.dto.PaymentDto;
import com.example.demo.dto.PaymentResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitSmartymService {
    @Headers("Content-type: application/json")
    @POST("payment-requests")
    Call<PaymentResponseDto> request(@Body PaymentDto payment);
}
