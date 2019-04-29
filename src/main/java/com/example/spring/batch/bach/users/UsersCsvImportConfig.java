package com.example.spring.batch.bach.users;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.spring.batch.entithy.Users;
import com.example.spring.batch.repository.UsersRepository;

@Configuration
@EnableBatchProcessing
public class UsersCsvImportConfig {

	static final String[] CSV_FIELDS = {
			"username",
			"email",
			"password"
	};

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UsersCsvImportProcessor processor;

	@Bean
	public FlatFileItemReader<Users> usersFlatFileItemReader() {
		return new FlatFileItemReaderBuilder<Users>()
				.name("usersCsvIportReader")
				.resource(new ClassPathResource("users.csv"))
				.delimited()
				.names(CSV_FIELDS)
				.fieldSetMapper(new UsersCsvMapper())
				.build();
	}

	@Bean
	public Job usersCsvIportJob(
			UsersCsvImportListener listener,
			Step usersCsvIportStep1) {
		return jobBuilderFactory.get("usersCsvIportJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(usersCsvIportStep1)
				.end()
				.build();
	}

	@Bean
	public Step usersCsvIportStep1(UsersCsvImportWriter writer) {

		return stepBuilderFactory.get("usersCsvIportStep1")
				.<Users, Users>chunk(10)
				.reader(usersFlatFileItemReader())
				.processor(processor)
				.writer(writer)
				.build();
	}
}
