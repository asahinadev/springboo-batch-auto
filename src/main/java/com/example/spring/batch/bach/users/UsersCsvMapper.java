package com.example.spring.batch.bach.users;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import com.example.spring.batch.entithy.Users;

public class UsersCsvMapper
		extends BeanWrapperFieldSetMapper<Users> {

	public UsersCsvMapper() {
		super();
		setTargetType(Users.class);
	}

}
