package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
