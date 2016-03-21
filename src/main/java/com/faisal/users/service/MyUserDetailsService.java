package com.faisal.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.faisal.users.dao.UserDao;
import com.faisal.users.model.UserRole;

public class MyUserDetailsService implements UserDetailsService {

	private UserDao userDao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		com.faisal.users.model.User user = userDao.findByUserName(username);

		List<GrantedAuthority> authorities=buildUserAuthority(user.getUserRole());
		
		return buildUserForAuthentication(user, authorities);
		
	}

	private User buildUserForAuthentication(com.faisal.users.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){
		Set<GrantedAuthority> setAuth=new HashSet<GrantedAuthority>();
		
		for(UserRole userRole:userRoles){
			setAuth.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result=new ArrayList<GrantedAuthority>(setAuth);
		
		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
