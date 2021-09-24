//package com.it3920.shop.config;
//
//import com.it3920.shop.entity.User;
//import com.it3920.shop.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        Optional<User> adminOpt =  userRepository.findByUsername("admin");
//        if(adminOpt.isEmpty()){
//            User admin = new User();
//            admin.setUsername("admin");
//            admin.setPassword(passwordEncoder.encode("admin"));
//            admin.setFullName("Ad Min");
//            admin.setRole("ROLE_ADMIN");
//            userRepository.save(admin);
//        }
//    }
//}
