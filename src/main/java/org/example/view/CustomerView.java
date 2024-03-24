package org.example.view;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.controller.CustomerController;
import org.example.controller.SalespersonController;
import org.example.controller.StoreController;
import org.example.controller.TransactionController;
import org.example.model.Customer;
import org.example.model.Store;
import org.example.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class CustomerView {
    Scanner scanner;
    CustomerController customerController;
    StoreController storeController;
    TransactionController transactionController;

    public CustomerView() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("Welcome to the customer site!");
        System.out.println("Please choose what you want to do:");
        System.out.println("1. Show all stores");
        System.out.println("2. Show my credit");
        System.out.println("3. Show all transactions");
        System.out.println("4. Show the transactions made in a given store");
        System.out.println("5. Show the transactions made in a given date");
        System.out.println("6. Search a store");
        System.out.println("7. Add credit");
        System.out.println("8. Edit information about my data");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                List<Store> stores = storeController.getAllStore();
                for (Store store : stores) {
                    System.out.println(store);
                }
            }
            case 2 -> {
                System.out.print("Enter your customer ID: ");
                Long customerId = scanner.nextLong();
                Optional<Customer> customerOptional = customerController.findById(customerId);
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    System.out.println("Your credit: " + customer.getCredit());
                } else {
                    System.out.println("Customer not found with ID: " + customerId);
                }
            }
            case 3 -> {
                List<Transaction> transactions = transactionController.getAllTransaction();
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
            case 4 -> {
                System.out.print("Enter your customer ID: ");
                Long customerId = scanner.nextLong();
                Optional<Customer> customerOptional = customerController.findById(customerId);
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    System.out.print("Enter store ID: ");
                    Long storeId = scanner.nextLong();
                    Optional<Store> storeOptional = storeController.findById(storeId);
                    if (storeOptional.isPresent()) {
                        Store store = storeOptional.get();
                        List<Transaction> transactions = transactionController.getTransactionsByCustomerAndStore(customer, store);
                        for (Transaction transaction : transactions) {
                            System.out.println(transaction);
                        }
                    } else {
                        System.out.println("Store not found with ID: " + storeId);
                    }
                } else {
                    System.out.println("Customer not found with ID: " + customerId);
                }
            }
            case 5 -> {
                System.out.print("Enter date (YYYY-MM-DD): ");
                String dateString = scanner.next();
                LocalDate date = LocalDate.parse(dateString);
                List<Transaction> transactions = transactionController.getTransactionsByDate(date);
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
            case 6 -> {
                System.out.print("Enter store name to search: ");
                String name = scanner.nextLine();
                List<Store> foundStores = storeController.searchByName(name);
                if (foundStores.isEmpty()) {
                    System.out.println("No stores found with name: " + name);
                } else {
                    for (Store store : foundStores) {
                        System.out.println(store);
                    }
                }
            }
            case 7 -> {
                System.out.print("Enter your customer ID: ");
                Long customerId = scanner.nextLong();
                Optional<Customer> customerOptional = customerController.findById(customerId);
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    System.out.print("Enter amount to add: ");
                    double amount = scanner.nextDouble();
                    customer.setCredit(customer.getCredit() + amount);
                    customerController.updateCustomer(customer);
                } else {
                    System.out.println("Customer not found with ID: " + customerId);
                }
            }
            case 8 -> {
                System.out.print("Enter your customer ID: ");
                Long customerId = scanner.nextLong();
                scanner.nextLine();
                Optional<Customer> customerOptional = customerController.findById(customerId);
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    System.out.println("Choose the information you want to edit:");
                    System.out.println("1. First Name");
                    System.out.println("2. Last Name");
                    System.out.println("3. Credit");
                    System.out.print("Enter your choice: ");
                    int editChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (editChoice) {
                        case 1 -> {
                            System.out.print("Enter new first name: ");
                            String newFirstName = scanner.nextLine();
                            customer.setFirstName(newFirstName);
                        }
                        case 2 -> {
                            System.out.print("Enter new last name: ");
                            String newLastName = scanner.nextLine();
                            customer.setLastName(newLastName);
                        }
                        case 3 -> {
                            System.out.print("Enter new credit amount: ");
                            double newCredit = scanner.nextDouble();
                            customer.setCredit(newCredit);
                        }
                        default -> System.out.println("Invalid choice.");
                    }
                    customerController.updateCustomer(customer);
                } else {
                    System.out.println("Customer not found with ID: " + customerId);
                }
            }

            default -> {
                System.out.println("Invalid choice. Please choose another number from above.");
                display();
            }
        }
    }
}
