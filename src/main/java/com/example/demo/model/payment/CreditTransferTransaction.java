package com.example.demo.model.payment;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CreditTransferTransaction {
    InstructedAmount instructedAmount;
    RemittanceInformation remittanceInformation;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate requestedExecutionDate;

    public CreditTransferTransaction() {
    }

    public CreditTransferTransaction(
            InstructedAmount instructedAmount,
            RemittanceInformation remittanceInformation,
            LocalDate requestedExecutionDate) {
        this.instructedAmount = instructedAmount;
        this.remittanceInformation = remittanceInformation;
        this.requestedExecutionDate = requestedExecutionDate;
    }

    public InstructedAmount getInstructedAmount() {
        return instructedAmount;
    }

    public void setInstructedAmount(InstructedAmount instructedAmount) {
        this.instructedAmount = instructedAmount;
    }

    public RemittanceInformation getRemittanceInformation() {
        return remittanceInformation;
    }

    public void setRemittanceInformation(RemittanceInformation remittanceInformation) {
        this.remittanceInformation = remittanceInformation;
    }

    public LocalDate getRequestedExecutionDate() {
        return requestedExecutionDate;
    }

    public void setRequestedExecutionDate(LocalDate requestedExecutionDate) {
        this.requestedExecutionDate = requestedExecutionDate;
    }
}
