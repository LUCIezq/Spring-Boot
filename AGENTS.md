# AGENTS.md - Spring Boot Project Guidelines

## Project Overview
Spring Boot 4.0.3 application for managing prices (gestiondeprecios). Java 17.

## Build & Test Commands

### Run Application
```bash
./mvnw spring-boot:run
```

### Build
```bash
./mvnw clean package
```

### Run Tests
```bash
./mvnw test                    # All tests
./mvnw test -Dtest=ClassName   # Single test class
./mvnw test -Dtest=ClassName#methodName  # Single test method
```

### Docker
```bash
docker-compose up -d    # Start MySQL + Adminer
```

## Architecture

### Package Structure
```
com.sastreria.gestiondeprecios/
├── auth/           # Authentication (controller, service, impl, dto)
├── config/         # Configuration classes
├── enums/          # Enumerations (Rol, ErrorCode)
├── exceptions/     # Custom exceptions + GlobalExceptionHandler
├── jwt/            # JWT filter
├── productCategory/  # Feature module (entity, controller, service, impl, repository, dto, mapper)
├── products/      # Products feature
├── users/         # Users feature
└── util/           # Utilities
```

### Layered Architecture
1. **Controller** - REST endpoints, validation, logging
2. **Service (Interface)** - Business logic contract
3. **ServiceImpl** - Business logic implementation with `@Transactional`
4. **Repository** - Spring Data JPA data access
5. **Mapper** - `@Component` converting between DTOs and Entities
6. **DTOs** - Records for request/response

## Code Style Guidelines

### Naming Conventions
- **Classes**: PascalCase (`ProductCategory`, `UserServiceImpl`)
- **Interfaces**: PascalCase (`ProductCategoryService`)
- **Methods**: camelCase (`findByName`, `existsByName`)
- **Variables**: camelCase (`productCategory`, `userRequest`)
- **Packages**: lowercase with dots (`com.sastreria.gestiondeprecios`)
- **Enums**: UPPER_SNAKE_CASE (`ADMIN`, `USER`)
- **DTOs**: PascalCase with suffix `Request` or `Response` (`UserRequest`, `UserResponse`)
- **Mappers**: PascalCase with suffix `Mapper` (`ProductMapper`)

### Java Records for DTOs
```java
// Request DTOs with validation
public record ProductCategoryRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 20, message = "...")
        String name,
        @Size(min = 3, max = 100, message = "...")
        String description
) { }

// Response DTOs with @Builder
@Builder
public record ProductCategoryResponse(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt
) { }
```

### Entities
```java
@Entity
@Table(name = "product_types")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
```

### Controllers
```java
@RestController
@RequestMapping("/v1/product-categories")
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryController {
    private final ProductCategoryService service;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductCategoryResponse> create(
            @Valid @RequestBody ProductCategoryRequest request) {
        log.info("Request para crear producto tipo({})", request.name());
        ProductCategory product = service.save(mapper.toEntity(request));
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(mapper.toDto(product));
    }
}
```

### Service Implementation
```java
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository repository;

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        // Normalize input
        String normalizeName = TextNormalizer.normalize(productCategory.getName());
        if (existsByName(normalizeName)) {
            throw new ProductTypeAlreadyExist("Ya se encuentra una categoria con este nombre");
        }
        productCategory.setName(normalizeName);
        
        try {
            return repository.save(productCategory);
        } catch (DataIntegrityViolationException e) {
            throw new ProductTypeAlreadyExist("...");
        }
    }
}
```

### Mappers
```java
@Component
public class ProductMapper {
    public ProductCategory toEntity(ProductCategoryRequest request) {
        return ProductCategory.builder()
                .name(request.name())
                .description(request.description())
                .active(true)
                .build();
    }

    public ProductCategoryResponse toDto(ProductCategory productCategory) {
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .createdAt(productCategory.getCreatedAt())
                .build();
    }
}
```

### Custom Exceptions
```java
// Custom exception
public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}

// Global exception handler
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFound exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .build());
    }
}
```

### Imports Order (IDE will handle, but be aware)
1. `java.*`
2. `javax.*`
3. Third-party (`jakarta.*`, `org.*`, `com.*`)
4. Project imports

### Lombok Usage
- `@Data`, `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor` for entities
- `@RequiredArgsConstructor` for services, controllers, mappers
- `@Slf4j` for logging in controllers and exception handlers

### Annotations Order on Classes
1. `@Entity`, `@Table`, `@RestController`, etc.
2. `@RequestMapping` or similar mappings
3. Lombok annotations (`@Data`, `@RequiredArgsConstructor`, etc.)
4. `@Slf4j`

### API Versioning
- Use `/v1/` prefix for all endpoints: `/v1/product-categories`

### Validation
- Use Jakarta Validation (`@Valid`) on controller method parameters
- Use record DTOs with constraint annotations for request validation
- Spanish error messages for validation constraints

### Testing
- H2 in-memory database for tests (`application-test.yml`)
- Use `@SpringBootTest` or `@WebMvcTest` as appropriate
