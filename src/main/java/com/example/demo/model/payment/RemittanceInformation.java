package com.example.demo.model.payment;

public class RemittanceInformation {
    String unstructured;

    public RemittanceInformation() {
    }

    public RemittanceInformation(String unstructured) {
        this.unstructured = unstructured;
    }

    public String getUnstructured() {
        return unstructured;
    }

    public void setUnstructured(String unstructured) {
        this.unstructured = unstructured;
    }
}
