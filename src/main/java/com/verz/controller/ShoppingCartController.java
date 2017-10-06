package com.verz.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.verz.domain.Car;
import com.verz.domain.CartItem;
import com.verz.domain.ShoppingCart;
import com.verz.domain.User;
import com.verz.service.CarService;
import com.verz.service.CartItemService;
import com.verz.service.ShoppingCartService;
import com.verz.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}

	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("car") Car car,
			@ModelAttribute("days") String days,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		car = carService.findOne(car.getEngine().getId());
		
		CartItem cartItem = cartItemService.addCarToCartItem(car, user, Integer.parseInt(days));
		model.addAttribute("addCarSuccess", true);
		
		return "forward:/carDetail?id="+car.getId();
	}
	
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(
			@ModelAttribute("id") Long cartItemId,
			@ModelAttribute("days") int days
			) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setDays(days);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
}
