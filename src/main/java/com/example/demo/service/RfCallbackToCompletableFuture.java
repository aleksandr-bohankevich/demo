package com.example.demo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.example.demo.model.PaymentStatusEnum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RfCallbackToCompletableFuture<T> implements Callback<T> {
    private final CompletableFuture<Response<T>> cf;
    private Integer paymentId;

    private final PaymentStorageService paymentStorageService;

    public RfCallbackToCompletableFuture(CompletableFuture<Response<T>> cf, Integer id, PaymentStorageService paymentStorageService) {
        this.cf = cf;
        this.paymentId = id;
        this.paymentStorageService = paymentStorageService;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
// for check results
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (response.isSuccessful()) {
            paymentStorageService.setPaymentStatus(paymentId, PaymentStatusEnum.REGISTERED);
        } else {
            paymentStorageService.setPaymentStatus(paymentId, PaymentStatusEnum.ERROR);
        }
        cf.complete(response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        cf.completeExceptionally(t);
    }
}
