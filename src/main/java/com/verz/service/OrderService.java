package com.verz.service;

import com.verz.domain.Address;
import com.verz.domain.Order;
import com.verz.domain.Payment;
import com.verz.domain.ShoppingCart;
import com.verz.domain.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
			Address shippingAddress,
			Address billingAddress,
			Payment payment,
			User user);
	
	Order findOne(Long id);
}
