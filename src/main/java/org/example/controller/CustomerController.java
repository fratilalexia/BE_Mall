package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Controller
public class CustomerController {

    CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public void updateCustomer(Customer customer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customer.getId());
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setCredit(customer.getCredit());

            customerRepository.save(existingCustomer);
        } else {
            throw new IllegalArgumentException("Customer not found with ID: " + customer.getId());
        }
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> searchByName(String name) {
        return customerRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }
}
