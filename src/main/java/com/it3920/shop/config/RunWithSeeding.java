package com.it3920.shop.config;

import com.it3920.shop.entity.User;
import com.it3920.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.it3920.shop")
public class RunWithSeeding implements CommandLineRunner {
//    public static void main(String[] args) {
//        SpringApplication.run(com.it3920.shop.config.RunWithSeeding.class, args);
//    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<User> adminOpt =  userRepository.findByUsername("admin");
        if(adminOpt.isEmpty()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFullName("Anh Min");
            admin.setRole("ROLE_ADMIN");
            admin.setPhoneNumber("0999999999");
            admin.setScore(0);
            userRepository.save(admin);
        }
    }
}
