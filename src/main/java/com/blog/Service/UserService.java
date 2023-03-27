package com.blog.Service;


import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {
	UserDto createuser(UserDto user);
	UserDto updateUser(UserDto user,Integer userid);
	UserDto getUserbyid(int userid);
	List<UserDto> getAlluser();
	void deleteuser(int userid);
}
