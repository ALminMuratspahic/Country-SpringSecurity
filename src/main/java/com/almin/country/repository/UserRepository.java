package com.almin.country.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almin.country.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUserName(String userName);

}
