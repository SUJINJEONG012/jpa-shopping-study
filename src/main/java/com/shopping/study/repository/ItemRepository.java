package com.shopping.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shopping.study.entity.Item;
//QuerydslPredicateExecutor<Item>
public interface ItemRepository extends JpaRepository<Item, Long>
{
 
	
	List<Item> findByName(String itemNm); //상품명으로 데이터 조회
	
//	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);// 상품명 또는 상품상세설명을 조회
//	
//	List<Item> findByPriceLessThan(Integer price);//파라미터로 넘어온 price변수보다 값이 작은 상품데이터 조회
//	
//	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); //상품의 가격이 높은 순으로 조회
//	
//	
//	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc, nativeQuery =  true")
//	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
}
