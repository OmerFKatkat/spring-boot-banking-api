package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, TransactionRepository transactionRepository1) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.transactionRepository = transactionRepository1;

    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User register(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        } else {
            if(user.getRole() == null){user.setRole("USER");};
            return userRepository.save(user);
        }
    }

    public String login(String email,String password) {
        User user = findByEmail(email);
        if (user == null || user.getPassword() == null) {
            return null;
        };
        if (user.getPassword().equals(password)) {
            return jwtUtil.generateToken(email);
        };
        return null;
    }

    public Double getBalance(String email){
        return findByEmail(email).getBalance();
    }

    public String transfer(String senderEmail, String receiverEmail, Double amount) {
        User sender = findByEmail(senderEmail);
        User receiver = findByEmail(receiverEmail);

        if (sender == null || receiver == null) {return null;}
        if (sender.getBalance() < amount){return null;}

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setSenderEmail(senderEmail);
        transaction.setReceiverEmail(receiverEmail);
        transaction.setAmount(amount);
        transaction.setDate(String.valueOf(System.currentTimeMillis()));
        transactionRepository.save(transaction);

        return "Transfer successful";
    }

    public List<Transaction> getTransactions(String email){
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionRepository.findBySenderEmail(email));
        transactions.addAll(transactionRepository.findByReceiverEmail(email));
        return transactions;
    }
}
