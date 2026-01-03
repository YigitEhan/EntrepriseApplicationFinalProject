# 🎭 Arts Equipment Rental Platform

**University Project - Enterprise Applications Course**  
**Author:** Yigit (ehb Student)  
**Version:** 1.0.0 ()

---

## 📋 Project Overview

## Overview
This project is a proof-of-concept web application developed for the course **Enterprise Applications**.
It simulates a platform for an arts education institute where registered students can browse technical equipment
and reserve items for projects via a shopping cart and checkout confirmation flow.

The focus of this application is on:
- data display and filtering
- secure authentication
- session handling
- reservation flow (cart → confirmation)

No real payments are implemented.

---


### Key Features 

- Product catalog with category filtering
- User registration and secure login (Spring Security)
- Password hashing using BCrypt
- Session-based shopping cart
- Checkout and confirmation page
- Clean MVC architecture (Controller / Service / Repository)

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java 17** | Programming language |
| **Spring Boot 3.2.1** | Application framework |
| **Spring MVC** | Web layer (Controller pattern) |
| **Spring Data JPA** | Data persistence (ORM) |
| **Spring Security** | Authentication & authorization |
| **Thymeleaf** | Server-side template engine |
| **H2 Database** | In-memory database |
| **Maven** | Build & dependency management |
| **Lombok** | Boilerplate code reduction |

---

## 📁 Project Structure

```
src/main/java/com/artsrental/equipment/
├── EquipmentRentalApplication.java    # Main application entry point
├── config/
│   └── SecurityConfig.java            # Spring Security configuration
├── model/
│   ├── User.java                      # User entity (M:N with Role)
│   ├── Role.java                      # Role entity (RBAC)
│   ├── Product.java                   # Product entity (M:1 with Category)
│   └── Category.java                  # Category entity (1:N with Product)
├── repository/
│   ├── UserRepository.java            # User data access
│   ├── RoleRepository.java            # Role data access
│   ├── ProductRepository.java         # Product data access (with filtering)
│   └── CategoryRepository.java        # Category data access
├── service/
│   ├── ProductService.java            # Product business logic
│   └── CategoryService.java           # Category business logic
├── controller/
│   └── CatalogController.java         # Catalog & filtering controller
└── seeder/
    └── DataSeeder.java                # Database seeding on startup

src/main/resources/
├── application.properties             # Application configuration
├── templates/
│   └── catalog.html                   # Product catalog page
└── static/
    └── css/
        └── style.css                  # Custom styles
```

---

## 🚀 How to Run the Project

### Prerequisites
- **Java 17** or higher installed
- **Maven 3.6+** installed (or use the included Maven wrapper)
- **IDE:** Visual Studio Code (recommended) or IntelliJ IDEA

### Option 1: Using Maven Wrapper (Recommended)

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Option 2: Using Installed Maven

```bash
mvn clean install
mvn spring-boot:run
```

### Option 3: Using IDE
1. Open the project in VS Code or IntelliJ
2. Run the `EquipmentRentalApplication.java` main class
3. The application will start on `http://localhost:8080`

---

## 🌐 Accessing the Application

Once the application is running:

- **Catalog Page:** http://localhost:8080/catalog
- **H2 Database Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:equipmentdb`
  - Username: `sa`
  - Password: *(leave empty)*

---

## 📊 Seeded Data

The application automatically seeds the database on startup with:

### Categories (4)
1. **Lighting** - Stage and studio lighting equipment
2. **Cables** - Audio, video, and power cables
3. **Stage Equipment** - Props, stands, and stage elements
4. **Control Panels** - Mixing desks and control systems

### Products (12)
- **Lighting:** LED Par Light 64, Fresnel Spotlight 1000W, Moving Head Light
- **Cables:** XLR Cable 10m, DMX Cable 5m, Power Extension 20m
- **Stage Equipment:** Microphone Stand, Speaker Stand Pair, Fog Machine
- **Control Panels:** DMX Controller 512, Audio Mixer 16-Channel, Lighting Console

### Test User 
- **Username:** `student`
- **Password:** `password123`
- **Role:** USER

---

## Verification & Testing (for Evaluation)

### 1. User Registration & Password Security
Execute the following SQL query:
```sql
SELECT username, password FROM users;
```

### 2. Role Assignment & Authorization
```sql
SELECT u.username, r.name
FROM users u
JOIN user_roles ur ON ur.user_id = u.id
JOIN roles r ON r.id = ur.role_id
ORDER BY u.username;
``` 

### 3. Authentication & Protected Routes
http://localhost:8080/cart
http://localhost:8080/checkout
http://localhost:8080/confirmation
``
## 🏗️ Architecture & Design Decisions

### MVC Pattern
- **Controller** → Handles HTTP requests, delegates to Service
- **Service** → Contains business logic, calls Repository
- **Repository** → Data access layer (Spring Data JPA)

### Entity Relationships
```
User ←→ Role (Many-to-Many)
Product → Category (Many-to-One)
Category ← Product (One-to-Many)
```

### Why Category is an Entity (not Enum)?
- **Flexibility:** Categories can be added/modified without code changes
- **Database-driven:** Easier to manage via admin interface (future)
- **Scalability:** Supports category descriptions, icons, etc.

### Why BCrypt for Passwords?
- **Industry standard** for password hashing
- **Salted hashing** prevents rainbow table attacks
- **Adaptive cost factor** can be increased as hardware improves
- **One-way function** - passwords cannot be decrypted

---

## 📚 References & Learning Resources

### Official Documentation
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)

### Tutorials Used
- Baeldung: Spring Boot + Thymeleaf Tutorial
- Spring.io Guides: Building a RESTful Web Service
- Mkyong: Spring Security Tutorial

---

## 🤖 AI Usage

This project was developed with assistance from **chatgpt**  as a learning tool for understanding Enterprise Application development.

### AI-Assisted Components
- ✅ Spring Security configuration patterns
- ✅ Code documentation and comments
- ✅ Project documentation and README


## 📝 License

This is a university project for educational purposes.

---

## 👨‍💻 Author

**Yigit** - Ehb Student  
Enterprise Applications- 2024

---

## 🔜 Possible Future Enhancements 
- **Persist reservations in the database** (e.g., `Reservation` + `ReservationItem`) so users can view their reservation history.
- **Date range reservations & availability checking** to prevent double-booking of the same equipment.
- **Admin dashboard** to manage products/categories, approve reservations, and mark items as returned.
- **Inventory management** (stock count, maintenance status, damaged/repair workflows).
- **Email notifications** for reservation confirmation, pickup reminders, and return deadlines.
- **Search & advanced filters** (keyword search, price range, availability, sorting).
- **User profiles** (edit profile, change password, view past confirmations).
- **Improved UI/UX** (better responsiveness, accessibility improvements, and more product images/details).
---

**Last Updated:** january 2026

