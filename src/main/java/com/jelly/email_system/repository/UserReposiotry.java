package com.jelly.email_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelly.email_system.entities.User;

@Repository
public interface UserReposiotry extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	
}
