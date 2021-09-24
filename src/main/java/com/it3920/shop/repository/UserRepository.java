package com.it3920.shop.repository;

import com.it3920.shop.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("{'phoneNumber' : ?0, 'isVerifiedPhoneNumber': ?1}")
    Optional<User> findUserVerifiedPhoneNumber(String phoneNumber, boolean state);
}
