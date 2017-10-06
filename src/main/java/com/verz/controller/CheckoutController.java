package com.verz.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.verz.domain.Address;
import com.verz.domain.CartItem;
import com.verz.domain.Order;
import com.verz.domain.Payment;
import com.verz.domain.ShoppingCart;
import com.verz.domain.User;
import com.verz.domain.helper.AddressType;
import com.verz.service.CartItemService;
import com.verz.service.OrderService;
import com.verz.service.ShoppingCartService;
import com.verz.service.UserService;
import com.verz.utility.MailConstructor;
import com.verz.utility.PLConstants;




@Controller
public class CheckoutController {
	
	EnumSet<AddressType> shippingType = EnumSet.of(AddressType.Shipping);
	EnumSet<AddressType> billingType = EnumSet.of(AddressType.Billing);

	private Address shippingAddress = new Address(shippingType);
	private Address billingAddress = new Address(billingType);
	private Payment payment = new Payment();

	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy MMMM dd - hh:mm"), true);
	    binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping("/checkout")
	public String checkout(@RequestParam("id") Long cartId,
			@RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField, Model model,
			Principal principal) {
		User user = userService.findByUsername(principal.getName());

		if (cartId != user.getShoppingCart().getId()) {
			return "badRequestPage";
		}

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

		if (cartItemList.size() == 0) {
			model.addAttribute("emptyCart", true);
			return "carShelf";
		}

		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", user.getShoppingCart());

		List<String> stateList = PLConstants.listOfPLStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);

		model.addAttribute("classActiveShipping", true);

		if (missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
		}

		return "checkout";

	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkoutPost(@ModelAttribute("shippingAddress") Address shippingAddress,
			@ModelAttribute("billingAddress") Address billingAddress, @ModelAttribute("payment") Payment payment,
			@ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
		    Principal principal, Model model) {
		ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("cartItemList", cartItemList);
		
		//put required values to billing address
		if (billingSameAsShipping.equals("true")) {
			billingAddress.setRecipientName(shippingAddress.getRecipientName());
			billingAddress.setAddressStreet(shippingAddress.getAddressStreet());
			billingAddress.setAddressCity(shippingAddress.getAddressCity());
			billingAddress.setAddressState(shippingAddress.getAddressState());
			billingAddress.setAddressZipcode(shippingAddress.getAddressZipcode());
		}
		
		//back-end validation address for required values
		if (shippingAddress.getAddressStreet().isEmpty() 
				|| shippingAddress.getAddressCity().isEmpty()
				|| shippingAddress.getAddressState().isEmpty()
				|| shippingAddress.getRecipientName().isEmpty()
				|| shippingAddress.getAddressZipcode().isEmpty()
				|| billingAddress.getAddressStreet().isEmpty()
				|| billingAddress.getAddressCity().isEmpty() 
				|| billingAddress.getAddressState().isEmpty()
				|| billingAddress.getRecipientName().isEmpty()
				|| billingAddress.getAddressZipcode().isEmpty())
			return "redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true";
			
		
		
		User user = userService.findByUsername(principal.getName());
		
		Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, user);
		
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		mailSender.send(mailConstructor.constructNotificationEmail(user, order, Locale.ENGLISH));
		
		return "orderSubmittedPage";
	}

	

	

}
