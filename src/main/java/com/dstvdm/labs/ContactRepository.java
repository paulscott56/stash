package com.dstvdm.labs;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.dstvdm.labs.entities.Contact;

@Component
public interface ContactRepository extends MongoRepository<Contact, String> {

}
