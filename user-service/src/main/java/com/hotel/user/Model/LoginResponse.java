package com.hotel.user.Model;

public class LoginResponse {
    private String token;
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userRole;

    public LoginResponse() {}

    public LoginResponse(String token, String id, String firstName, String lastName, String email, String userRole) {
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}
