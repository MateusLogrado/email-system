package com.jelly.email_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelly.email_system.entities.Subscription;
import com.jelly.email_system.entities.embeddables.SubscriptionId;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

}
