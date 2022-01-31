package com.example.demo.model.payment;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class Payment {
    Beneficiary beneficiary;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm:ss")
    LocalDateTime creationDateTime;
    CreditTransferTransaction creditTransferTransaction;
    int numberOfTransactions;
    String paymentInformationId;
    PaymentTypeInformation paymentTypeInformation;

    public Payment() {
    }

    public Payment(
            Beneficiary beneficiary,
            LocalDateTime creationDateTime,
            CreditTransferTransaction creditTransferTransaction,
            int numberOfTransactions,
            String paymentInformationId,
            PaymentTypeInformation paymentTypeInformation) {
        this.beneficiary = beneficiary;
        this.creationDateTime = creationDateTime;
        this.creditTransferTransaction = creditTransferTransaction;
        this.numberOfTransactions = numberOfTransactions;
        this.paymentInformationId = paymentInformationId;
        this.paymentTypeInformation = paymentTypeInformation;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public CreditTransferTransaction getCreditTransferTransaction() {
        return creditTransferTransaction;
    }

    public void setCreditTransferTransaction(CreditTransferTransaction creditTransferTransaction) {
        this.creditTransferTransaction = creditTransferTransaction;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public String getPaymentInformationId() {
        return paymentInformationId;
    }

    public void setPaymentInformationId(String paymentInformationId) {
        this.paymentInformationId = paymentInformationId;
    }

    public PaymentTypeInformation getPaymentTypeInformation() {
        return paymentTypeInformation;
    }

    public void setPaymentTypeInformation(PaymentTypeInformation paymentTypeInformation) {
        this.paymentTypeInformation = paymentTypeInformation;
    }
}
