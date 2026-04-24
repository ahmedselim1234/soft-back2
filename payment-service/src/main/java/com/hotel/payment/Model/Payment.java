package com.hotel.payment.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    private String paymentNumber;

    private String bookingId;

    private String userId;

    private double amount;

    private String cardLastFour;

    private String status = "SUCCESS";

    private LocalDateTime paidAt = LocalDateTime.now();

    public Payment() {}

    public Payment(String id, String paymentNumber, String bookingId, String userId,
                   double amount, String cardLastFour, String status, LocalDateTime paidAt) {
        this.id = id;
        this.paymentNumber = paymentNumber;
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
        this.cardLastFour = cardLastFour;
        this.status = status;
        this.paidAt = paidAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPaymentNumber() { return paymentNumber; }
    public void setPaymentNumber(String paymentNumber) { this.paymentNumber = paymentNumber; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCardLastFour() { return cardLastFour; }
    public void setCardLastFour(String cardLastFour) { this.cardLastFour = cardLastFour; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
