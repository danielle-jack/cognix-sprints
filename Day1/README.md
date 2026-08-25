# Day 1: Java Console Bank Application

## Overview
This project is a single-flow console-based banking application built in Java. It simulates a basic banking environment with role-based access for Admins and Customers. The app enforces business rules for different account types and acts as a comprehensive showcase of Object-Oriented Programming (OOP) fundamentals.

## Core Concepts Demonstrated
* **Object-Oriented Design (OOD):**
  * **Abstraction & Interfaces:** Used `User` and `Account` abstract classes, and an `AccountOperations` interface.
  * **Inheritance:** `Admin` and `Customer` extend `User`; `SavingsAccount` and `CheckingsAccount` extend `Account`.
  * **Polymorphism:** Method overriding for specific withdrawal rules (e.g., overdraft limits).
  * **Encapsulation:** Private fields accessed securely via getters and setters.
* **Collections & Data Structures:**
  * `HashMap` and `LinkedHashMap` for fast O(1) user and account lookups.
  * `TreeSet` to automatically sort and store unique customer account IDs.
  * `ArrayList` for generating iterable data views.
* **Control Flow:** Implementation of loops (`while`, `for-each`), conditionals, `switch-case` menus, ternary operators, and `try-catch` blocks for error handling.

## Project Structure
* `BankApp.java`: Contains the core business logic, interfaces, blueprints (abstract classes), concrete subclasses, and the central `Bank` data store.
* `Runner.java`: The executable entry point containing the `main` method, login loop, console UI, and user dashboards.

## How to Run
1. Open your terminal and navigate to the folder containing the `.java` files.
2. Compile all files together:
   ```bash
   javac *.java
3. Run the application:
    ```bash
    java Runner