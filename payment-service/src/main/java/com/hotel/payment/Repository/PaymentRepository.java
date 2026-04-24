package com.hotel.payment.Repository;

import com.hotel.payment.Model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    Optional<Payment> findByBookingId(String bookingId);
    List<Payment> findByUserId(String userId);
}
