package com.blog.Service;

import java.util.List;

import com.blog.model.Post;
import com.blog.payload.PostDto;

public interface PostService {

	
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatepost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getallpost();
	PostDto getPostbyid(Integer postid);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userid);
	List<PostDto> searchPosts(String keyword);
	
}
