package com.Ecommerce.ProductService.Service;

import com.Ecommerce.ProductService.DTO.RegisterRequest;
import com.Ecommerce.ProductService.Entity.User;
import com.Ecommerce.ProductService.ExceptionHandling.UserAlreadyExistException;
import com.Ecommerce.ProductService.Repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String save(RegisterRequest request) {
        if(userRepository.findByUserName(request.getUserName()).isPresent()){
            throw new UserAlreadyExistException("User is already exists");
        }
        User user=new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        userRepository.save(user);
        return "User saved successfully";
    }

    public @Nullable List<User> findAll() {
        return userRepository.findAll();
    }

    public @Nullable String deleteByUserId(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );
        user.setActive(false);
        userRepository.save(user);
        return "User Deleted";
    }
}
