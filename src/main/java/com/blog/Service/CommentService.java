package com.blog.Service;

import com.blog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createCommen(CommentDto commentDto ,Integer postId);
	void deleteComment(Integer commentId);
}
