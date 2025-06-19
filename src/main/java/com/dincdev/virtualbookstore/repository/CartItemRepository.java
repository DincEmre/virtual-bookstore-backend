package com.dincdev.virtualbookstore.repository;

import com.dincdev.virtualbookstore.entity.CartItem;
import com.dincdev.virtualbookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
}
