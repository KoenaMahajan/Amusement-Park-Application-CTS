package com.amusement.foodmerch.repository;

import com.amusement.foodmerch.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
