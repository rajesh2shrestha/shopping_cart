package com.zorba.shoppingCart.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@Setter
@Getter
@ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id", nullable = false, insertable = false)
	private Long custId;
	
	@Column(name="cust_name")
	private String customerName;
	
	@Column(name="mob_number")
	private Long mobileNumber;
	
	@Column(name="country")
	private String country;

	@BatchSize(size=100)
	@OneToMany(mappedBy="customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Items> items = new ArrayList<Items>();


	public void addItems(Items item) {
		this.items.add(item);
		item.setCustomer(this);
	}
	
}

/*
 * Customer Object Items --> memory
 * select * from customer where cust_id = 01;--- 6ms 
 * 
 * */
