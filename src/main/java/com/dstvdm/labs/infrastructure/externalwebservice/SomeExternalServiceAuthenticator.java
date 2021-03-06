package com.dstvdm.labs.infrastructure.externalwebservice;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dstvdm.labs.domain.DomainUser;
import com.dstvdm.labs.entities.Account;
import com.dstvdm.labs.entities.Role;
import com.dstvdm.labs.entities.User;
import com.dstvdm.labs.entities.UserCreateForm;
import com.dstvdm.labs.infrastructure.AuthenticatedExternalWebService;
import com.dstvdm.labs.infrastructure.security.ExternalServiceAuthenticator;

public class SomeExternalServiceAuthenticator implements ExternalServiceAuthenticator {

	@Autowired
	private UserService userService;
	
	
	@Override
	public AuthenticatedExternalWebService authenticate(String username, String password) {
		ExternalWebServiceStub externalWebService = new ExternalWebServiceStub();
//		UserCreateForm form = new UserCreateForm();
//		form.setEmail("pscott209@gmail.com");
//		form.setPassword("password");
//		form.setPasswordRepeated("password");
//		form.setRole(Role.ADMIN);
//		
//		userService.create(form);
		
		try {
		    Optional<User> user = userService.getUserByEmail(username);
		    User userOpt = user.get();
		    
		    if (BCrypt.checkpw(password, userOpt.getPasswordHash())) {
		    	AuthenticatedExternalWebService authenticatedExternalWebService = new AuthenticatedExternalWebService(
						new DomainUser(username), null, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DOMAIN_USER"));
				authenticatedExternalWebService.setExternalWebService(externalWebService);
				return authenticatedExternalWebService;
		    }
		    else {
		    	return null;
		    }
		}catch (Exception e) {
		  	e.printStackTrace();
		}
		
		return null;	
	}
}
