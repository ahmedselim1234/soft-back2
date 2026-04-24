package com.hotel.payment.Controller;

import com.hotel.payment.Model.Payment;
import com.hotel.payment.Model.PaymentRequest;
import com.hotel.payment.Service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable String id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/booking/{bookingId}")
    public Optional<Payment> getPaymentByBookingId(@PathVariable String bookingId) {
        return paymentService.getPaymentByBookingId(bookingId);
    }

    @PostMapping("/")
    public Object processPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }
}
