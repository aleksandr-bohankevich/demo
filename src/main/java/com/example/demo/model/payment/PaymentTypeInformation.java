package com.example.demo.model.payment;

public class PaymentTypeInformation {
    String categoryPurpose;
    String localInstrument;
    String serviceLevel;

    public PaymentTypeInformation() {
    }

    public PaymentTypeInformation(String categoryPurpose, String localInstrument, String serviceLevel) {
        this.categoryPurpose = categoryPurpose;
        this.localInstrument = localInstrument;
        this.serviceLevel = serviceLevel;
    }

    public String getCategoryPurpose() {
        return categoryPurpose;
    }

    public void setCategoryPurpose(String categoryPurpose) {
        this.categoryPurpose = categoryPurpose;
    }

    public String getLocalInstrument() {
        return localInstrument;
    }

    public void setLocalInstrument(String localInstrument) {
        this.localInstrument = localInstrument;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
}
