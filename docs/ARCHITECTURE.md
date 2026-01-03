# 🏗️ Application Architecture

## Overview
This application follows a **layered MVC architecture** with clear separation of concerns:

```
Browser → Controller → Service → Repository → Database
   ↑          ↓
   └─── View (Thymeleaf)
```

## Layers

### 1. **Controller Layer** (Presentation)
- Handles HTTP requests and responses
- Maps URLs to handler methods (`@GetMapping`, `@PostMapping`)
- Delegates business logic to Service layer
- Prepares data for views using Spring `Model`
- Examples: `CatalogController`, `CartController`, `CheckoutController`, `AuthController`

### 2. **Service Layer** (Business Logic)
- Contains application business rules
- Orchestrates operations across multiple repositories
- Marked with `@Service` and `@Transactional`
- Examples: `ProductService`, `CategoryService`, `CartService`, `UserService`

### 3. **Repository Layer** (Data Access)
- Interfaces extending `JpaRepository<Entity, ID>`
- Provides CRUD operations automatically
- Custom query methods using Spring Data naming conventions
- Examples: `ProductRepository`, `CategoryRepository`, `UserRepository`, `RoleRepository`

### 4. **Model Layer** (Domain Entities)
- JPA entities representing database tables
- Annotated with `@Entity`, `@Table`, `@Column`
- Define relationships: `@ManyToOne`, `@OneToMany`, `@ManyToMany`
- Examples: `Product`, `Category`, `User`, `Role`

## Request Flow Example: Viewing Catalog

1. **User** navigates to `/catalog?categoryId=1`
2. **CatalogController** receives request via `@GetMapping`
3. **Controller** calls `CategoryService.getCategoryById(1)` to get category
4. **Controller** calls `ProductService.getProductsByCategory(category)` to get filtered products
5. **ProductService** calls `ProductRepository.findByCategory(category)`
6. **Repository** executes JPA query against H2 database
7. **Service** returns `List<Product>` to Controller
8. **Controller** adds products and categories to `Model`
9. **Thymeleaf** renders `catalog.html` with model data
10. **Browser** receives HTML response

## Session-Based Shopping Cart

The shopping cart uses **HttpSession** to store cart data:
- Cart stored as `Map<Long, Integer>` (productId → quantity)
- Session attribute key: `"shopping_cart"`
- Managed by `CartService` which wraps session operations
- Cart persists across requests until checkout or session expiry
- Uses `LinkedHashMap` to preserve insertion order

## Key Design Patterns

- **MVC Pattern**: Separation of Controller, Model, View
- **Repository Pattern**: Data access abstraction via Spring Data JPA
- **Service Layer Pattern**: Business logic encapsulation
- **Dependency Injection**: Constructor injection via `@Autowired` or Lombok `@RequiredArgsConstructor`
- **Session State Pattern**: Shopping cart stored in HTTP session

