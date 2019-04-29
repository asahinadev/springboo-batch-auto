package com.example.spring.batch.bach.users;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.batch.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsersCsvImportListener
		extends JobExecutionListenerSupport {

	@Autowired
	UsersRepository repository;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("[USERS] CSV IMPORT [START]");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		switch (jobExecution.getStatus()) {
		case COMPLETED:

			repository.findAll().stream().forEach((item) -> {
				log.debug("[USERS] CSV IMPORT {}", item);
			});

			break;

		default:
			break;
		}
		log.info("[USERS] CSV IMPORT [{}] {} ～ {}",
				jobExecution.getStatus(),
				jobExecution.getStartTime(),
				jobExecution.getEndTime());
	}

}
