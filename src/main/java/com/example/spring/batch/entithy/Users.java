package com.example.spring.batch.entithy;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity(name = "users")
@Table(name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = { "deleted", "email" }),
				@UniqueConstraint(columnNames = { "deleted", "username" })
		}

)
public class Users
		implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	Integer id;

	@Column(length = 255)
	String username;

	@Column(length = 255)
	String email;

	@Column(length = 255)
	String password;

	@Column
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime created;

	@Column
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime updated;

	@Column
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime deleted;

}
