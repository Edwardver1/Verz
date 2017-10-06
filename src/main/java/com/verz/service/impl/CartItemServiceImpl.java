package com.verz.service.impl;


import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verz.domain.Car;
import com.verz.domain.CarToCartItem;
import com.verz.domain.CartItem;
import com.verz.domain.Order;
import com.verz.domain.ShoppingCart;
import com.verz.domain.User;
import com.verz.repository.CarToCartItemRepository;
import com.verz.repository.CartItemRepository;
import com.verz.service.CartItemService;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CarToCartItemRepository carToCartItemRepository;
	

	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		 
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal price = new BigDecimal (cartItem.getCar().getPrice()).multiply(new BigDecimal(cartItem.getDays()));
		
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		cartItem.setSubtotal(price);
		
		return cartItem;
	}

	@Override
	public CartItem addCarToCartItem(Car car, User user, int days) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if(car.getId() == cartItem.getCar().getId()) {
				cartItem.setDays(cartItem.getDays() + days);
				cartItem.setSubtotal(new BigDecimal(car.getPrice()).multiply(new BigDecimal(days)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setCar(car);
		
		cartItem.setDays(days);
		cartItem.setSubtotal(new BigDecimal(car.getPrice()).multiply(new BigDecimal(days)));
		cartItem = cartItemRepository.save(cartItem);
		
		CarToCartItem carToCartItem = new CarToCartItem();
		carToCartItem.setCar(car);
		carToCartItem.setCartItem(cartItem);
		carToCartItemRepository.save(carToCartItem);
		
		return cartItem;
	}

	@Override
	public CartItem findById(Long id) {
		return cartItemRepository.findOne(id);
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		carToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
		
	}

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> findByOrder(Order order) {
	
		return cartItemRepository.findByOrder(order);
	}
}
