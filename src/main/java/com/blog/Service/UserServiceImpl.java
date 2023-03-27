package com.blog.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.UserRepo;
import com.blog.exception.ResourceNotFoundException;
import com.blog.model.User;
import com.blog.payload.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	
	// import from the main class  
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createuser(UserDto userDto) {
		User user = this.dtotoUser(userDto);
		User saveuser = this.userRepo.save(user);
		return this.userToDto(saveuser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) {

		User user = this.userRepo.findById(userid)
				.orElseThrow((() -> new ResourceNotFoundException("User", "id", userid)));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);

		return userDto1;
	}

	@Override
	public UserDto getUserbyid(int userid) {
		
		User user = this.userRepo.findById(userid)
				.orElseThrow((() -> new ResourceNotFoundException("User", "id", userid)));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		 
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteuser(int userid) {
		 User user=this.userRepo.findById(userid).orElseThrow((() -> new ResourceNotFoundException("User", "id", userid)));
		 this.userRepo.delete(user);
	}

	private User dtotoUser(UserDto userDto) {
		User user = this.modelmapper.map(userDto, User.class);
		
		
		
//		user.setId(user.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;

	}

	private UserDto userToDto(User user) {

		UserDto userDto = this.modelmapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getEmail());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
