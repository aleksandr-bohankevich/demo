package com.example.demo.dto;

public class PaymentResponseDto {
    private String status;

    public PaymentResponseDto() {
    }

    public PaymentResponseDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
