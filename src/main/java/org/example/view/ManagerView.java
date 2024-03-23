package org.example.view;

import org.example.controller.CustomerController;
import org.example.controller.SalespersonController;
import org.example.controller.StoreController;
import org.example.controller.TransactionController;
import org.example.model.Customer;
import org.example.model.Salesperson;
import org.example.model.Store;
import org.example.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManagerView {

    Scanner scanner;
    CustomerController customerController;
    SalespersonController salespersonController;
    StoreController storeController;
    TransactionController transactionController;

    public ManagerView(CustomerController customerController, SalespersonController salespersonController, StoreController storeController, TransactionController transactionController) {
        this.scanner = new Scanner(System.in);
        this.customerController = customerController;
        this.salespersonController = salespersonController;
        this.storeController = storeController;
        this.transactionController = transactionController;
    }

    public void display() {
        System.out.println("Welcome to the manager site!");
        System.out.println("Please choose what you want to do:");
        System.out.println("1. Show all stores");
        System.out.println("2. Show all salespersons");
        System.out.println("3. Show all customers");
        System.out.println("4. Show the profit of a store");
        System.out.println("5. Show the total number of transactions of a store in a given day");
        System.out.println("6. Show the transactions of a store in a given day");
        System.out.println("7. Search a store");
        System.out.println("8. Search a salesperson");
        System.out.println("9. Search a customer");
        System.out.println("10. Edit information about a store");
        System.out.println("11. Edit information about a salesperson");
        System.out.println("12. Increase the paycheck of a salesperson");
        System.out.println("13. Add a store");
        System.out.println("14. Add a salesperson");
        System.out.println("15. Add a customer");
        System.out.println("16. Delete a store");
        System.out.println("17. Delete a salesperson");
        System.out.println("18. Delete a customer");

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
                List<Salesperson> salespersons = salespersonController.getAllSalesperson();
                for (Salesperson salesperson : salespersons) {
                    System.out.println(salesperson);
                }
            }
            case 3 -> {
                List<Customer> customers = customerController.getAllCustomers();
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            }
            case 4 -> {
                System.out.print("Enter store ID: ");
                Long storeId = scanner.nextLong();
                Optional<Store> storeOptional = storeController.findById(storeId);
                if (storeOptional.isPresent()) {
                    Store store = storeOptional.get();
                    System.out.println("Profit of Store " + store.getName() + ": " + store.getProfit());
                } else {
                    System.out.println("Store not found with ID: " + storeId);
                }
            }
            case 5 -> {
                System.out.print("Enter store ID: ");
                Long storeId = scanner.nextLong();
                System.out.print("Enter date (YYYY-MM-DD): ");
                String dateString = scanner.next();
                LocalDate date = LocalDate.parse(dateString);

                int totalTransactions = transactionController.getTotalTransactionsByStoreAndDate(storeId, date);
                System.out.println("Total transactions of Store " + storeId + " on " + dateString + ": " + totalTransactions);
            }
            case 6 -> {
                System.out.print("Enter store ID: ");
                Long storeId = scanner.nextLong();
                System.out.print("Enter date (YYYY-MM-DD): ");
                String dateString = scanner.next();
                LocalDate date = LocalDate.parse(dateString);
                List<Transaction> transactions = transactionController.getTransactionsByStoreAndDate(storeId, date);
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
            case 7 -> {
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
            case 8 -> {
                System.out.print("Enter salesperson name to search: ");
                String name = scanner.nextLine();
                List<Salesperson> foundSalesperson = salespersonController.searchByName(name);
                if (foundSalesperson.isEmpty()) {
                    System.out.println("No stores found with name: " + name);
                } else {
                    for (Salesperson salesperson : foundSalesperson) {
                        System.out.println(salesperson);
                    }
                }
            }
            case 9 -> {
                System.out.print("Enter customer name to search: ");
                String name = scanner.nextLine();
                List<Customer> foundCustomers = customerController.searchByName(name);
                if (foundCustomers.isEmpty()) {
                    System.out.println("No customers found with name: " + name);
                } else {
                    for (Customer customer : foundCustomers) {
                        System.out.println(customer);
                    }
                }
            }
            case 10 -> {
                System.out.print("Enter store ID to edit: ");
                Long storeId = scanner.nextLong();
                scanner.nextLine();

                Optional<Store> storeOptional = storeController.findById(storeId);
                if (storeOptional.isPresent()) {
                    Store store = storeOptional.get();

                    System.out.print("Enter new name for the store: ");
                    String newName = scanner.nextLine();

                    store.setName(newName);
                    storeController.updateStore(store);

                    System.out.println("Store information updated successfully.");
                } else {
                    System.out.println("Store not found with ID: " + storeId);
                }
            }

            case 11 -> {
                System.out.print("Enter the ID of the salesperson you want to edit: ");
                Long salespersonId = scanner.nextLong();
                scanner.nextLine();

                Optional<Salesperson> salespersonOptional = salespersonController.findById(salespersonId);
                if (salespersonOptional.isPresent()) {
                    Salesperson salesperson = salespersonOptional.get();

                    System.out.print("Enter the new first name: ");
                    String newFirstName = scanner.nextLine();
                    System.out.print("Enter the new last name: ");
                    String newLastName = scanner.nextLine();
                    System.out.print("Enter the new paycheck: ");
                    double newPaycheck = scanner.nextDouble();

                    salesperson.setFirstName(newFirstName);
                    salesperson.setLastName(newLastName);
                    salesperson.setPayCheck(newPaycheck);

                    salespersonController.updateSalesperson(salesperson);

                    System.out.println("Salesperson information updated successfully.");
                } else {
                    System.out.println("Salesperson not found with ID: " + salespersonId);
                }
            }
            case 12 -> {
                System.out.print("Enter salesperson ID to increase paycheck: ");
                Long salespersonId = scanner.nextLong();
                scanner.nextLine();

                Optional<Salesperson> salespersonOptional = salespersonController.findById(salespersonId);
                if (salespersonOptional.isPresent()) {

                    System.out.print("Enter the amount to increase paycheck: ");
                    double increaseAmount = scanner.nextDouble();

                    salespersonController.increasePaycheck(salespersonId, increaseAmount);

                    System.out.println("Paycheck increased successfully.");
                } else {
                    System.out.println("Salesperson not found with ID: " + salespersonId);
                }
            }
            case 13 -> {
                System.out.print("Enter store name: ");
                String storeName = scanner.nextLine();
                System.out.print("Enter max number of customers: ");
                int maxCustomers = scanner.nextInt();
                System.out.print("Enter initial profit: ");
                double profit = scanner.nextDouble();

                Store newStore = new Store();
                newStore.setName(storeName);
                newStore.setMaxCustomers(maxCustomers);
                newStore.setProfit(profit);

                storeController.createStore(newStore);

                System.out.println("Store added successfully.");
            }
            case 14 -> {
                System.out.print("Enter salesperson first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter salesperson last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter initial paycheck: ");
                double paycheck = scanner.nextDouble();

                Salesperson newSalesperson = new Salesperson();
                newSalesperson.setFirstName(firstName);
                newSalesperson.setLastName(lastName);
                newSalesperson.setPayCheck(paycheck);

                salespersonController.createSalesperson(newSalesperson);

                System.out.println("Salesperson added successfully.");
            }
            case 15 -> {
                System.out.print("Enter customer first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter customer last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter initial credit: ");
                double credit = scanner.nextDouble();

                Customer newCustomer = new Customer();
                newCustomer.setFirstName(firstName);
                newCustomer.setLastName(lastName);
                newCustomer.setCredit(credit);

                customerController.createCustomer(newCustomer);

                System.out.println("Customer added successfully.");
            }
            case 16 -> {
                System.out.print("Enter store ID to delete: ");
                Long storeIdToDelete = scanner.nextLong();

                Optional<Store> storeOptional = storeController.findById(storeIdToDelete);
                if (storeOptional.isPresent()) {
                    Store storeToDelete = storeOptional.get();

                    storeController.deleteStore(storeToDelete);
                    System.out.println("Store deleted successfully.");
                } else {
                    System.out.println("Store not found with ID: " + storeIdToDelete);
                }
            }
            case 17 -> {
                System.out.print("Enter salesperson ID to delete: ");
                Long salespersonIdToDelete = scanner.nextLong();

                Optional<Salesperson> salespersonOptional = salespersonController.findById(salespersonIdToDelete);
                if (salespersonOptional.isPresent()) {
                    Salesperson salespersonToDelete = salespersonOptional.get();

                    salespersonController.deleteSalesperson(salespersonToDelete);
                    System.out.println("Salesperson deleted successfully.");
                } else {
                    System.out.println("Salesperson not found with ID: " + salespersonIdToDelete);
                }
            }
            case 18 -> {
                System.out.print("Enter customer ID to delete: ");
                Long customerIdToDelete = scanner.nextLong();

                Optional<Customer> customerOptional = customerController.findById(customerIdToDelete);
                if (customerOptional.isPresent()) {
                    Customer customerToDelete = customerOptional.get();

                    customerController.deleteCustomer(customerToDelete);
                    System.out.println("Customer deleted successfully.");
                } else {
                    System.out.println("Customer not found with ID: " + customerIdToDelete);
                }
            }

            default -> {
                System.out.println("Invalid choice. Please choose another number from above.");
                display();
            }
        }
    }
}
