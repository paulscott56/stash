package com.dstvdm.labs.infrastructure.externalwebservice;

import java.util.Collection;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dstvdm.labs.entities.User;
import com.dstvdm.labs.entities.UserCreateForm;
import com.dstvdm.labs.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt())); 
        user.setRole(form.getRole());
        return userRepository.save(user);
    }
}
