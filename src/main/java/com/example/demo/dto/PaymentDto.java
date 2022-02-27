package com.example.demo.dto;

import com.example.demo.model.payment.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDto {
    @JsonProperty
    private Payment payment;

    public PaymentDto(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
