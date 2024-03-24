package org.example.repository;

import org.example.model.Customer;
import org.example.model.Store;
import org.example.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    int countByStoreIdAndDate(Long storeId, LocalDate date);

    List<Transaction> findByStoreIdAndDate(Long storeId, LocalDate date);

    List<Transaction> findByCustomerAndStore(Customer customer, Store store);

    List<Transaction> findByDate(LocalDate date);
}
