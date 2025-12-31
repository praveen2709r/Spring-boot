package com.Ecommerce.ProductService.Controller;

import com.Ecommerce.ProductService.DTO.LoginRequest;
import com.Ecommerce.ProductService.DTO.RegisterRequest;
import com.Ecommerce.ProductService.Entity.User;
import com.Ecommerce.ProductService.Security.JwtUtil;
import com.Ecommerce.ProductService.Service.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    public AuthController(JwtUtil jwtUtil, AuthService authService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.save(request));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        return ResponseEntity.ok(jwtUtil.generateToken(loginRequest.getUserName()));
    }
    @GetMapping("/getUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        return ResponseEntity.ok(authService.deleteByUserId(userId));
    }
}
