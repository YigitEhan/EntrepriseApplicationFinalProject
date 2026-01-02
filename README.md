# 🎭 Arts Equipment Rental Platform

**University Project - Enterprise Applications Course**  
**Author:** Yigit (University Student)  
**Version:** 1.0.0 (Phase A)

---

## 📋 Project Overview

This is a proof-of-concept web application for an arts education institute that allows registered students to browse, reserve, and "rent" equipment (lights, cables, stage elements, control panels, etc.) for their projects and final works.

### Key Features (Phase A - Completed)
- ✅ Product catalog with 12 seeded products
- ✅ Category-based filtering (4 categories)
- ✅ Clean MVC architecture
- ✅ JPA entities with proper relationships
- ✅ H2 in-memory database
- ✅ Thymeleaf templates
- ✅ Responsive UI design

### Upcoming Features (Phase B)
- 🔜 User registration & login
- 🔜 Session-based authentication with BCrypt
- 🔜 Shopping cart functionality
- 🔜 Checkout & confirmation

---

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

### Test User (for Phase B)
- **Username:** `student`
- **Password:** `password123`
- **Role:** USER

---

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

## 🤖 AI Usage Transparency

This project was developed with assistance from **Augment AI** (Claude Sonnet 4.5) as a learning tool for understanding Enterprise Application development.

### AI-Assisted Components
- ✅ Project structure and architecture design
- ✅ Entity relationship modeling
- ✅ Spring Security configuration patterns
- ✅ Thymeleaf template structure
- ✅ Code documentation and comments

### Student-Owned Understanding
- ✅ MVC pattern and layered architecture
- ✅ JPA entity relationships and annotations
- ✅ Spring Boot dependency injection
- ✅ RESTful controller design
- ✅ Database seeding strategies

**Oral Defense Readiness:** I can explain every line of code, design decision, and architectural choice in this project.

---

## 🎓 Oral Defense Cheat Sheet

### Q: Why did you use BCrypt for password hashing?
**A:** BCrypt is an industry-standard adaptive hashing function designed for passwords. It uses salted hashing to prevent rainbow table attacks and has an adjustable cost factor that can be increased as hardware improves. Unlike MD5 or SHA-1, BCrypt is specifically designed to be slow, making brute-force attacks computationally expensive.

### Q: How does session-based authentication work?
**A:** When a user logs in, Spring Security creates a session on the server and stores the user's authentication details. The server sends a session ID to the client as a cookie (JSESSIONID). On subsequent requests, the client sends this cookie, and Spring Security retrieves the session to authenticate the user. Sessions are stored in memory (default) or can be persisted to a database.

### Q: How is the shopping cart stored?
**A:** The cart is stored in the HttpSession object on the server. Each user's session maintains a cart object containing selected products. This approach is simple, secure (cart data never leaves the server), and doesn't require database persistence for temporary data.

### Q: How does category filtering work?
**A:** The catalog controller accepts an optional `categoryId` query parameter. If provided, it calls `productService.getProductsByCategory()`, which uses Spring Data JPA's query derivation to execute `SELECT * FROM products WHERE category_id = ?`. The filtered products are then passed to the Thymeleaf template for rendering.

### Q: Why separate Controller, Service, and Repository layers?
**A:** This follows the **Separation of Concerns** principle:
- **Controller:** Handles HTTP (request/response), no business logic
- **Service:** Contains business rules, reusable across controllers
- **Repository:** Data access only, abstracts database operations

Benefits: easier testing, maintainability, and scalability.

### Q: Why use JPA instead of raw SQL?
**A:** JPA (Java Persistence API) provides:
- **Object-Relational Mapping (ORM):** Work with Java objects instead of SQL
- **Database independence:** Switch databases without changing code
- **Automatic query generation:** Spring Data JPA creates queries from method names
- **Type safety:** Compile-time checking vs. runtime SQL errors

---

## 📝 License

This is a university project for educational purposes.

---

## 👨‍💻 Author

**Yigit** - University Student  
Enterprise Applications Course - 2024

---

## 🔜 Next Steps (Phase B)

1. Implement user registration with validation
2. Add login/logout functionality
3. Secure routes with Spring Security
4. Implement session-based shopping cart
5. Create checkout and confirmation pages
6. Add integration tests

---

**Last Updated:** Phase A Completion - January 2024

