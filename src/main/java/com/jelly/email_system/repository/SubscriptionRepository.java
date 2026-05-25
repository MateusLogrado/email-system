package com.jelly.email_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelly.email_system.entities.Subscription;
import com.jelly.email_system.entities.User;
import com.jelly.email_system.entities.embeddables.SubscriptionId;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

	List<Subscription> findAllByEmpresa(User empresa);
	Optional<Subscription> findByEmail(String email);

}
