package com.Ecommerce.ProductService.Service;

import com.Ecommerce.ProductService.DTO.RegisterRequest;
import com.Ecommerce.ProductService.Entity.User;
import com.Ecommerce.ProductService.ExceptionHandling.UserAlreadyExistException;
import com.Ecommerce.ProductService.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        userRepository.save(user);
        return "User saved successfully";
    }
}
