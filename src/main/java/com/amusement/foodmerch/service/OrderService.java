package com.amusement.foodmerch.service;

import com.amusement.foodmerch.model.*;
import com.amusement.foodmerch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private FoodItemRepository foodRepo;
    @Autowired private MerchandiseItemRepository merchRepo;
    @Autowired private CouponRepository couponRepo;

    public Order placeOrder(Order order, String couponCode) {
        double total = 0.0;
        for (Long id : order.getItemIds()) {
            if ("food".equals(order.getOrderType())) {
                total += foodRepo.findById(id).get().getPrice();
            } else {
                total += merchRepo.findById(id).get().getPrice();
            }
        }
        if (couponCode != null) {
            Coupon coupon = couponRepo.findByCode(couponCode);
            if (coupon != null && coupon.isActive()) {
                total -= total * (coupon.getDiscountPercentage() / 100);
            }
        }
        order.setTotalAmount(total);
        order.setStatus("placed");
        return orderRepo.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }
}
