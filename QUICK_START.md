# ⚡ Quick Start - Arts Equipment Rental

**For the impatient developer who just wants to run the app!**

---

## 🚀 3-Step Quick Start

### Step 1: Ensure Java 17+ is Installed
```bash
java -version
# Should show version 17 or higher
```

### Step 2: Build the Project
```bash
# Windows
.\mvnw.cmd clean package -DskipTests

# Linux/Mac
./mvnw clean package -DskipTests
```

### Step 3: Run the Application
```bash
java -jar target/equipment-rental-1.0.0.jar
```

### Step 4: Open Browser
```
http://localhost:8080
```

**That's it! 🎉**

---

## 🧪 Quick Test

1. **Catalog Page:** http://localhost:8080/catalog
   - Should show 12 products
   - Filter dropdown should have 4 categories

2. **H2 Console:** http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:equipmentdb`
   - Username: `sa`
   - Password: *(empty)*

---

## 🐛 Quick Troubleshooting

| Problem | Solution |
|---------|----------|
| Port 8080 in use | Change port in `application.properties`: `server.port=8081` |
| Java not found | Install Java 17 from https://adoptium.net/ |
| Maven not found | Use Maven wrapper: `.\mvnw.cmd` (Windows) or `./mvnw` (Linux/Mac) |
| Build fails | Run: `.\mvnw.cmd clean install -U` |

---

## 📚 Full Documentation

- **Complete Guide:** [README.md](README.md)
- **Running Instructions:** [RUN_INSTRUCTIONS.md](RUN_INSTRUCTIONS.md)
- **Project Structure:** [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md)
- **Phase A Completion:** [PHASE_A_COMPLETION.md](PHASE_A_COMPLETION.md)
- **Git Setup:** [GIT_SETUP_GUIDE.md](GIT_SETUP_GUIDE.md)

---

## 🎓 For Oral Defense

**Key points to remember:**

1. **MVC Pattern:** Controller → Service → Repository
2. **Why BCrypt?** Secure password hashing with salt
3. **Category as Entity?** Flexibility for future changes
4. **How filtering works?** Spring Data JPA query derivation
5. **Session-based auth?** Server stores session, client gets cookie

**Full cheat sheet in:** [README.md](README.md#-oral-defense-cheat-sheet)

---

## 📦 What's Included (Phase A)

- ✅ 4 Entities (User, Role, Product, Category)
- ✅ 4 Repositories with custom queries
- ✅ 2 Services (Product, Category)
- ✅ 1 Controller (Catalog with filtering)
- ✅ 1 Thymeleaf template (Catalog page)
- ✅ Database seeding (4 categories, 12 products)
- ✅ H2 console enabled
- ✅ Spring Security configured
- ✅ Complete documentation

---

## 🔜 Coming in Phase B

- User registration & login
- Session-based authentication
- Shopping cart functionality
- Checkout & confirmation
- Protected routes

---

**Happy coding! 🎨**

