package com.shopverse.shopverse.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.shopverse.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}

