package com.example.spring.batch.entithy;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.spring.batch.persistence.LocalDateTimeColumn;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "deleted", "email" }),
		@UniqueConstraint(columnNames = { "deleted", "username" })
})
@EntityListeners(AuditingEntityListener.class)
public class Users
	implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String username;
	String email;
	String password;

	@CreatedDate
	@LocalDateTimeColumn
	LocalDateTime created;

	@LastModifiedDate
	@LocalDateTimeColumn
	LocalDateTime updated;

	@LocalDateTimeColumn
	LocalDateTime deleted;

}
