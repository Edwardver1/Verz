package com.verz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.Car;
import com.verz.domain.Category;

public interface CarRepository extends CrudRepository<Car,Long> {
	
	List<Car> findByCategory(Category category);

}
