package com.example.spring.batch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.batch.entithy.Users;

@Repository
public interface UsersRepository
	extends JpaRepository<Users, Integer> {

	public List<String> findAllByDeletedIsNull();

}
