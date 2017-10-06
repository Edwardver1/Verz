package com.verz.domain.helper;

public interface ICreditCard {

	public void becomeCreditCard(String holderName,String cardNumber,
					Integer expiryMonth,Integer expiryYear, Integer CVC);
	
}
