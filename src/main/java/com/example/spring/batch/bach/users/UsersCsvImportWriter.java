package com.example.spring.batch.bach.users;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.batch.entithy.Users;
import com.example.spring.batch.repository.UsersRepository;

@Component
public class UsersCsvImportWriter
		implements ItemWriter<Users> {

	static final Object lock = new Object();

	@Autowired
	UsersRepository usersRepository;

	@Override
	public void write(List<? extends Users> items) throws Exception {
		usersRepository.saveAll(items);
		usersRepository.flush();
	}

}
