package com.hotel.booking.Repository;

import com.hotel.booking.Model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
    List<Booking> findByRoomId(String roomId);
    List<Booking> findByStatus(String status);
}
