package com.it3920.shop.service;

import com.it3920.shop.dto.UserDto;
import com.it3920.shop.entity.User;
import com.it3920.shop.exception.InvalidException;
import com.it3920.shop.exception.NotFoundException;
import com.it3920.shop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder encoder;

    public User createUser(User user){
        Optional<User> userOpt = userRepository.findById(user.getUsername());
        if(userOpt.isPresent())
            throw new InvalidException("Tài khoản đã tồn tại", 1030);
        userRepository.findUserVerifiedPhoneNumber(user.getPhoneNumber(), true)
            .ifPresent(value -> {
                throw new InvalidException(
                        String.format("Số điện thoại %s đã đăng ký cho tài khoản khác", value.getPhoneNumber())
                        , 1032);
            });
        user.setScore(0);
        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setVerifiedPhoneNumber(false);
        return userRepository.save(user);
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUser(String userId){
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại", 1031));
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    public UserDto updateUserInfo(UserDto userDto){
        Optional<User> userOpt = userRepository.findById(userDto.getUsername());
        User user = userOpt.orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại", 1031));

        user.updateInfo(userDto);
        user = userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }
}
