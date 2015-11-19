package com.dstvdm.labs.infrastructure.externalwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import com.dstvdm.labs.AccountRepository;

import entities.Account;

public class MongoRepoService {
	
	private String username;
	private String password;
	
	@Autowired
	private AccountRepository repository;
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Account authenticate() throws BadCredentialsException {
		
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Account("Alice", "Smith"));
		repository.save(new Account("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Account customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByUsername("Alice"));

		
		
		try {
		    Account user = repository.findByUsername(username);
		    System.out.println(user.toString());
		    String name = user.getUsername();
			String pass = user.getPassword();
			if(username.equals(name) && password.equals(pass)) {
				return user;
			}
			
		} catch (NullPointerException e) {
			return null;
		}
		return null;
		
	}

	
}
