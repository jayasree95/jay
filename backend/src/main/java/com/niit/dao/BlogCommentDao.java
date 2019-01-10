package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface BlogCommentDao {
	
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getAllBlogComments(int blogPostId);

}
