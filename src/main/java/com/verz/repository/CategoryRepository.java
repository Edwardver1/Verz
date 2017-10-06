package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.Category;


public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Category findByName(String name);

}
