package com.verz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verz.domain.Car;
import com.verz.domain.Category;
import com.verz.repository.CarRepository;
import com.verz.repository.CategoryRepository;
import com.verz.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Car> findAll() {
		List<Car> carList = (List<Car>) carRepository.findAll();
		List<Car> activeCars = new ArrayList<>();
		
		for(Car car: carList){
			if(car.isActive()){
				activeCars.add(car);
			}
		}
		
		
		return activeCars;
	}

	@Override
	public Car findOne(Long id) {
		return carRepository.findOne(id);
	}

	@Override
	public List<Car> findByCategory(Category category) {
//		Category cat = categoryRepository.findByName(category);
		List<Car> carList = (List<Car>) carRepository.findByCategory(category);
		List<Car> activeCars = new ArrayList<>();
		
		for(Car car: carList){
			if(car.isActive()){
				activeCars.add(car);
			}
		}
		
		
		return activeCars;
	}

}
