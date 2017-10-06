package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.CarToCartItem;
import com.verz.domain.CartItem;

public interface CarToCartItemRepository extends CrudRepository<CarToCartItem,Long> {
	void deleteByCartItem(CartItem cartItem);

}
