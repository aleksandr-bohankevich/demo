package com.example.demo.model.payment;

public class CreditorAccount {
    String iban;

    public CreditorAccount() {
    }

    public CreditorAccount(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
