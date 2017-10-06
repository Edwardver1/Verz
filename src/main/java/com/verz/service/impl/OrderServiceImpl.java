package com.verz.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verz.domain.Address;
import com.verz.domain.CartItem;
import com.verz.domain.Order;
import com.verz.domain.Payment;
import com.verz.domain.ShoppingCart;
import com.verz.domain.User;
import com.verz.repository.OrderRepository;
import com.verz.service.CartItemService;
import com.verz.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;

	@Override
	public Order createOrder(ShoppingCart shoppingCart, Address shippingAddress, Address billingAddress,
			Payment payment, User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);

		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			cartItem.setOrder(order);
			
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(LocalDate.now());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}

	@Override
	public Order findOne(Long id) {
		
		return orderRepository.findOne(id);
	}

}
