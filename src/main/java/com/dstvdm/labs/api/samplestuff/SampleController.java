package com.dstvdm.labs.api.samplestuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dstvdm.labs.api.ApiController;
import com.dstvdm.labs.domain.CurrentlyLoggedUser;
import com.dstvdm.labs.domain.DomainUser;
import com.dstvdm.labs.domain.Stuff;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
public class SampleController extends ApiController {
	private final ServiceGateway serviceGateway;

	@Autowired
	public SampleController(ServiceGateway serviceGateway) {
		this.serviceGateway = serviceGateway;
	}

	@RequestMapping(value = STUFF_URL, method = RequestMethod.GET)
	public List<Stuff> getSomeStuff() {
		return serviceGateway.getSomeStuff();
	}

	@RequestMapping(value = STUFF_URL, method = RequestMethod.POST)
	public void createStuff(@RequestBody Stuff newStuff, @CurrentlyLoggedUser DomainUser domainUser) {
		serviceGateway.createStuff(newStuff, domainUser);
	}
}
