package com.dincdev.virtualbookstore.repository;

import com.dincdev.virtualbookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
