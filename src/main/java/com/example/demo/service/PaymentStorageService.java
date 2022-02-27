package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.PaymentStatusEnum;
import com.example.demo.model.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentStorageService {
    private List<Payment> payments = Collections.synchronizedList(new ArrayList<>());

    public List<Payment> getPayments() {
        return payments;
    }

    public synchronized void addPayment(Payment payment) {
        payment.setId(payments.size() + 1);
        payment.setStatus(PaymentStatusEnum.CREATE);
        payments.add(payment);
    }

    public synchronized void setPaymentStatus(Integer id, PaymentStatusEnum status) {
        Optional<Payment> payment = payments.stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
        if (!payment.isEmpty()) {
            payment.get().setStatus(status);
        }
    }
}
