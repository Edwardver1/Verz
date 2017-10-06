package com.verz.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.verz.domain.Car;
import com.verz.domain.Category;
import com.verz.domain.User;
import com.verz.service.CarService;
import com.verz.service.CategoryService;
import com.verz.service.UserService;

@Controller
public class SearchController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			 @RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		
//		model.addAttribute("category", category);
		Category cat2 = categoryService.findByName(category);
		
		List<Car> carList = carService.findByCategory(cat2);
//		
//		if (carList.isEmpty()) {
//			model.addAttribute("emptyList", true);
//			return "carShelf";
//		}
//		
		model.addAttribute("carList", carList);
		
		Category cat = categoryService.findOne((long) 1);
		model.addAttribute("cat",cat);
		
		return "carShelf";
	}

}
