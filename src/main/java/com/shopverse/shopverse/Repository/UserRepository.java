package com.shopverse.shopverse.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
}

