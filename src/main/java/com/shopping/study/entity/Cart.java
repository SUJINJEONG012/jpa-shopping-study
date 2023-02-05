package com.shopping.study.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name="cart")
@Data
public class Cart {
	
	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private Users users;
		
}
