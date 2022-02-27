package com.example.demo.model.payment;

public class InstructedAmount {
    Double amount;
    String currency;

    public InstructedAmount() {
    }

    public InstructedAmount(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
