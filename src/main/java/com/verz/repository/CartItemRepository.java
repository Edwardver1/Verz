package com.verz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.CartItem;
import com.verz.domain.Order;
import com.verz.domain.ShoppingCart;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<CartItem> findByOrder(Order order);
}
