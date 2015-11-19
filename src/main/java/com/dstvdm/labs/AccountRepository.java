package com.dstvdm.labs;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import entities.Account;


public interface AccountRepository extends MongoRepository<Account, String> {

	public Account findByUsername(String username);

}