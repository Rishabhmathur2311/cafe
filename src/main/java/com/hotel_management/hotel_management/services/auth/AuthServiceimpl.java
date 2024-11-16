package com.hotel_management.hotel_management.services.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel_management.hotel_management.Entity.User;
import com.hotel_management.hotel_management.Enum.UserRole;
import com.hotel_management.hotel_management.Repository.ReservationRepo;
import com.hotel_management.hotel_management.Repository.UserRepo;

@Service
public class AuthServiceimpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    public User createAdmin(User user) {
        Optional<User> local=userRepo.findByEmail(user.getEmail());
        User newUser = new User();
        System.out.println(user);

        if(user.getUserRole().equals(UserRole.ADMIN)) {
            if(local.isEmpty()){
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                newUser.setEmail(user.getEmail());
                newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                newUser.setName(user.getName());
                newUser.setUserRole(user.getUserRole());

                System.out.println(newUser);

                userRepo.save(newUser);
                return newUser;
            }
            else{
                System.out.println("User already exists");
            }
        }
        else{
            System.out.println("User is not admin");
        }

        return null;
    }

    public User createCustomer(User user) {
        Optional<User> local=userRepo.findByEmail(user.getEmail());
        User newUser = new User();
        System.out.println(user);

        if(user.getUserRole().equals(UserRole.CUSTOMER)) {
            if(local.isEmpty()){
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                newUser.setEmail(user.getEmail());
                newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                newUser.setName(user.getName());
                newUser.setUserRole(user.getUserRole());

                System.out.println(newUser);

                userRepo.save(newUser);
                return newUser;
            }
            else{
                System.out.println("User already exists");
            }
        }
        else{
            System.out.println("User is not customer");
        }

        return null;
    }

    public User getUserByEmail(String email) {
        Optional<User> local=userRepo.findByEmail(email);
        if(local.isPresent()) {
            return local.get();
        }
        else{
            System.out.println("User not found");
            return null;
        }
    }
}
