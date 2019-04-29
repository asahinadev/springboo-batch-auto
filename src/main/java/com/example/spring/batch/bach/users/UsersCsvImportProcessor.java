package com.example.spring.batch.bach.users;

import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.spring.batch.entithy.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsersCsvImportProcessor
		implements ItemProcessor<Users, Users> {

	@Override
	public Users process(Users item) throws Exception {
		log.debug("item => {}", item);

		LocalDateTime now = LocalDateTime.now();

		item.setCreated(now);
		item.setUpdated(now);

		return item;
	}

}
