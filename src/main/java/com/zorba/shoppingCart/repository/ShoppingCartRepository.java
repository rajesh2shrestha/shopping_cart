package com.zorba.shoppingCart.repository;

import org.springframework.data.repository.CrudRepository;

import com.zorba.shoppingCart.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<Customer, Long> {


}
