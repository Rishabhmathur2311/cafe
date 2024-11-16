package com.hotel_management.hotel_management.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel_management.hotel_management.Entity.AuthenticationRequest;
import com.hotel_management.hotel_management.Entity.AuthenticationResponse;
import com.hotel_management.hotel_management.Entity.User;
import com.hotel_management.hotel_management.Enum.UserRole;
import com.hotel_management.hotel_management.Repository.UserRepo;
import com.hotel_management.hotel_management.services.auth.AuthService;
import com.hotel_management.hotel_management.services.jwt.UserService;
import com.hotel_management.hotel_management.util.JwtUtil;

import jakarta.persistence.EntityExistsException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AuthService authService;



    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public User CreateAdmin(@RequestBody User user) {
        try{
            user.setUserRole(UserRole.ADMIN);
            System.out.println(user);
            return authService.createAdmin(user);
        } catch (EntityExistsException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/customer-signup")
    public User CreateCustomer(@RequestBody User user) {
        try{
            user.setUserRole(UserRole.CUSTOMER);
            System.out.println(user);
            return authService.createAdmin(user);
        } catch (EntityExistsException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{email}")
    public User GetUser(@PathVariable String email) {
        return authService.getUserByEmail(email);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        try {
            System.out.println(authenticationRequest);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());

            System.out.println(authentication);

        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
        System.out.println(authenticationRequest);

        System.out.println(authenticationRequest);

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepo.findByEmail(authenticationRequest.getEmail());

        String jwt = null;
        try {
            jwt = jwtUtil.generateToken(userDetails);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(jwt);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if(optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }

        return authenticationResponse;
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) throws Exception {
        System.out.println("HELLO");
        System.out.println(principal);

        if(principal != null) {
            System.out.println(principal);
            return (User) userService.userDetailsService().loadUserByUsername(principal.getName());
        }
        else{
            System.out.println("principal is null");
            return null;
        }
        // System.out.println(authenticationResponse);

        
        // Optional<User> userGet=userRepo.findById(authenticationResponse.getUserId());
        // System.out.print(userGet);
        // return userGet.get();
    }
}
