package com.exam.controller;



import com.exam.entity.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	
	//add cAtegory
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category category2 = this.categoryService.addCategory(category);
	return ResponseEntity.ok(category2);
	
	}
	
	
	//getCAtegory
	@GetMapping("/{categoryId}")
	private Category getCategory(@PathVariable("categoryId")long categoryId) {
		return this.categoryService.getCategory(categoryId);
	}
	
	///get aLl Categories
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	
	//update
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.upadateCategory(category);
	}
	
	//delete Categpry
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId")long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
}
