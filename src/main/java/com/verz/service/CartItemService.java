package com.verz.service;

import java.util.List;

import com.verz.domain.Car;
import com.verz.domain.CartItem;
import com.verz.domain.Order;
import com.verz.domain.ShoppingCart;
import com.verz.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addCarToCartItem(Car car, User user, int days);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
