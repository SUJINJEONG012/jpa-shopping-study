package com.shopping.study.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cart_item")
public class CartItem {
	@Id
	@GeneratedValue
	@Column(name="cart_item_id")
	private Long id;
	
	//하나의 장바구니에는 여러개의 상품을 담을 수 있으므로 @ManyToOne으로 다대일 관계맵핑 설정  
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	/*
	 * 장바구니에 담을 상품의 정보를 알아야 하므로 상품 엔티티를 매핑해준다. 
	 * 하나의 상품은 여러개의 장바구니의 장바구니에 상품으르 담길 수 있으므로 
	 * 마찬가지로 @ManyToOne 어노테이션으로 다대일관계로 매핑 
	 * */
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	private int count; //같은상품을 몇개 담을지 저장
}
