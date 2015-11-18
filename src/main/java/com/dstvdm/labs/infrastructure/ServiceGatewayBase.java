package com.dstvdm.labs.infrastructure;

import com.dstvdm.labs.infrastructure.externalwebservice.ExternalWebServiceStub;

public abstract class ServiceGatewayBase {
	private AuthenticatedExternalServiceProvider authenticatedExternalServiceProvider;

	public ServiceGatewayBase(AuthenticatedExternalServiceProvider authenticatedExternalServiceProvider) {
		this.authenticatedExternalServiceProvider = authenticatedExternalServiceProvider;
	}

	protected ExternalWebServiceStub externalService() {
		return authenticatedExternalServiceProvider.provide().getExternalWebService();
	}
}
