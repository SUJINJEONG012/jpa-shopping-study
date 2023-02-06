package com.shopping.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.study.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
