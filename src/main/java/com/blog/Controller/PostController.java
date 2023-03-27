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

import com.blog.Service.PostService;
import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createpost2 = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createpost2, HttpStatus.CREATED);
	}

	// get by user
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userid) {
		List<PostDto> posts = this.postService.getPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// getPost by category
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDto>> getPostsBycategory(@PathVariable Integer categoryid) {
		List<PostDto> posts = this.postService.getPostByCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get all post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getallposts() {
		List<PostDto> allpost = this.postService.getallpost();
		return new ResponseEntity<List<PostDto>>(allpost, HttpStatus.OK);

	}

	// get by id
	@GetMapping("/post/{postid}")
	public ResponseEntity<PostDto> getpostbyid(@PathVariable Integer postid) {
		PostDto postDto = postService.getPostbyid(postid);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	// delete by id
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletepost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("post deleted !!", true);
	}
	
	//update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
	PostDto updatepost=this.postService.updatepost(postDto, postId);
	return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
		
	}
	
	//seaech
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDto>> seachbytitle(@PathVariable("keywords")String keywords){
		List<PostDto> result=this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);			
	}
	
	
}
