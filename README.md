# OOP-Final-Version Ticketing System

## Overview
This program is a ticketing system that is built using Java Spring Boot and Angular. The Java CLI is setup inside the Spring Boot Application.

This ticketing system uses multi-threading to simulate simultaneous interactions between vendors and customers buying tickets. It allows for managing ticket sales with adjustable parameters such as ticket release rates, customer retrieval rates, and more. Once set up, the system provides a front-end interface to monitor real-time ticket sales. The program will continue to run until all tickets are sold. The system will automatically stop when all tickets have been sold out.

## Features

- **Multi-Threading**: Supports simultaneous interactions between multiple vendors and customers.

- **Advanced Synchronization Methods**: Critical sections are protected using locks.

- **Real-Time Tracking**: View live updates on ticket sales, vendor actions, and customer purchases.

- **Customizable Configuration**: Easily configure ticket pool parameters such as release rates, retrieval rates, and maximum capacity.

- **Front-End Interface**: Use the Angular front-end to control and monitor the system in real-time.

- **Storage**: Save configurations for future use.


## Prerequisites

- **Java**: Version 21

- **Maven**: For dependency management and building the project

- **Spring Boot**: For the backend API

- **Spring Web**: For web services and RESTful controllers

- **GSON**: To handle JSON files for saving and loading configurations

- **NodeJS and npm**: Prerequisites for Angular

- **Angular**: Version 18 for the frontend

- **IDE**: Any Java IDE (e.g., IntelliJ) and an Angular-compatible IDE (e.g., Visual Studio Code)


## Backend Setup

### Step 1: Open the  Backend Folder

1. **Open the Backend Folder**: Open the `CLI+ Backend` folder as a Java project in your IDE of choice.

2. Rescan project indexes in the IDE

3. Navigate to the `Main` class: `CLI+ Backend/src/main/java/com.oop.cwk/Main`

4. Run the `Main` class through the IDE.

### Step 2: Configure the CLI

1. **Create or Load Configuration**:
    
    - When prompted, select whether you want to create a new configuration file or load an existing one:
        
        - Press **1** to create a new configuration and input the necessary details.
        
        - Press **2** to load an existing configuration by entering the name of the JSON file where the configuration is stored.
    
2. **Input Configuration Details**:
    
    - **Total Tickets**: Minimum 1, maximum 10,000 tickets available for the event.
    
    - **Ticket Release Rate**: The time a vendor has to wait before attempting to release another ticket (between 1 and 10 seconds).
    
    - **Customer Retrieval Rate**: The time a customer has to wait before attempting to buy another ticket (between 1 and 10 seconds).
    
    - **Maximum Ticket Capacity**: The maximum number of tickets available for sale at any given time.
    
    - **Number of Vendors**: The number of vendor threads (minimum 1, maximum 100).
    
    - **Number of Customers**: The number of customer threads (minimum 1, maximum 100).
    
3. **Do Not Press Enter Yet**: Once the configuration details are input, do not press **Enter** to start the system just yet.


## Frontend Setup

### Step 3: Set Up the Angular Application

1. **Open the Angular Frontend Folder**: Open the frontend folder in your IDE of choice.

2. **Install Dependencies**: Run `npm install` in the terminal to install the necessary dependencies.

3. **Run the Application**: Start the Angular application by running `ng serve` in the terminal.

4. **Access the Application**: Open your browser and navigate to the Angular application.


### Step 4: Start the Program

- Once the Angular application is running and open in your browser, **press Enter** in the command-line interface (CLI) to start the system.

## Usage Instructions

1. Launch the backend and configure the ticket system by following the prompts.

2. Start the Angular front-end with `ng serve` and access the application via your browser.

3. Use the control buttons to manage the ticketing system operations.

4. Monitor ticket sales, vendor and customer activity, and logs via the front-end interface.




