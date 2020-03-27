package com.training.blog.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.blog.model.Author;
import com.training.blog.dao.AuthorDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	// @Autowired
	// private UserInfoService userInfoDAO;

	// @Override
	// public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	// 	UserInfo userInfo = userInfoDAO.getUserInfoByUserName(userName);
	// 	GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
	// 	return new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
	// }

	@Autowired
	private AuthorDao authorDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Author author = authorDao.findByUsername(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
		return new User(author.getUsername(), author.getPassword(), Arrays.asList(authority));
	}
}
