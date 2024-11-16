package com.aurum.aurum_core.console;

import com.aurum.aurum_core.model.User;
import com.aurum.aurum_core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {
    @Autowired
    private UserService userService;

    private User loggedInUser = null;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (loggedInUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
            } else {
                System.out.println("3. Logout");
                System.out.println("4. Exit");
            }

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (loggedInUser == null) {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        try {
                            userService.createUser(username, password);
                            System.out.println("User registered successfully!");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    if (loggedInUser == null) {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        var user = userService.loginUser(username, password);
                        if (user.isPresent()) {
                            loggedInUser = user.get();
                            System.out.println("Logged in as: " + loggedInUser.getUsername());
                        } else {
                            System.out.println("Invalid credentials!");
                        }
                    }
                    break;

                case 3:
                    if (loggedInUser != null) {
                        System.out.println("Logged out!");
                        loggedInUser = null;
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
