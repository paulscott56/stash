package com.dstvdm.labs.infrastructure.security;

public interface ExternalServiceAuthenticator {

	AuthenticationWithToken authenticate(String username, String password);
}
