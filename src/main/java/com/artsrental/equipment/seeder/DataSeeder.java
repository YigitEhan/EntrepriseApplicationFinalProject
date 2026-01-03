package com.artsrental.equipment.seeder;

import com.artsrental.equipment.model.Category;
import com.artsrental.equipment.model.Product;
import com.artsrental.equipment.model.Role;
import com.artsrental.equipment.model.User;
import com.artsrental.equipment.repository.CategoryRepository;
import com.artsrental.equipment.repository.ProductRepository;
import com.artsrental.equipment.repository.RoleRepository;
import com.artsrental.equipment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Data seeder to populate the database on application startup.
 * 
 * Seeds:
 * - 4 Categories (Lighting, Cables, Stage Equipment, Control Panels)
 * - 10+ Products across all categories
 * - 2 Roles (USER, ADMIN)
 * - 1 Test user for development
 * 
 * Runs automatically when the application starts (CommandLineRunner).
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedCategories();
        seedProducts();
        seedUsers();
    }
    
    private void seedRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));
            System.out.println("✓ Roles seeded");
        }
    }
    
    private void seedCategories() {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("Lighting", "Stage and studio lighting equipment"));
            categoryRepository.save(new Category("Cables", "Audio, video, and power cables"));
            categoryRepository.save(new Category("Stage Equipment", "Props, stands, and stage elements"));
            categoryRepository.save(new Category("Control Panels", "Mixing desks and control systems"));
            System.out.println("✓ Categories seeded");
        }
    }
    
    private void seedProducts() {
        if (productRepository.count() == 0) {
            Category lighting = categoryRepository.findByName("Lighting").orElseThrow();
            Category cables = categoryRepository.findByName("Cables").orElseThrow();
            Category stage = categoryRepository.findByName("Stage Equipment").orElseThrow();
            Category control = categoryRepository.findByName("Control Panels").orElseThrow();
            
            // Lighting products (3)
            productRepository.save(new Product(
                "LED Par Light 64",
                "Professional RGB LED par light with DMX control",
                new BigDecimal("15.00"),
                "/images/lighting.svg",
                lighting
            ));

            productRepository.save(new Product(
                "Fresnel Spotlight 1000W",
                "Classic theater spotlight with barn doors",
                new BigDecimal("25.00"),
                "/images/spotlight.svg",
                lighting
            ));

            productRepository.save(new Product(
                "Moving Head Light",
                "Intelligent moving head with gobo patterns",
                new BigDecimal("45.00"),
                "/images/projector.svg",
                lighting
            ));

            // Cables products (3)
            productRepository.save(new Product(
                "XLR Cable 10m",
                "Professional balanced audio cable",
                new BigDecimal("5.00"),
                "/images/microphone.svg",
                cables
            ));

            productRepository.save(new Product(
                "DMX Cable 5m",
                "5-pin DMX cable for lighting control",
                new BigDecimal("4.00"),
                "/images/mixer.svg",
                cables
            ));

            productRepository.save(new Product(
                "Power Extension 20m",
                "Heavy-duty power extension cable",
                new BigDecimal("8.00"),
                "/images/speaker.svg",
                cables
            ));

            // Stage Equipment products (3)
            productRepository.save(new Product(
                "Microphone Stand",
                "Adjustable boom microphone stand",
                new BigDecimal("6.00"),
                "/images/tripod.svg",
                stage
            ));

            productRepository.save(new Product(
                "Speaker Stand Pair",
                "Heavy-duty adjustable speaker stands (pair)",
                new BigDecimal("12.00"),
                "/images/easel.svg",
                stage
            ));

            productRepository.save(new Product(
                "Fog Machine",
                "Professional fog machine with remote control",
                new BigDecimal("30.00"),
                "/images/screen.svg",
                stage
            ));

            // Control Panels products (3)
            productRepository.save(new Product(
                "DMX Controller 512",
                "512-channel DMX lighting controller",
                new BigDecimal("40.00"),
                "/images/canvas.svg",
                control
            ));

            productRepository.save(new Product(
                "Audio Mixer 16-Channel",
                "Professional 16-channel mixing desk",
                new BigDecimal("50.00"),
                "/images/mixer.svg",
                control
            ));

            productRepository.save(new Product(
                "Lighting Console",
                "Advanced lighting control console with touchscreen",
                new BigDecimal("75.00"),
                "/images/camera.svg",
                control
            ));

            System.out.println("✓ Products seeded (12 products)");
        }
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Role userRole = roleRepository.findByName("USER").orElseThrow();

            User testUser = new User();
            testUser.setUsername("student");
            testUser.setEmail("student@arts.edu");
            testUser.setPassword(encoder.encode("password123"));
            testUser.getRoles().add(userRole);
            userRepository.save(testUser);

            System.out.println("✓ Test user created (username: student, password: password123)");
        }
    }
}

