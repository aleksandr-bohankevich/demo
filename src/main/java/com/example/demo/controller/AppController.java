package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import com.example.demo.model.payment.Payment;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PaymentStorageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private final PaymentService paymentService;
    private final PaymentStorageService paymentStorageService;

    public AppController(PaymentService paymentService, PaymentStorageService paymentStorageService) {
        this.paymentService = paymentService;
        this.paymentStorageService = paymentStorageService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/payment-registration")
    public String showPaymentForm(@AuthenticationPrincipal OAuth2User principal, Model model) {
        Map<String, Object> user = Collections.singletonMap("name", principal.getAttribute("name"));
        model.addAttribute("user", user);

        Payment payment = paymentService.getPaymentForExample();
        model.addAttribute("payment", payment);
        return "payment-registration";
    }

    @PostMapping("/payment-send")
    public String submitForm(@ModelAttribute("payment") Payment payment,
                             @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        paymentStorageService.addPayment(payment);
        return paymentService.sendPayment(payment, authorizedClient.getAccessToken().getTokenValue());
    }

    @GetMapping("/payments")
    public String showPayments(@AuthenticationPrincipal OAuth2User principal, Model model) {
        Map<String, Object> user = Collections.singletonMap("name", principal.getAttribute("name"));
        model.addAttribute("user", user);

        model.addAttribute("payments", paymentStorageService.getPayments());
        return "payments";
    }
}
