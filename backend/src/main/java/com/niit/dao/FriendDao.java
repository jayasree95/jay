package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDao {
	List<User> getAllSuggestedUsers(String email);

	void friendRequest(Friend friend);
	List<Friend> pendingRequests(String email);//email is email id of logged in user
	
	void acceptFriendRequest(Friend friendRequest);

	List<User> listOfFriends(String email);

	void deleteFriendRequest(Friend friend);


}
