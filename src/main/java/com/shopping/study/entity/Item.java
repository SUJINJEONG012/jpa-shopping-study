package com.shopping.study.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.shopping.study.constant.ItemStatus;

import lombok.Data;

@Data
@Entity
@Table(name="item")
public class Item {

	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //상품아이디 
	
	@Column(nullable = false, length = 50)
	private String itemNm; //상품
	
	@Column(name="price" , nullable = false)
	private int price; //상품가
	
	@Column(nullable = false)
	private int stockNumber; //재고
	
	@Lob
	@Column(nullable = false)
	private String itemDetail; //상품상세설명
	
	@Enumerated(EnumType.STRING)
	private ItemStatus itemStatus; //상품판매상태 
	
	private LocalDateTime regTime; //등록시간
	private LocalDateTime updateTime; //수정시간
}
