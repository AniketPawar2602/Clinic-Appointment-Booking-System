## 🏥 Clinic Appointment Booking System

This is a **Spring Boot-based Clinic Appointment Booking Application** that allows patients to book appointments with doctors by selecting available time slots. It ensures no double booking for the same doctor and slot on a given date.

---

### 📌 Features

- 📅 Book appointments with available time slots  
- 👨‍⚕️ Doctor and 👨‍🦰 Patient entities with relationships  
- 🔁 Prevent double booking of time slots  
- 🗂️ Use of DTOs and layered architecture  
- 🧪 Easily testable endpoints using Postman  

---

### ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Lombok (optional)**
- **Postman (for API testing)**

---

### 🗃️ Database Structure

- `Doctor`
- `Patient`
- `TimeSlot` → Predefined slots like 9:00 - 9:30, 9:30 - 10:00, etc.
- `Appointment` → Mapped to Doctor, Patient, TimeSlot, and Date

---

### 📥 API Endpoints (Example)

> ⚡ *The following endpoints and request/response formats are examples to help you understand how to interact with the system. You can customize them as needed in your implementation.*

---

#### 🔹 **Appointment**

- **Book Appointment**

```http
POST /appointment/book-appointment
```

**Request Body Example:**
```json
{
  "doctorId": 1,
  "patientId": 2,
  "slotId": 3,
  "appointmentDate": "2025-04-15"
}
```

- **Get All Appointments**

```http
GET /appointment/getAll
```

**Returns:**  
List of all booked appointments.

---

#### 🔹 **Doctor**

- **Create Doctor**

```http
POST /doctor/create
```

**Request Body Example:**
```json
{
  "name": "Dr. John Doe",
  "specialization": "Cardiologist",
  "contactNumber": "1234567890",
  "email": "john.doe@example.com"
}
```

- **Get Doctor by ID**

```http
GET /doctor/{doctorId}
```

- **Update Doctor Information**

```http
PUT /doctor/{doctorId}
```

**Request Body Example:**
```json
{
  "name": "Dr. John Smith",
  "specialization": "Cardiologist",
  "contactNumber": "0987654321",
  "email": "john.smith@example.com"
}
```

- **Delete Doctor**

```http
DELETE /doctor/{doctorId}
```

---

#### 🔹 **Patient**

- **Create Patient**

```http
POST /patient/create
```

**Request Body Example:**
```json
{
  "firstName": "Alice",
  "lastName": "Johnson",
  "dob": "1985-08-15",
  "email": "alice.johnson@example.com",
  "phone": "9876543210"
}
```

- **Get Patient by ID**

```http
GET /patient/{patientId}
```

- **Update Patient Information**

```http
PUT /patient/{patientId}
```

**Request Body Example:**
```json
{
  "firstName": "Alice",
  "lastName": "Johnson",
  "dob": "1985-08-15",
  "email": "alice.johnson@newemail.com",
  "phone": "9999999999"
}
```

- **Delete Patient**

```http
DELETE /patient/{patientId}
```

---

### 🧾 Sample TimeSlot Insert Query

```sql
INSERT INTO time_slot (start_time, end_time) VALUES
('09:00:00', '09:30:00'),
('09:30:00', '10:00:00'),
('10:00:00', '10:30:00'),
('10:30:00', '11:00:00'),
('11:00:00', '11:30:00'),
('11:30:00', '12:00:00'),
('12:00:00', '12:30:00'),
('12:30:00', '13:00:00');
```

---

### 🛠️ How to Run

1. Clone the repository:
```bash
git clone https://github.com/AniketPawar2602/Clinic-Appointment-Booking-System.git
```

2. Configure your **`application.properties`**:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinic_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

4. Test the APIs using Postman at:  
`http://localhost:8080/...`

---

### 🙋 Author

**Aniket Pawar**  
🔗 [LinkedIn](https://www.linkedin.com/in/aniket-pawar-2b8587271/)
