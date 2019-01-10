package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blogpostlikes_details")
public class BlogPostLikes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int likeId;
@ManyToOne
private BlogPost blogPost;
@ManyToOne	
private User likedBy;
public int getLikeId() {
	return likeId;
}
public void setLikeId(int likeId) {
	this.likeId = likeId;
}
public BlogPost getBlogPost() {
	return blogPost;
}
public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}
public User getLikedBy() {
	return likedBy;
}
public void setLikedBy(User likedBy) {
	this.likedBy = likedBy;
}
}
