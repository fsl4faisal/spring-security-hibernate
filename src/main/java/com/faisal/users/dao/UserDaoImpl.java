package com.faisal.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.faisal.users.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();
		
		System.out.println("Here is text above users");
		users = getSessionFactory().getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username).list();
		
		System.out.println("Here is the text after users:"+users.get(0).getUsername()+" "+users.get(0).getPassword());
		if(users.size()>0){
			return users.get(0);
		}
		else{
			return null;
		}
	}
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	

}
