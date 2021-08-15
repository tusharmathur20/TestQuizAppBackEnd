package com.exam.service;

import java.util.Set;

import com.exam.entity.exam.Category;

public interface CategoryService {

	public Category addCategory (Category category);
	public Category upadateCategory(Category category);
	public Set<Category> getCategories();
	public Category getCategory(long categoryId);
	
public  void deleteCategory(long categoryId);
}
