package com.exam.service.Impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;
	
	
	@Override
	public Category addCategory(Category category) {
	return this.catRepo.save(category);
	}

	@Override
	public Category upadateCategory(Category category) {
	return this.catRepo.save(category);
	}

	@Override
	public Set<Category> getCategories() {
	return new LinkedHashSet<>( this.catRepo.findAll());
	}

	@Override
	public Category getCategory(long categoryId) {
		return this.catRepo.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(long categoryId) {
Category category = new Category();
category.setCid(categoryId);
		this.catRepo.delete(category);
		
	}

}
