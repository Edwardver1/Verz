package com.verz.service;

import java.util.List;

import com.verz.domain.Category;



public interface CategoryService {
	
	List<Category> findAll ();
	
	Category findOne(Long id);
	
	Category findByName(String name);

}
