package com.verz.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verz.domain.Car;
import com.verz.domain.User;
import com.verz.service.CarService;
import com.verz.service.UserService;


@Controller
public class HomeController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarService carService;
		

	@RequestMapping("/")
	public String carShelf(Model model){
		return "redirect:/carShelf";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("classActiveLogin", true);
		return "myAccount";
	}
	
	@RequestMapping("/carShelf")
	public String carShelf(Model model, Principal principal) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Car> carList = carService.findAll();
		model.addAttribute("carList", carList);
		model.addAttribute("activeAll",true);
		
		return "carShelf";
	}
	
	
	@RequestMapping("/carDetail")
	public String carDetail(
			@PathParam("id") Long id, Model model, Principal principal
			) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		Car car = carService.findOne(id);
		
		model.addAttribute("car", car);
		
		List<Integer> daysList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		model.addAttribute("daysList", daysList);
		model.addAttribute("days", 1);
		
		return "carDetail";
	}
	
}
