package com.it3920.shop.controller;

import com.it3920.shop.entity.Order;
import com.it3920.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Secured("ROLE_ADMIN")
    @GetMapping("")
    public ResponseEntity<?> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrders(@Valid @RequestBody Order order){
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update-state")
    public ResponseEntity<?> updateOrders(@Valid @RequestBody Order order){
        Order updatedOrder = orderService.update(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/cancel/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable String id){
        Order cancelOrder = orderService.cancel(id);
        return ResponseEntity.ok(cancelOrder);
    }

}
