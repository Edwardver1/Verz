package com.verz.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.verz.domain.helper.ICreditCard;
import com.verz.domain.helper.IPaypal;
import com.verz.domain.helper.PaymentType;

@Entity
public class Payment implements ICreditCard,IPaypal {

	private PaymentType paymentType;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String holderName;
	private String cardNumber;
	private Integer expiryMonth;
	private Integer expiryYear;
	private Integer cvc;
	
	private String paypalEmail;
	private String paypalPassword;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	private Order order;
	
   public Payment(){
	   
   }
   
   public Payment(String holderName, String cardNumber, Integer month , Integer year, Integer cvc){
	   this.holderName = holderName;
	   this.cardNumber = cardNumber;
	   this.expiryMonth = month;
	   this.expiryYear = year;
	   this.cvc = cvc;
   }
   
  

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getCardNumber() {
//		if(PaymentType.Card.equals(paymentType)){
//			return cardNumber;
//		} else{
//			throw new RuntimeException("Your payment type must be Card");
//		}
//	}
//
//	public int getExpiryMonth() {
//		if(PaymentType.Card.equals(paymentType)){
//			return expiryMonth;
//		} else{
//			throw new RuntimeException("Your payment type must be Card");
//		}
//	}
//
//	public int getExpiryYear() {
//		if(PaymentType.Card.equals(paymentType)){
//			return expiryYear;
//		} else{
//			throw new RuntimeException("Your payment type must be Card");
//		}
//	}
//
//	public int getCvc() {
//		if(PaymentType.Card.equals(paymentType)){
//			return cvc;
//		} else{
//			throw new RuntimeException("Your payment type must be Card");
//		}
//	}
//
//
//	public String getHolderName() {
//		if(PaymentType.Card.equals(paymentType)){
//			return holderName;
//		}else{ 
//			throw new RuntimeException("Your payment type must be Card");
//		}
//	}
	
//	public String getEmail() {
//	if(PaymentType.Paypal.equals(paymentType)){
//		return email;
//	}else{ 
//		throw new RuntimeException("Your payment type must be Paypal");
//	}
//}
//
//  public String getPassword() {
//	if(PaymentType.Paypal.equals(paymentType)){
//		return password;
//	} else{
//		throw new RuntimeException("Your payment type must be Paypal");
//	}
//	
//}

	public Order getOrder() {
		return order;
	}

	
	public void setOrder(Order order) {
		this.order = order;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public void becomePaypal(String email, String password) {
		this.cardNumber = null;
		this.cvc = null;
		this.holderName = null;
		this.expiryMonth = null;
		this.expiryYear = null;
		
		this.paypalEmail = email;
		this.paypalPassword = password;
		
		setPaymentType(PaymentType.Paypal);
		
	}

	@Override
	public void becomeCreditCard(String holderName, String cardNumber, Integer expiryMonth, Integer expiryYear,
			Integer CVC) {
		this.cardNumber = cardNumber;
		this.cvc = CVC;
		this.holderName = holderName;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		
		this.paypalEmail = null;
		this.paypalPassword = null;
		
		setPaymentType(PaymentType.Card);
		
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setExpiryMonth(Integer expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public void setExpiryYear(Integer expiryYear) {
		this.expiryYear = expiryYear;
	}

	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}

	public String getHolderName() {
		return holderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Integer getExpiryMonth() {
		return expiryMonth;
	}

	public Integer getExpiryYear() {
		return expiryYear;
	}

	public Integer getCvc() {
		return cvc;
	}

	public String getPaypalEmail() {
		return paypalEmail;
	}

	public void setPaypalEmail(String paypalEmail) {
		this.paypalEmail = paypalEmail;
	}

	public String getPaypalPassword() {
		return paypalPassword;
	}

	public void setPaypalPassword(String paypalPassword) {
		this.paypalPassword = paypalPassword;
	}

	

	
	



	



	
	
	
	
}
