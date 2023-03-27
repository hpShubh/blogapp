package com.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.Repository.UserRepo;
import com.blog.exception.ResourceNotFoundException;
import com.blog.model.User;



@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loding user from db by username
		
		User user=userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "email : "+username, 0 ));
		
		return user;
	}

}
