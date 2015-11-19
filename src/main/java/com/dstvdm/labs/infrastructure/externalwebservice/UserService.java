package com.dstvdm.labs.infrastructure.externalwebservice;

import java.util.Collection;
import java.util.Optional;

import com.dstvdm.labs.entities.User;
import com.dstvdm.labs.entities.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}