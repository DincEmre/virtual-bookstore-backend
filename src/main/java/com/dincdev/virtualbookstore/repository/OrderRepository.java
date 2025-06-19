package com.dincdev.virtualbookstore.repository;

import com.dincdev.virtualbookstore.entity.Order;
import com.dincdev.virtualbookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
