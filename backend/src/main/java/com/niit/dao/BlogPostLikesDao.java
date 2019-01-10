package com.niit.dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;

public interface BlogPostLikesDao {

	BlogPostLikes hasUserLikedBlogPost(int blogPostId,String email);//email is loggedin user's email id
    BlogPost updateLikes(int blogPostId, String email);
	
}

