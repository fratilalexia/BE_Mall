package org.example.view;

import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class View {

    private final Scanner scanner;

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
                ManagerView managerView = new ManagerView();
                managerView.display();
            }
            case 2 -> {
                CustomerView customerView = new CustomerView();
                customerView.display();
            }
            default -> {
                System.out.println("Invalid choice. Please choose 1 or 2.");
                showMenu();
            }
        }
    }
}
