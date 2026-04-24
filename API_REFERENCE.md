# Hotel Management System — API Reference

## Architecture

| Service         | Port  | Gateway Path   |
|-----------------|-------|----------------|
| API Gateway     | 8080  | —              |
| User Service    | 9000  | `/users/**`    |
| Room Service    | 9001  | `/rooms/**`    |
| Payment Service | 9002  | `/payments/**` |
| Booking Service | 9003  | `/bookings/**` |
| Eureka Server   | 8761  | —              |

**Base URL (via Gateway):** `http://localhost:8080`

**Authentication:** All protected endpoints require a JWT Bearer token:
```
Authorization: Bearer <token>
```

**User Roles:** `ADMIN` | `MANAGER` | `RECEPTIONIST` | `GUEST`

---

## USER SERVICE — `/users`

### POST `/users/register` — Register new user
**Public — no token required**

Request body:
```json
{
  "firstName": "string (required, max 50)",
  "lastName": "string (required, max 50)",
  "email": "string (required, valid email)",
  "password": "string (required, min 8 chars)",
  "phoneNumber": "string (required)",
  "address": "string (required)",
  "userRole": "GUEST | RECEPTIONIST | MANAGER | ADMIN  (default: GUEST)"
}
```

Response:
```json
{ "message": "User registered successfully" }
```

---

### POST `/users/login` — Login
**Public — no token required**

Request body:
```json
{
  "email": "string",
  "password": "string"
}
```

Response:
```json
{
  "token": "JWT string",
  "id": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "userRole": "string"
}
```

---

### GET `/users/` — Get all users
**Requires JWT + ADMIN role**

Response: `Array<User>`

---

### GET `/users/{id}` — Get user by ID
**Requires JWT**

Response: `User object`

---

### PUT `/users/{id}` — Update user
**Requires JWT**

Request body: same fields as register (all optional for update)

Response:
```json
{ "message": "User updated successfully" }
```

---

### PUT `/users/{id}/toggle-status` — Activate / Deactivate user
**Requires JWT + ADMIN role**

Response:
```json
{ "message": "User status updated" }
```

---

### DELETE `/users/{id}` — Delete user
**Requires JWT**

Response:
```json
{ "message": "User deleted successfully" }
```

---

## ROOM SERVICE — `/rooms`

### GET `/rooms/` — Get all rooms
**Requires JWT**

Response: `Array<Room>`

---

### GET `/rooms/available` — Get available rooms only
**Requires JWT**

Response: `Array<Room>` (status = `AVAILABLE`)

---

### GET `/rooms/{id}` — Get room by ID
**Requires JWT**

Response: `Room object`

---

### POST `/rooms/` — Add new room
**Requires JWT (MANAGER / ADMIN)**

Request body:
```json
{
  "roomNumber": "string (required, unique)",
  "roomType": "string (required)",
  "capacity": "integer (required, min 1)",
  "pricePerNight": "number (required, >= 0)",
  "status": "AVAILABLE | OCCUPIED | MAINTENANCE  (default: AVAILABLE)",
  "description": "string (optional)",
  "amenities": ["array of strings"],
  "imageUrl": "string (optional)"
}
```

Response:
```json
{ "message": "Room added successfully" }
```

---

### PUT `/rooms/{id}` — Update room
**Requires JWT (MANAGER / ADMIN)**

Request body: same fields as create

Response:
```json
{ "message": "Room updated successfully" }
```

---

### DELETE `/rooms/{id}` — Delete room
**Requires JWT (MANAGER / ADMIN)**

Response:
```json
{ "message": "Room deleted successfully" }
```

---

## BOOKING SERVICE — `/bookings`

### GET `/bookings/` — Get all bookings
**Requires JWT**

Response: `Array<Booking>`

---

### GET `/bookings/{id}` — Get booking by ID
**Requires JWT**

Response: `Booking object`

---

### GET `/bookings/user/{userId}` — Get bookings by user
**Requires JWT**

Response: `Array<Booking>`

---

### POST `/bookings/` — Create booking
**Requires JWT**

Request body:
```json
{
  "checkInDate": "string (required, e.g. 2025-06-01)",
  "checkOutDate": "string (required, e.g. 2025-06-05)",
  "roomId": "string (required)",
  "userId": "string (required)",
  "extras": "string (optional)"
}
```

Response:
```json
{
  "message": "Booking created successfully",
  "bookingNumber": "string"
}
```

---

### PUT `/bookings/{id}` — Update booking
**Requires JWT**

Request body: same fields as create

Response:
```json
{ "message": "Booking updated successfully" }
```

---

### DELETE `/bookings/{id}` — Cancel booking
**Requires JWT**

Response:
```json
{ "message": "Booking cancelled successfully" }
```

---

## PAYMENT SERVICE — `/payments`

### GET `/payments/` — Get all payments
**Requires JWT**

Response: `Array<Payment>`

---

### GET `/payments/{id}` — Get payment by ID
**Requires JWT**

Response: `Payment object`

---

### GET `/payments/booking/{bookingId}` — Get payment by booking
**Requires JWT**

Response: `Payment object`

---

### POST `/payments/` — Process payment
**Requires JWT**

Request body:
```json
{
  "bookingId": "string (required)",
  "userId": "string (required)",
  "amount": "number (required)",
  "cardNumber": "string (required, 13–19 digits)",
  "expiryDate": "string (required, format: MM/YY)",
  "cvv": "string (required, 3–4 digits)"
}
```

Response:
```json
{
  "message": "Payment successful",
  "paymentNumber": "string",
  "cardLastFour": "string",
  "paidAt": "ISO-8601 timestamp"
}
```

---

## Data Models

### User
```json
{
  "id": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "phoneNumber": "string",
  "address": "string",
  "userRole": "ADMIN | MANAGER | RECEPTIONIST | GUEST",
  "active": "boolean"
}
```

### Room
```json
{
  "id": "string",
  "roomNumber": "string",
  "roomType": "string",
  "capacity": "integer",
  "pricePerNight": "number",
  "status": "AVAILABLE | OCCUPIED | MAINTENANCE",
  "description": "string",
  "amenities": ["string"],
  "imageUrl": "string"
}
```

### Booking
```json
{
  "id": "string",
  "bookingNumber": "string",
  "checkInDate": "string",
  "checkOutDate": "string",
  "days": "integer",
  "roomId": "string",
  "userId": "string",
  "status": "PENDING | CONFIRMED | CANCELLED",
  "totalPrice": "number",
  "extras": "string",
  "createdAt": "ISO-8601 timestamp"
}
```

### Payment
```json
{
  "id": "string",
  "paymentNumber": "string",
  "bookingId": "string",
  "userId": "string",
  "amount": "number",
  "cardLastFour": "string",
  "status": "SUCCESS | FAILED",
  "paidAt": "ISO-8601 timestamp"
}
```

---

## CORS

Allowed origins: `http://localhost:5173`, `http://localhost:3000`  
Credentials: enabled
