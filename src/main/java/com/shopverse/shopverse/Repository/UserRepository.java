package com.shopverse.shopverse.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByPhoneAndPassword(String phone, String password);
    Optional<User> findByUsernameAndPassword(String username, String password);
}

