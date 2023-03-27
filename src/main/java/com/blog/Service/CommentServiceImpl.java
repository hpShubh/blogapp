package com.blog.Service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.CommentRepo;
import com.blog.Repository.PostRepo;
import com.blog.exception.ResourceNotFoundException;
import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.payload.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createCommen(CommentDto commentDto, Integer postId) {
		Post post = this.postrepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postv id ", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postv id ", commentId));
		this.commentRepo.delete(com);
	}

}
