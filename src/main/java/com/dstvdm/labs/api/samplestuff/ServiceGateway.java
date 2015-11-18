package com.dstvdm.labs.api.samplestuff;

import java.util.List;

import com.dstvdm.labs.domain.DomainUser;
import com.dstvdm.labs.domain.Stuff;

public interface ServiceGateway {
	List<Stuff> getSomeStuff();

	void createStuff(Stuff newStuff, DomainUser domainUser);
}
