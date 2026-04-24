package com.hotel.booking.Service;

import com.hotel.booking.Model.Booking;
import com.hotel.booking.Model.ResponseMessage;
import com.hotel.booking.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Object createBooking(Booking booking) {
        booking.setBookingNumber("BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    public Object updateBooking(String id, Booking bookingDetails) {
        Optional<Booking> optBooking = bookingRepository.findById(id);
        if (optBooking.isEmpty()) {
            return new ResponseMessage("Booking not found", null);
        }
        Booking booking = optBooking.get();
        booking.setCheckInDate(bookingDetails.getCheckInDate());
        booking.setCheckOutDate(bookingDetails.getCheckOutDate());
        booking.setDays(bookingDetails.getDays());
        booking.setStatus(bookingDetails.getStatus());
        booking.setTotalPrice(bookingDetails.getTotalPrice());
        booking.setExtras(bookingDetails.getExtras());
        return bookingRepository.save(booking);
    }

    public Object deleteBooking(String id) {
        if (!bookingRepository.existsById(id)) {
            return new ResponseMessage("Booking not found", null);
        }
        bookingRepository.deleteById(id);
        return new ResponseMessage("Booking cancelled successfully", null);
    }
}
