package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.dto.request.UserCreationRequest;
import com.hotelmanagementsystem.indentity_service.entity.User;
import com.hotelmanagementsystem.indentity_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPasswordHash(request.getPasswordHash());
        user.setIsActive(request.getIsActive());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAvatarUrl(request.getAvatarUrl());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}