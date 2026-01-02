# 🚀 Quick Start Guide - Arts Equipment Rental

## Prerequisites Check

Before running the application, ensure you have:

### 1. Java 17 or Higher
```bash
# Check Java version
java -version

# Should output something like:
# java version "17.0.x" or higher
```

If Java is not installed:
- **Windows:** Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
- **Mac:** `brew install openjdk@17`
- **Linux:** `sudo apt install openjdk-17-jdk`

### 2. Maven (Optional - we include Maven Wrapper)
```bash
# Check Maven version
mvn -version

# Should output Maven 3.6+ (optional)
```

---

## 🏃 Running the Application

### Method 1: Maven Wrapper (Recommended - No Maven Installation Needed)

**Windows:**
```cmd
mvnw.cmd clean spring-boot:run
```

**Linux/Mac:**
```bash
chmod +x mvnw
./mvnw clean spring-boot:run
```

### Method 2: Installed Maven

```bash
mvn clean spring-boot:run
```

### Method 3: VS Code

1. Open the project folder in VS Code
2. Install the "Extension Pack for Java" extension
3. Open `src/main/java/com/artsrental/equipment/EquipmentRentalApplication.java`
4. Click the "Run" button above the `main` method
5. Or press `F5` to start debugging

### Method 4: IntelliJ IDEA

1. Open the project in IntelliJ
2. Wait for Maven to download dependencies
3. Right-click on `EquipmentRentalApplication.java`
4. Select "Run 'EquipmentRentalApplication'"

---

## ✅ Verify Application is Running

You should see output like:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.1)

...
✓ Roles seeded
✓ Categories seeded
✓ Products seeded (12 products)
✓ Test user created (username: student, password: password123)
...
Started EquipmentRentalApplication in X.XXX seconds
```

---

## 🌐 Access the Application

Once running, open your browser and navigate to:

### Main Application
- **Catalog Page:** http://localhost:8080/
- **Catalog (explicit):** http://localhost:8080/catalog

### H2 Database Console
- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:equipmentdb`
- **Username:** `sa`
- **Password:** *(leave empty)*

---

## 🧪 Testing the Application

### Test Category Filtering

1. Go to http://localhost:8080/catalog
2. Select a category from the dropdown (e.g., "Lighting")
3. Click "Apply Filter"
4. You should see only products from that category
5. Click "Clear" to show all products again

### Test Database Console

1. Go to http://localhost:8080/h2-console
2. Enter connection details (see above)
3. Click "Connect"
4. Run SQL queries:

```sql
-- View all products
SELECT * FROM PRODUCTS;

-- View all categories
SELECT * FROM CATEGORIES;

-- View products with category names
SELECT p.name, p.price, c.name as category 
FROM PRODUCTS p 
JOIN CATEGORIES c ON p.category_id = c.id;

-- View users
SELECT * FROM USERS;

-- View roles
SELECT * FROM ROLES;
```

---

## 🛑 Stopping the Application

- **Terminal:** Press `Ctrl + C`
- **VS Code/IntelliJ:** Click the red stop button

---

## 🐛 Troubleshooting

### Issue: "Port 8080 is already in use"

**Solution 1:** Stop the process using port 8080
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Solution 2:** Change the port in `application.properties`
```properties
server.port=8081
```

### Issue: "JAVA_HOME not set"

**Windows:**
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%
```

**Linux/Mac:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
```

### Issue: Maven dependencies not downloading

**Solution:**
```bash
# Clear Maven cache and rebuild
mvnw.cmd clean install -U

# Or with installed Maven
mvn clean install -U
```

### Issue: "Cannot find symbol" compilation errors

**Solution:** Ensure Lombok is properly configured in your IDE

**VS Code:**
1. Install "Lombok Annotations Support" extension
2. Reload window

**IntelliJ:**
1. Install Lombok plugin
2. Enable annotation processing: Settings → Build → Compiler → Annotation Processors → Enable

---

## 📦 Building a JAR File

To create a standalone JAR file:

```bash
# Using Maven Wrapper
mvnw.cmd clean package

# Using installed Maven
mvn clean package
```

The JAR will be created in `target/equipment-rental-1.0.0.jar`

Run the JAR:
```bash
java -jar target/equipment-rental-1.0.0.jar
```

---

## 🔄 Hot Reload (Development)

Spring Boot DevTools is included for automatic restart on code changes.

**How it works:**
1. Make changes to Java files
2. Save the file
3. Application automatically restarts (faster than manual restart)

**Note:** Template changes (Thymeleaf) are reflected immediately without restart.

---

## 📊 Project Health Check

After starting the application, verify:

- ✅ Application starts without errors
- ✅ Seeding messages appear in console
- ✅ Catalog page loads at http://localhost:8080
- ✅ 12 products are displayed
- ✅ Category filter dropdown shows 4 categories
- ✅ Filtering works correctly
- ✅ H2 console is accessible

---

## 🎓 For Oral Defense

Be prepared to:
1. **Run the application** from scratch
2. **Demonstrate filtering** functionality
3. **Show the H2 console** and explain the database schema
4. **Explain the code** in any file when asked
5. **Describe the architecture** (MVC, layered design)

---

## 📞 Need Help?

If you encounter issues:
1. Check the console output for error messages
2. Verify Java version: `java -version`
3. Ensure port 8080 is available
4. Check `application.properties` configuration
5. Review the logs in the terminal

---

**Good luck with your project! 🎓**

