package com.dincdev.virtualbookstore.controller;

import com.dincdev.virtualbookstore.entity.*;
import com.dincdev.virtualbookstore.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CartItemRepository cartRepo;
    private final UserRepository userRepo;

    @PostMapping("/checkout")
    public Order checkout(Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        List<CartItem> cartItems = cartRepo.findByUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            BigDecimal price = item.getBook().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            orderItem.setPrice(price);
            order.getItems().add(orderItem);
            total = total.add(price);
        }

        order.setTotalPrice(total);
        orderRepo.save(order);
        orderItemRepo.saveAll(order.getItems());

        cartRepo.deleteByUser(user);
        return order;
    }

    @GetMapping("/my")
    public List<Order> getMyOrders(Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        return orderRepo.findByUser(user);
    }
}
