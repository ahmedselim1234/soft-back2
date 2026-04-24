package com.hotel.room.Service;

import com.hotel.room.Model.ResponseMessage;
import com.hotel.room.Model.Room;
import com.hotel.room.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus("AVAILABLE");
    }

    public Object createRoom(Room room) {
        if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
            return new ResponseMessage("Room number already exists", null);
        }
        if (room.getStatus() == null || room.getStatus().isBlank()) {
            room.setStatus("AVAILABLE");
        }
        return roomRepository.save(room);
    }

    public Object updateRoom(String id, Room roomDetails) {
        Optional<Room> optRoom = roomRepository.findById(id);
        if (optRoom.isEmpty()) {
            return new ResponseMessage("Room not found", null);
        }
        Room room = optRoom.get();
        room.setRoomType(roomDetails.getRoomType());
        room.setCapacity(roomDetails.getCapacity());
        room.setPricePerNight(roomDetails.getPricePerNight());
        room.setStatus(roomDetails.getStatus());
        room.setDescription(roomDetails.getDescription());
        room.setAmenities(roomDetails.getAmenities());
        room.setImageUrl(roomDetails.getImageUrl());
        return roomRepository.save(room);
    }

    public Object deleteRoom(String id) {
        if (!roomRepository.existsById(id)) {
            return new ResponseMessage("Room not found", null);
        }
        roomRepository.deleteById(id);
        return new ResponseMessage("Room deleted successfully", null);
    }
}
