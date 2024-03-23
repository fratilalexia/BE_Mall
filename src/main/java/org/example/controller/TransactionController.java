package org.example.controller;

import org.example.model.Customer;
import org.example.model.Store;
import org.example.model.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        Optional<Transaction> existingTransactionOptional = transactionRepository.findById(transaction.getId());

        if (existingTransactionOptional.isPresent()) {
            Transaction existingTransaction = existingTransactionOptional.get();
            existingTransaction.setSum(transaction.getSum());
            existingTransaction.setCustomer(transaction.getCustomer());
            existingTransaction.setStore(transaction.getStore());
            existingTransaction.setDate(transaction.getDate());

            transactionRepository.save(existingTransaction);
        } else {
            throw new IllegalArgumentException("Transaction not found with ID: " + transaction.getId());
        }
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public int getTotalTransactionsByStoreAndDate(Long storeId, LocalDate date) {
        return transactionRepository.countByStoreIdAndDate(storeId, date);
    }

    public List<Transaction> getTransactionsByStoreAndDate(Long storeId, LocalDate date) {
        return transactionRepository.findByStoreIdAndDate(storeId, date);
    }

    public List<Transaction> getTransactionsByCustomerAndStore(Customer customer, Store store) {
        return transactionRepository.findByCustomerAndStore(customer, store);
    }

    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findByDate(date);

    }
}
