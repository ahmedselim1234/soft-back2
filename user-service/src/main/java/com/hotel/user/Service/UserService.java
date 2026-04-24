package com.hotel.user.Service;

import com.hotel.user.Model.LoginRequest;
import com.hotel.user.Model.LoginResponse;
import com.hotel.user.Model.ResponseMessage;
import com.hotel.user.Model.User;
import com.hotel.user.Repository.UserRepository;
import com.hotel.user.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Object registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseMessage("Email already registered", null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getUserRole() == null || user.getUserRole().isBlank()) {
            user.setUserRole("GUEST");
        }
        User saved = userRepository.save(user);
        saved.setPassword(null);
        return saved;
    }

    public Object loginUser(LoginRequest request) {
        Optional<User> optUser = userRepository.findByEmail(request.getEmail());
        if (optUser.isEmpty()) {
            return new ResponseMessage("Invalid email or password", null);
        }
        User user = optUser.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseMessage("Invalid email or password", null);
        }
        if (!user.isActive()) {
            return new ResponseMessage("Account is deactivated", null);
        }
        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserRole());
    }

    public Object updateUser(String id, User userDetails) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            return new ResponseMessage("User not found", null);
        }
        User user = optUser.get();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setAddress(userDetails.getAddress());
        if (userDetails.getUserRole() != null && !userDetails.getUserRole().isBlank()) {
            user.setUserRole(userDetails.getUserRole());
        }
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        User saved = userRepository.save(user);
        saved.setPassword(null);
        return saved;
    }

    public Object deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            return new ResponseMessage("User not found", null);
        }
        userRepository.deleteById(id);
        return new ResponseMessage("User deleted successfully", null);
    }

    public Object toggleUserStatus(String id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            return new ResponseMessage("User not found", null);
        }
        User user = optUser.get();
        user.setActive(!user.isActive());
        userRepository.save(user);
        return new ResponseMessage("User status updated", user.isActive());
    }
}
