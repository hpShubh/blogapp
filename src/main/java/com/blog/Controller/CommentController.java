package com.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Service.CommentService;
import com.blog.Service.CommentServiceImpl;
import com.blog.model.Comment;
import com.blog.payload.ApiResponse;
import com.blog.payload.CommentDto;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentServiceImpl;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto comment, @PathVariable Integer postId ){
		CommentDto createcomment=this.commentServiceImpl.createCommen(comment, postId);
		return new ResponseEntity<CommentDto>(createcomment ,HttpStatus.CREATED);
				
	}
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> createComments( @PathVariable Integer commentId ){
		this.commentServiceImpl.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true), HttpStatus.OK);
	}
	
}
