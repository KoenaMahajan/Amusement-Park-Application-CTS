package com.amusement.foodmerch.repository;

import com.amusement.foodmerch.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
