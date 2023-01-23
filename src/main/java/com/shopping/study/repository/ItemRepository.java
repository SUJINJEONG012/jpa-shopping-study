package com.shopping.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.study.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
