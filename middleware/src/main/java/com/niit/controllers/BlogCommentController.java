package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.BlogCommentDao;
import com.niit.dao.UserDao;
import com.niit.model.BlogComment;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class BlogCommentController {

	@Autowired
	private BlogCommentDao blogCommentDao;
		@Autowired
	private UserDao userDao;
		
		@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
		//create a blogcomment and set the values for two  properties "commentTxt" and "blogPost" in frontend
		public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
			String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(6,"Please login...");
	    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
			}
			blogComment.setCommentedOn(new Date());
			User commentedBy=userDao.getUser(email);
			blogComment.setCommentedBy(commentedBy);
			blogCommentDao.addBlogComment(blogComment);
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
	   @RequestMapping(value="/blogcomments/{blogPostId}",method=RequestMethod.GET)
	   public ResponseEntity<?> getAllBlogComments(@PathVariable int blogPostId,HttpSession session){
		   String email=(String)session.getAttribute("email");
			//NOT LOGGED IN
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(6,"Please login...");
	   		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
			}
			List<BlogComment> blogComments=blogCommentDao.getAllBlogComments(blogPostId);
			
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	   }
	
}
