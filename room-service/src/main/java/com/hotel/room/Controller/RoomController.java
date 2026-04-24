package com.hotel.room.Controller;

import com.hotel.room.Model.Room;
import com.hotel.room.Service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Optional<Room> getRoomById(@PathVariable String id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @PostMapping("/")
    public Object createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public Object updateRoom(@PathVariable String id, @RequestBody Room roomDetails) {
        return roomService.updateRoom(id, roomDetails);
    }

    @DeleteMapping("/{id}")
    public Object deleteRoom(@PathVariable String id) {
        return roomService.deleteRoom(id);
    }
}
