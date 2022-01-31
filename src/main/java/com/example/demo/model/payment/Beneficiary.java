package com.example.demo.model.payment;

public class Beneficiary {
    Creditor creditor;
    CreditorAccount creditorAccount;

    public Beneficiary() {
    }

    public Beneficiary(Creditor creditor, CreditorAccount creditorAccount) {
        this.creditor = creditor;
        this.creditorAccount = creditorAccount;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
    }

    public CreditorAccount getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(CreditorAccount creditorAccount) {
        this.creditorAccount = creditorAccount;
    }
}
