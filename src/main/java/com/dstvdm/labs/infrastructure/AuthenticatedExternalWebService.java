package com.dstvdm.labs.infrastructure;

import org.springframework.security.core.GrantedAuthority;

import com.dstvdm.labs.infrastructure.externalwebservice.ExternalWebServiceStub;
import com.dstvdm.labs.infrastructure.security.AuthenticationWithToken;

import java.util.Collection;

public class AuthenticatedExternalWebService extends AuthenticationWithToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8290949644726759846L;
	
	private ExternalWebServiceStub externalWebService;

	public AuthenticatedExternalWebService(Object aPrincipal, Object aCredentials,
			Collection<? extends GrantedAuthority> anAuthorities) {
		super(aPrincipal, aCredentials, anAuthorities);
	}

	public void setExternalWebService(ExternalWebServiceStub externalWebService) {
		this.externalWebService = externalWebService;
	}

	public ExternalWebServiceStub getExternalWebService() {
		return externalWebService;
	}
}
