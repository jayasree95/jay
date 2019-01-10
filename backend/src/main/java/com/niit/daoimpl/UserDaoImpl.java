package com.niit.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.UserDao;
import com.niit.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
		
		public UserDaoImpl(){
			System.out.println("UserDaoImpl bean is created");
		}
		
		public void registerUser(User user) {
			Session session=sessionFactory.getCurrentSession();
			session.save(user);

		}

		public boolean isEmailValid(String email) {
			//if email is unique -> return true
			//if email already exists in the table -> return false
			//select * from user_s190124 where email=? -> 0 row [true]
			//									          1 row [false]
			
			Session session=sessionFactory.getCurrentSession();
			User user=(User)session.get(User.class,email);
			if(user==null)
				return true;//entered email is unique
			else
				return false;//entered email is duplicate
		}

		public User login(User user) {//user.email='' and user.password=''
			Session session = sessionFactory.getCurrentSession();
			Query query=session.createQuery("from User where email=? and password=?");
			query.setString(0, user.getEmail());
			query.setString(1, user.getPassword());
			return (User)query.uniqueResult();//1 user or null
			//for invalid credentials - return null
			//for valid credentials - return 1 object
		}

		public void update(User user) {
			Session session=sessionFactory.getCurrentSession();
			session.update(user);
			
		}

		public User getUser(String email) {
			Session session=sessionFactory.getCurrentSession();
			User user=(User)session.get(User.class, email);
			return user;
		}

	
}
