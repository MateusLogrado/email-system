package com.jelly.email_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelly.email_system.entities.EmailStatus;
import com.jelly.email_system.entities.embeddables.EmailStatusId;

@Repository
public interface EmailStatusRepository extends JpaRepository<EmailStatus, EmailStatusId> {

}
