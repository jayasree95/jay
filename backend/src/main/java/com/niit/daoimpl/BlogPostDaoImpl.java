package com.niit.daoimpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogPostDao;
import com.niit.model.BlogPost;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {
	@Autowired
	private SessionFactory sessionFactory;
		public void addBlogPost(BlogPost blogPost) {
			Session session=sessionFactory.getCurrentSession();
			session.save(blogPost);
		}
		public List<BlogPost> getBlogsWaitingForApproval() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from BlogPost where approved=false");
			List<BlogPost> blogPostsWaitingForApproval=query.list();
			return blogPostsWaitingForApproval;
		}
		public List<BlogPost> getBlogsApproved() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from BlogPost where approved=true");
			List<BlogPost> blogPostsApproved=query.list();
			return blogPostsApproved;
		}
		public BlogPost getBlog(int blogId) {
			Session session=sessionFactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class,blogId);
			return blogPost;
		}
		public void approveBlogPost(int blogPostId) {
			Session session=sessionFactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogPostId);
			blogPost.setApproved(true);
			session.update(blogPost);
		}
		
		public void updateBlogPost(BlogPost blogPost) {
			Session session=sessionFactory.getCurrentSession();
			blogPost.setApproved(false);
			session.update(blogPost);
		}
		public void rejectblogPost(BlogPost blogPost) {
			
		     Session session=sessionFactory.getCurrentSession();
		     session.delete(blogPost);
				
		}
			
		}

	

	
