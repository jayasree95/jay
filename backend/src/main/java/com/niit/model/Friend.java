package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend_detail")
public class Friend {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int friendId;
	@ManyToOne
private User fromId;
	@ManyToOne
private User toId;
private char status;
public int getFriendId() {
	return friendId;
}
public void setFriendId(int friendId) {
	this.friendId = friendId;
}
public User getFromId() {
	return fromId;
}
public void setFromId(User fromId) {
	this.fromId = fromId;
}
public User getToId() {
	return toId;
}
public void setToId(User toId) {
	this.toId = toId;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}
	
	
	
}
