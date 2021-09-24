package com.it3920.shop.controller;

import com.it3920.shop.dto.Role;
import com.it3920.shop.dto.UserDto;
import com.it3920.shop.entity.User;
import com.it3920.shop.repository.UserRepository;
import com.it3920.shop.security.CustomUserDetail;
import com.it3920.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/role")
    public ResponseEntity<?> getRole(Principal principal){
        String username = principal.getName();
        String role = userService.getUser(username).getRole().replace("ROLE_", "");
        return ResponseEntity.ok(new Role(role));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/update-info")
    public ResponseEntity<?> updateUserInfo(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUserInfo(userDto));
    }
}
