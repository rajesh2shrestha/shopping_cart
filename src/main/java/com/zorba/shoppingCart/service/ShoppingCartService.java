package com.zorba.shoppingCart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.zorba.shoppingCart.DTO.CustomerDTO;
import com.zorba.shoppingCart.DTO.ItemsDTO;
import com.zorba.shoppingCart.entity.Customer;
import com.zorba.shoppingCart.entity.Items;
import com.zorba.shoppingCart.repository.ShoppingCartRepository;



@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public Boolean saveRecords(List<CustomerDTO> customerDTOList) {

		for (CustomerDTO customerDTO : customerDTOList) {
			Customer customer = new Customer();
			customer.setCustomerName(customerDTO.getCustName());
			customer.setMobileNumber(customerDTO.getMobileNumber());
			customer.setCountry(customerDTO.getCountry());


			List<ItemsDTO> itemsDTOList = customerDTO.getItems();
			for (ItemsDTO iterating : itemsDTOList) {
				Items items = new Items();
				items.setItemName(iterating.getName());
				items.setItemQuantity(iterating.getQuantity());
				customer.addItems(items);
			}
			shoppingCartRepository.save(customer);
		}
		return true;
	}
	public List<CustomerDTO> fetchRecords(){
		Iterable<Customer> customerList = shoppingCartRepository.findAll();
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		Iterator iterator = customerDTOList.iterator();
		while(iterator.hasNext()){
			Customer customer = (Customer)iterator.next();
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustName(customer.getCustomerName());
			customerDTO.setMobileNumber(customer.getMobileNumber());
			customerDTO.setCountry(customer.getCountry());

			List<ItemsDTO> itemsDTOList = new ArrayList<>();
			for(Items items : customer.getItems()){
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setName(items.getItemName());
				itemsDTO.setQuantity(items.getItemQuantity());
				itemsDTOList.add(itemsDTO);
			}
			customerDTO.setItems(itemsDTOList);
			customerDTOList.add(customerDTO);
		}

		return customerDTOList;
	}

	public boolean updateCustomerInfo(CustomerDTO customerDTO, Long custId){
		Optional<Customer> customerOptional = shoppingCartRepository.findById(custId);
		if(customerOptional != null){
			Customer customer = customerOptional.get();
			customer.setCustomerName(customerDTO.getCustName());
			customer.setMobileNumber(customerDTO.getMobileNumber());
			customer.setCountry(customer.getCountry());

			List<ItemsDTO> itemsDTOList = customerDTO.getItems();
			for(ItemsDTO itemsDTO : itemsDTOList){
				Items items = new Items();
				items.setItemName(items.getItemName());
				items.setItemQuantity(items.getItemQuantity());
			}
			shoppingCartRepository.save(customer);
		}
		return true;

	}
	public CustomerDTO fetchItemsSelectedByCustomer(Long custId) {
		Customer customer = new Customer();
		CustomerDTO customerDTO = new CustomerDTO();
		Optional<Customer> customerOptional = shoppingCartRepository.findById(custId);
		if(customerOptional != null)
		{
			customer = customerOptional.get();
			customerDTO.setCustName(customer.getCustomerName());
			customerDTO.setMobileNumber(customer.getMobileNumber());
			customerDTO.setCountry(customer.getCountry());
			List<ItemsDTO> itemsDTOList = new ArrayList<>();
			for(Items items: customer.getItems()){
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setName(items.getItemName());
				itemsDTO.setQuantity(items.getItemQuantity());
				itemsDTOList.add(itemsDTO);
			}
			customerDTO.setItems(itemsDTOList);
		}
		return customerDTO;
	}

	public void deleteCustomerRecords(Long custId) {
		shoppingCartRepository.deleteById(custId);
	}
}
