package com.dincdev.virtualbookstore.controller;

import com.dincdev.virtualbookstore.entity.*;
import com.dincdev.virtualbookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired private CartItemRepository cartItemRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private BookRepository bookRepo;

    @GetMapping
    public List<CartItem> viewCart(Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        return cartItemRepo.findByUser(user);
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Long bookId, @RequestParam int quantity, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        Book book = bookRepo.findById(bookId).orElseThrow();
        CartItem item = new CartItem(null, user, book, quantity);
        return cartItemRepo.save(item);
    }

    @DeleteMapping("/clear")
    public void clearCart(Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        cartItemRepo.deleteByUser(user);
    }
}
