package com.verz.service;

import java.util.List;

import com.verz.domain.Car;
import com.verz.domain.Category;


public interface CarService {
	
	List<Car> findAll ();
	
	Car findOne(Long id);
	
	List<Car> findByCategory(Category category);

}
