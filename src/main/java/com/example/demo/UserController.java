package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping("/balance")
    public Double getBalance(@RequestParam("email") String email) {
        return userService.getBalance(email);
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest transferRequest) {
        return userService.transfer(transferRequest.getSenderEmail(), transferRequest.getReceiverEmail(), transferRequest.getAmount());
    }

    @GetMapping("transactions")
    public List<Transaction> getTransactions(@RequestParam("email") String email) {
        return userService.getTransactions(email);
    }
}
