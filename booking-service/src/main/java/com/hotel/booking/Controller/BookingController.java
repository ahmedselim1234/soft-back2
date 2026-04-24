package com.hotel.booking.Controller;

import com.hotel.booking.Model.Booking;
import com.hotel.booking.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable String id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable String userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @PostMapping("/")
    public Object createBooking(@Valid @RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public Object updateBooking(@PathVariable String id, @RequestBody Booking bookingDetails) {
        return bookingService.updateBooking(id, bookingDetails);
    }

    @DeleteMapping("/{id}")
    public Object deleteBooking(@PathVariable String id) {
        return bookingService.deleteBooking(id);
    }
}
