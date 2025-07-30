package com.amusement.foodmerch.controller;

import com.amusement.foodmerch.model.Order;
import com.amusement.foodmerch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order,
                            @RequestParam(required = false) String couponCode) {
        return orderService.placeOrder(order, couponCode);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam String status) {
        Order order = orderService.getOrderById(id);
        order.setStatus(status);
        return orderService.save(order);
    }
}
