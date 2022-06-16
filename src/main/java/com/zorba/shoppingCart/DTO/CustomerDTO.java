package com.zorba.shoppingCart.DTO;

import com.zorba.shoppingCart.entity.Items;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@ToString
public class CustomerDTO {

	private String custName;
	private Long mobileNumber;
	private String country;
	private List<ItemsDTO> items;

}
