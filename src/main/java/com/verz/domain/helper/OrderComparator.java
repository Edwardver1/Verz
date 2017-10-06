package com.verz.domain.helper;

import java.util.Comparator;

import com.verz.domain.Order;

public class OrderComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
	
		return o2.getOrderDate().compareTo(o1.getOrderDate());
	}

	

}
