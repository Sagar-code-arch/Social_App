package com.bsl.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.entity.User;
import com.bsl.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    
    public User loginUser(String username, String password) {

		User user = userRepository.findByName(username);    // it will set user to null if username is not present
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}

	}
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    
    public List<User> getAllUsers(){
    	return userRepository.findAll();
    }
}

