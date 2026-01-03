# 📊 Data Model & Entity Relationships

## Entity Overview

This application uses **4 main entities** to model the domain:

1. **User** - Registered students/users
2. **Role** - User roles for authorization (USER, ADMIN)
3. **Product** - Rental equipment items
4. **Category** - Equipment categories

## Entity Relationships Diagram

```
┌─────────────┐         ┌──────────────┐
│    User     │────────>│     Role     │
│─────────────│  M:N    │──────────────│
│ id          │         │ id           │
│ username    │         │ name         │
│ email       │         └──────────────┘
│ password    │
│ roles       │
└─────────────┘

┌─────────────┐         ┌──────────────┐
│   Product   │────────>│   Category   │
│─────────────│  M:1    │──────────────│
│ id          │         │ id           │
│ name        │         │ name         │
│ description │         │ description  │
│ price       │         └──────────────┘
│ imageUrl    │
│ available   │
│ category    │
└─────────────┘
```

## Entity Details

### 1. User Entity

**Purpose:** Represents registered students who can rent equipment

**Fields:**
- `id` (Long) - Primary key, auto-generated
- `username` (String) - Unique, 3-50 characters
- `email` (String) - Unique, max 100 characters
- `password` (String) - BCrypt hashed, max 255 characters
- `roles` (Set<Role>) - Many-to-Many relationship

**Relationships:**
- **Many-to-Many** with `Role` via join table `user_roles`

**Security:**
- Password stored as BCrypt hash (never plaintext)
- Username and email must be unique (enforced by database constraints)

### 2. Role Entity

**Purpose:** Defines user roles for authorization (RBAC - Role-Based Access Control)

**Fields:**
- `id` (Long) - Primary key, auto-generated
- `name` (String) - Unique, max 20 characters (e.g., "USER", "ADMIN")
- `users` (Set<User>) - Many-to-Many relationship (inverse side)

**Relationships:**
- **Many-to-Many** with `User` (mapped by `roles` field in User)

**Seeded Roles:**
- `USER` - Default role for registered students
- `ADMIN` - Administrative role (future use)

### 3. Product Entity

**Purpose:** Represents rental equipment items

**Fields:**
- `id` (Long) - Primary key, auto-generated
- `name` (String) - Product name, max 100 characters
- `description` (String) - Product description, max 500 characters
- `price` (BigDecimal) - Daily rental price (precision 10, scale 2)
- `imageUrl` (String) - Image path/URL, max 255 characters
- `available` (Boolean) - Availability status (default: true)
- `category` (Category) - Many-to-One relationship

**Relationships:**
- **Many-to-One** with `Category` (foreign key: `category_id`)

**Business Rules:**
- Price stored as `BigDecimal` for precision (no floating-point errors)
- Available flag allows marking items as unavailable without deletion

### 4. Category Entity

**Purpose:** Organizes products into logical groups

**Fields:**
- `id` (Long) - Primary key, auto-generated
- `name` (String) - Unique category name, max 50 characters
- `description` (String) - Category description, max 255 characters
- `products` (List<Product>) - One-to-Many relationship

**Relationships:**
- **One-to-Many** with `Product` (mapped by `category` field in Product)

**Seeded Categories:**
1. **Lighting** - Stage and studio lighting equipment
2. **Cables** - Audio, video, and power cables
3. **Stage Equipment** - Props, stands, and stage elements
4. **Control Panels** - Mixing desks and control systems

## Design Decisions

### Why Category is an Entity (not Enum)?

**Decision:** Category is a database entity, not a Java enum.

**Justification:**
1. **Flexibility** - Categories can be added/modified without code changes
2. **Database-driven** - Easier to manage via admin interface (future feature)
3. **Scalability** - Supports additional fields (description, icon, sort order)
4. **Dynamic filtering** - Categories loaded from database at runtime
5. **No redeployment** - Adding categories doesn't require application restart

**Alternative (Enum):**
- Would require code changes and redeployment for new categories
- Less flexible for non-technical administrators

### Why Separate Role Entity?

**Decision:** Role is a separate entity with Many-to-Many relationship to User.

**Justification:**
1. **Flexibility** - Users can have multiple roles (e.g., USER + ADMIN)
2. **Extensibility** - Easy to add new roles (MODERATOR, INSTRUCTOR, etc.)
3. **Database normalization** - Avoids data duplication
4. **Spring Security integration** - Roles loaded dynamically for authorization

**Alternative (Enum or String field):**
- Would limit users to single role
- Harder to extend with new roles

### Why BigDecimal for Price?

**Decision:** Product price uses `BigDecimal` instead of `double` or `float`.

**Justification:**
1. **Precision** - No floating-point rounding errors (critical for money)
2. **Accuracy** - Exact decimal representation (e.g., 15.50 stays 15.50)
3. **Industry standard** - Recommended for financial calculations
4. **Database mapping** - Maps to `DECIMAL(10,2)` in database

**Example of float problem:**
```
float price = 15.50f;
float total = price * 3;  // May result in 46.499999 instead of 46.50
```

## Database Seeding

**Class:** `DataSeeder` (implements `CommandLineRunner`)

**Execution:** Runs automatically on application startup

**Seeded Data:**
1. **Roles** (2): USER, ADMIN
2. **Categories** (4): Lighting, Cables, Stage Equipment, Control Panels
3. **Products** (12): 3 products per category
4. **Test User** (1): username=`student`, password=`password123`, role=USER

**Purpose:**
- Provides demo data for testing and evaluation
- Ensures database is never empty on first run
- Demonstrates entity relationships

**Implementation:**
- Checks if data exists before seeding (`if (repository.count() == 0)`)
- Uses repository `save()` methods
- Passwords hashed with BCrypt before saving

## Database Schema (H2)

**Tables Created by JPA:**
- `users` - User accounts
- `roles` - User roles
- `user_roles` - Join table (User ↔ Role)
- `products` - Rental equipment
- `categories` - Equipment categories

**Hibernate DDL Mode:** `create-drop`
- Database recreated on each application restart
- All data lost on shutdown (acceptable for demo/development)
- Production would use `update` or `validate`

