package com.verz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verz.domain.Category;
import com.verz.repository.CategoryRepository;
import com.verz.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		
		
		return categories;
	}

	@Override
	public Category findOne(Long id) {
		
		return categoryRepository.findOne(id);
	}

	@Override
	public Category findByName(String name) {
		
		return categoryRepository.findByName(name);
	}
	
	
}
