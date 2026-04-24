package com.hotel.payment.Service;

import com.hotel.payment.Model.Payment;
import com.hotel.payment.Model.PaymentRequest;
import com.hotel.payment.Model.ResponseMessage;
import com.hotel.payment.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    public Optional<Payment> getPaymentByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public Object processPayment(PaymentRequest request) {
        // Fake payment — any valid card format always succeeds
        String cardNumber = request.getCardNumber();
        String lastFour = cardNumber.substring(cardNumber.length() - 4);

        Payment payment = new Payment();
        payment.setPaymentNumber("PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        payment.setBookingId(request.getBookingId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setCardLastFour(lastFour);
        payment.setStatus("SUCCESS");
        payment.setPaidAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
