package com.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {

	private int id;
	@NotNull
	private String name;
	@Email
	private String email;
	private String password;
	private String about;
	
}
