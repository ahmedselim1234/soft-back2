package com.hotel.booking.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String bookingNumber;

    @NotBlank(message = "Check-in date is required")
    private String checkInDate;

    @NotBlank(message = "Check-out date is required")
    private String checkOutDate;

    private int days;

    @NotBlank(message = "Room ID is required")
    private String roomId;

    @NotBlank(message = "User ID is required")
    private String userId;

    private String status = "PENDING";

    private double totalPrice;

    private String extras;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Booking() {}

    public Booking(String id, String bookingNumber, String checkInDate, String checkOutDate,
                   int days, String roomId, String userId, String status, double totalPrice,
                   String extras, LocalDateTime createdAt) {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.days = days;
        this.roomId = roomId;
        this.userId = userId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.extras = extras;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBookingNumber() { return bookingNumber; }
    public void setBookingNumber(String bookingNumber) { this.bookingNumber = bookingNumber; }

    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getExtras() { return extras; }
    public void setExtras(String extras) { this.extras = extras; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
