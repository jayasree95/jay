package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.NotificationDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Notification;

@Controller
public class NotificationController {
	@Autowired
	private NotificationDao notificationDao;
	   @RequestMapping(value="/notificationsnotviewed",method=RequestMethod.GET)
		public ResponseEntity<?> getAllNotificationsNotViewed(HttpSession session){
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(6,"Please login...");
	    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
			}
			List<Notification> notificationsNotViewed= notificationDao.getAllNotificationsNotViewed(email);
			return new ResponseEntity<List>(notificationsNotViewed,HttpStatus.OK);
		}
	   @RequestMapping(value="/getnotification/{notificationId}",method=RequestMethod.GET)
	   public ResponseEntity<?> getNotification(@PathVariable int notificationId,HttpSession session){
		   String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(6,"Please login...");
	   		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
			}
			Notification notification=notificationDao.getNotification(notificationId);
			return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	   }
	   @RequestMapping(value="/updatenotification/{notificationId}",method=RequestMethod.PUT)
	   public ResponseEntity<?> updateNotification(@PathVariable int notificationId,HttpSession session){
		   String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(6,"Please login...");
	  		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
			}
			notificationDao.updateNotificactionViewedStatus(notificationId);
			return new ResponseEntity<Void>(HttpStatus.OK);
	   }
	}



