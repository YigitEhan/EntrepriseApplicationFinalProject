# 🔒 Security Implementation

## Spring Security Configuration

This application uses **Spring Security** for authentication and authorization.

### Configuration Class: `SecurityConfig`

The `SecurityConfig` class configures:
1. **Password Encoder** (BCrypt)
2. **Security Filter Chain** (authentication rules)
3. **Form-based Login**
4. **Session Management**

### Key Security Features

#### 1. **BCrypt Password Hashing**

**What is BCrypt?**
- Industry-standard password hashing algorithm
- One-way cryptographic hash function (cannot be reversed)
- Includes automatic salt generation (prevents rainbow table attacks)
- Adaptive cost factor (can increase difficulty as hardware improves)

**Why BCrypt?**
- **Security**: Passwords are never stored in plaintext
- **Salting**: Each password gets a unique salt, preventing identical passwords from having the same hash
- **Slow by design**: Intentionally computationally expensive to prevent brute-force attacks
- **Future-proof**: Cost factor can be increased over time

**How it works in this application:**
```
Registration Flow:
1. User submits password: "password123"
2. UserService calls: passwordEncoder.encode("password123")
3. BCrypt generates: "$2a$10$N9qo8uLOickgx2ZMRZoMye..."
4. Hashed password stored in database
```

**Bean Configuration:**
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

#### 2. **Session-Based Authentication**

**How it works:**
1. User submits login form (`/login` POST)
2. Spring Security intercepts the request
3. `UserService.loadUserByUsername()` retrieves user from database
4. Spring Security compares submitted password with stored BCrypt hash
5. If valid, creates authenticated session with `JSESSIONID` cookie
6. Subsequent requests include session cookie for authentication

**Session Storage:**
- Session ID stored in browser cookie
- Session data (including authentication) stored server-side
- Cart data also stored in session (`HttpSession`)

#### 3. **Authorization Rules**

**Public Routes** (no authentication required):
- `/login` - Login page
- `/register` - Registration page
- `/css/**`, `/images/**` - Static resources
- `/h2-console/**` - H2 database console (development only)

**Protected Routes** (authentication required):
- `/catalog` - Product catalog
- `/cart/**` - Shopping cart operations
- `/checkout/**` - Checkout and confirmation

**Configuration:**
```java
http.authorizeHttpRequests(auth -> auth
    .requestMatchers("/login", "/register", "/css/**", "/images/**").permitAll()
    .anyRequest().authenticated()
)
```

#### 4. **Form Login Configuration**

- Login URL: `/login`
- Login processing URL: `/login` (POST)
- Default success URL: `/catalog`
- Failure URL: `/login?error`
- Logout URL: `/logout`
- Logout success URL: `/login?logout`

#### 5. **CSRF Protection**

- **Enabled** for all state-changing operations (POST, PUT, DELETE)
- Thymeleaf automatically includes CSRF token in forms
- Protects against Cross-Site Request Forgery attacks

#### 6. **User Registration Flow**

1. User fills registration form (`/register`)
2. `AuthController` validates input (username/email uniqueness, password length)
3. `UserService.registerUser()` is called
4. Password is hashed using BCrypt
5. Default "USER" role is assigned
6. User saved to database
7. Redirect to login page

## Verifying Security Implementation

### Check Password Hashing (H2 Console)

**Access H2 Console:**
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:equipmentdb`
- Username: `sa`
- Password: (leave empty)

**Query to verify BCrypt hashing:**
```sql
SELECT id, username, password FROM users;
```

**Expected output:**
```
ID | USERNAME | PASSWORD
1  | student  | $2a$10$N9qo8uLOickgx2ZMRZoMye...
```

The password column should contain a BCrypt hash (starts with `$2a$` or `$2b$`), **never** plaintext.

### Check Role Assignment

**Query to verify user-role relationships:**
```sql
SELECT u.username, r.name AS role
FROM users u
JOIN user_roles ur ON ur.user_id = u.id
JOIN roles r ON r.id = ur.role_id
ORDER BY u.username;
```

**Expected output:**
```
USERNAME | ROLE
student  | USER
```

### Test Authentication

1. Navigate to protected route: `http://localhost:8080/cart`
2. Should redirect to `/login` if not authenticated
3. Login with credentials: `student` / `password123`
4. Should redirect to `/catalog` after successful login
5. Access to `/cart` should now be granted

## Security Best Practices Implemented

✅ **Passwords hashed with BCrypt** (never stored in plaintext)  
✅ **Session-based authentication** (stateful, server-side sessions)  
✅ **CSRF protection enabled** (prevents cross-site attacks)  
✅ **Role-based access control** (USER, ADMIN roles)  
✅ **Secure password validation** (minimum length enforced)  
✅ **Unique username/email constraints** (prevents duplicates)  
✅ **H2 console frame options** (allows embedding for development)

