package org.example.view;

import org.example.controller.CustomerController;
import org.example.controller.SalespersonController;
import org.example.controller.StoreController;
import org.example.controller.TransactionController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class View {

    private final Scanner scanner;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private SalespersonController salespersonController;

    @Autowired
    private StoreController storeController;

    @Autowired
    private TransactionController transactionController;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("Welcome to the application!");
        System.out.println("Please choose as who you want to see:");
        System.out.println("1. Manager");
        System.out.println("2. Customer");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                ManagerView managerView = new ManagerView(customerController, salespersonController, storeController, transactionController);
                managerView.display();
            }
            case 2 -> {
                CustomerView customerView = new CustomerView(customerController, salespersonController, storeController, transactionController);
                customerView.display();
            }
            default -> {
                System.out.println("Invalid choice. Please choose 1 or 2.");
                showMenu();
            }
        }
    }
}
