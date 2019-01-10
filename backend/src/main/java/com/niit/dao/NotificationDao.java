package com.niit.dao;

import java.util.List;

import com.niit.model.Notification;

public interface NotificationDao {

	void addNotification(Notification notification);
	List<Notification> getAllNotificationsNotViewed(String email);
	Notification getNotification(int notificationId);
	void updateNotificactionViewedStatus(int notificationId);
}
