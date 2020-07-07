package com.example.spring.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.batch.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsersListener extends JobExecutionListenerSupport {
	private static final String BEFORE_JOB = "[USERS] CSV IMPORT [START]";
	private static final String AFTER_JOB_ST = "[USERS] CSV STATUS [{}] ";
	private static final String AFTER_JOB_TM = "[USERS] CSV IMPORT [{}] {} ～ {}";
	private static final String AFTER_JOB_OJ = "[USERS] CSV IMPORT {}";

	@Autowired
	UsersRepository repository;

	@Override
	public void beforeJob(JobExecution job) {
		log.info(BEFORE_JOB);
	}

	@Override
	public void afterJob(JobExecution job) {
		switch (job.getStatus()) {
		case STARTING:
			log.info(AFTER_JOB_ST, job.getExitStatus());
			break;

		case STARTED:
			log.info(AFTER_JOB_ST, job.getExitStatus());
			break;

		case STOPPING:
			log.info(AFTER_JOB_ST, job.getExitStatus());
			break;

		case STOPPED:
			log.info(AFTER_JOB_ST, job.getExitStatus());
			break;

		case FAILED:
			log.error(AFTER_JOB_TM, job.getExitStatus(), job.getStartTime(), job.getEndTime());
			break;

		case ABANDONED:
			log.warn(AFTER_JOB_TM, job.getExitStatus(), job.getStartTime(), job.getEndTime());
			break;

		case UNKNOWN:
			log.info(AFTER_JOB_ST, job.getExitStatus());
			break;

		case COMPLETED:
			log.info(AFTER_JOB_TM, job.getStatus(), job.getStartTime(), job.getEndTime());
			if (log.isDebugEnabled()) {
				repository.findAll().forEach((item) -> {
					log.debug(AFTER_JOB_OJ, item);
				});
			}
			break;

		default:
			break;
		}
	}
}
