package com.blog.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Service.UserServiceImpl;
import com.blog.payload.ApiResponse;
import com.blog.payload.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	
	//create the user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createuserdto = userServiceImpl.createuser(userDto);
		return new ResponseEntity<>(createuserdto, HttpStatus.CREATED);
	}

	
	//update the user
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateuser(@RequestBody UserDto userDto,@PathVariable("userid") Integer userid) {
		 UserDto updateUser=this.userServiceImpl.updateUser(userDto, userid);
	
		return ResponseEntity.ok(updateUser);
		
	}
	
	//delete the user 
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Integer userid){
		this.userServiceImpl.deleteuser(userid);
		return new  ResponseEntity(new ApiResponse("user deleted successfully " ,true), HttpStatus.OK);
	}
	
	
	//get all user 
	
	@GetMapping("/get")
	public ResponseEntity<List<UserDto>> getallUser(){
		return ResponseEntity.ok(this.userServiceImpl.getAlluser());
	}
	
	// get single user
	@GetMapping("/get/{userid}")
	public ResponseEntity<UserDto> getsingleUser(@PathVariable Integer userid){
		return ResponseEntity.ok(this.userServiceImpl.getUserbyid(userid));
	}	
}

