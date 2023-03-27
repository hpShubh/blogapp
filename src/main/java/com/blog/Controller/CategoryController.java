package com.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Service.CategoryService;
import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto2 = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}

	@PutMapping("/{cid}")
	public ResponseEntity<CategoryDto> UpdateCategory(@RequestBody CategoryDto categorydto, @PathVariable Integer cid) {
		CategoryDto updateCat = this.categoryService.updateCategory(categorydto, cid);
		return new ResponseEntity<CategoryDto>(updateCat, HttpStatus.OK);

	}
	@DeleteMapping("del/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category delete successfully ",true),HttpStatus.OK);
	}
	
	@GetMapping("{catId}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable Integer catId){
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK); 
	}
	
	
	@GetMapping("/getallcat")
	public ResponseEntity<List<CategoryDto>> getallcat(){
		List<CategoryDto> categories=this.categoryService.getallCategory();
		return ResponseEntity.ok(categories);
	}
	
}