 package com.verz.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false,updatable = false)
	private Long id;
	private int days;
	private BigDecimal subtotal;
	
	@OneToOne
	private Car car;
	
	@OneToMany(mappedBy = "cartItem")
	@JsonIgnore
	private List<CarToCartItem> carToCartItemList;
	
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	public CartItem(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public List<CarToCartItem> getCarToCartItemList() {
		return carToCartItemList;
	}

	public void setCarToCartItemList(List<CarToCartItem> carToCartItemList) {
		this.carToCartItemList = carToCartItemList;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
