package com.dstvdm.labs.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dstvdm.labs.entities.Account;
import com.dstvdm.labs.entities.User;

public interface UserRepository extends MongoRepository<User, Long> {
	// public Account findByUsername(String username);
	Optional<User> findOneByEmail(String email);

}
