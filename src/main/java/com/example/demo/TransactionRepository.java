package com.example.demo;

import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findBySenderEmail(String email);
    List<Transaction> findByReceiverEmail(String email);
}
