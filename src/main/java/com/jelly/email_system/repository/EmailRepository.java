package com.jelly.email_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelly.email_system.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
