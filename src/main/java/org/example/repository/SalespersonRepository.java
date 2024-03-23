package org.example.repository;

import org.example.model.Salesperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalespersonRepository extends JpaRepository<Salesperson, Long> {
    List<Salesperson> searchByFirstName(String name);
}
