package com.dash.services;

import com.dash.exceptions.InvalidPasswordException;
import com.dash.exceptions.UserNotFoundException;
import com.dash.models.User;
import com.dash.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(Long.parseLong(userId));
    }

    public User updateUser(Long userId, User user) {
        User existingUser = getUser(userId);
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setContact(user.getContact());
        existingUser.setGender(user.getGender());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setAdmin(user.isAdmin());
        return userRepository.save(existingUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void authenticate(User user) {
        User existingUser = getUserByEmail(user.getEmail());

        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Authentication successful
        } else {
            throw new InvalidPasswordException();
        }
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public User getUserProfile(String email) {
        return getUserByEmail(email);
    }
}
