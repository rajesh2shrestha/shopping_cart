package com.zorba.shoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import com.zorba.shoppingCart.DTO.ItemsDTO;
import com.zorba.shoppingCart.entity.Customer;
import com.zorba.shoppingCart.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.zorba.shoppingCart.DTO.CustomerDTO;
import com.zorba.shoppingCart.service.ShoppingCartService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingcartService;


	@PostMapping("/saveRecords")
	public Boolean saveRecords(@RequestBody List<CustomerDTO> customers) {
		return shoppingcartService.saveRecords(customers);
	}

	@GetMapping(value = "/getAllRecords", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDTO> getAllRecords(){
		return shoppingcartService.fetchRecords();
	}
	@GetMapping(value = "/fetchRecords", produces=MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO fetchRecords(@RequestParam(name="custId") Long custId) {
		return shoppingcartService.fetchItemsSelectedByCustomer(custId);

	}

	@DeleteMapping(value="/deleteRecords")
	public String deleteRecords(@RequestParam(name="custId") Long custId) {
		String status = "";
		try{
			shoppingcartService.deleteCustomerRecords(custId);
			status = "Success";
		}catch(Exception e){
			status = "Failure";
		}
		return status;
	}

	@PutMapping(value = "/updateCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean updateCustomerInfo(@RequestBody CustomerDTO customerDTO, Long custId){
		return shoppingcartService.updateCustomerInfo(customerDTO, custId);
	}

}
