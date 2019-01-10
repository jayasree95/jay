package com.niit.dao;

import java.util.List;

import com.niit.model.BlogPost;

public interface BlogPostDao {
	void addBlogPost(BlogPost blogPost);
	List<BlogPost> getBlogsWaitingForApproval();
	List<BlogPost> getBlogsApproved();
	BlogPost getBlog(int blogId);
	void approveBlogPost(int blogPostId);
	void updateBlogPost(BlogPost blogPost);
	void rejectblogPost(BlogPost blogPost);
}
