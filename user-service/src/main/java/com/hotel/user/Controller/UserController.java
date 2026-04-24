package com.hotel.user.Controller;

import com.hotel.user.Model.LoginRequest;
import com.hotel.user.Model.ResponseMessage;
import com.hotel.user.Model.User;
import com.hotel.user.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public Object registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @PutMapping("/{id}")
    public Object updateUser(@PathVariable String id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}/toggle-status")
    public Object toggleStatus(@PathVariable String id) {
        return userService.toggleUserStatus(id);
    }
}
// - ADMIN                                                                                                             
//   - GUEST                                                                                                             
//   - RECEPTIONIST                                                                                                      
//   - MANAGER 