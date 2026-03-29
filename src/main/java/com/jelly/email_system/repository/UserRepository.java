package com.jelly.email_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelly.email_system.entities.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
        
        List<User> findAllByEmailIn(List<String> emails);
	
}
