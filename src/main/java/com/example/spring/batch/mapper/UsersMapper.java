package com.example.spring.batch.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import com.example.spring.batch.entithy.Users;

public class UsersMapper
	extends BeanWrapperFieldSetMapper<Users> {

	public UsersMapper() {
		setTargetType(Users.class);
	}

}
