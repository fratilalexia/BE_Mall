package org.example.view;

import org.example.controller.CustomerController;
import org.example.controller.SalespersonController;
import org.example.controller.StoreController;
import org.example.controller.TransactionController;

import java.util.Scanner;

public class CustomerView {
    private Scanner scanner;
    private CustomerController customerController;
    private SalespersonController salespersonController;
    private StoreController storeController;
    private TransactionController transactionController;

    public CustomerView(CustomerController customerController, SalespersonController salespersonController, StoreController storeController, TransactionController transactionController) {
        this.scanner = new Scanner(System.in);
        this.customerController = customerController;
        this.salespersonController = salespersonController;
        this.storeController = storeController;
        this.transactionController = transactionController;
    }

    public void display() {
        System.out.println("Welcome to the customer site!");
        System.out.println("Please choose what you want to do:");
        System.out.println("1. Show all stores");
        System.out.println("2. Show all transactions");
        System.out.println("3. Edit information about my data");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
            }
            case 2 -> {
            }
            default -> {
                System.out.println("Invalid choice. Please choose another number from above.");
                display();
            }
        }
    }
}
