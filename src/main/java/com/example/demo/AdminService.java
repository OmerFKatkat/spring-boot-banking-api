package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public String freeze(String email){
        User account = userRepository.findByEmail(email).orElse(null);
        if(account == null) {return "User not found";}
        account.setBalance(-1.0);
        userRepository.save(account);
        return "Account frozen";
    }
}
