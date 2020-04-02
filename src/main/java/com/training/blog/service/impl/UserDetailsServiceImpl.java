package com.training.blog.service.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.blog.model.Author;
import com.training.blog.dao.AuthorDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthorDao authorDao;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorDao.findByUsername(username);
		if (author == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(author.getUsername(), author.getPassword(), getAuthority());
    }

    private Collection<? extends GrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }

}
