package org.example.controller;

import org.example.model.Salesperson;
import org.example.repository.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SalespersonController {
    @Autowired
    SalespersonRepository salespersonRepository;

    public SalespersonController(SalespersonRepository salespersonRepository) {
        this.salespersonRepository = salespersonRepository;
    }

    public void createSalesperson(Salesperson salesperson) {
        salespersonRepository.save(salesperson);
    }

    public void deleteSalesperson(Salesperson salesperson) {
        salespersonRepository.delete(salesperson);
    }

    public void updateSalesperson(Salesperson salesperson) {
        Optional<Salesperson> existingSalespersonOptional = salespersonRepository.findById(salesperson.getId());
        if (existingSalespersonOptional.isPresent()) {
            Salesperson existingSalesperson = existingSalespersonOptional.get();
            existingSalesperson.setFirstName(salesperson.getFirstName());
            existingSalesperson.setLastName(salesperson.getLastName());
            existingSalesperson.setPayCheck(salesperson.getPayCheck());

            salespersonRepository.save(existingSalesperson);
        } else {
            throw new IllegalArgumentException("Salesperson not found with ID: " + salesperson.getId());
        }
    }

    public Optional<Salesperson> findById(Long id) {
        return salespersonRepository.findById(id);
    }

    public List<Salesperson> getAllSalesperson() {
        return salespersonRepository.findAll();
    }

    public List<Salesperson> searchByName(String name) {
        return salespersonRepository.searchByFirstName(name);
    }
}
