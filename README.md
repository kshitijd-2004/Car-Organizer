# Car Organizer

Car Organizer is an application designed to list cars based on their mileages from a CSV file, with sorting and filtering capabilities according to user input.

## Technologies

The Car Organizer project is developed using the following technologies:

1. **Java 8 (JDK 20.0.1):** The core programming language used to build the application.

2. **JUnit 4 / JUnit 5.8.1:** The testing framework used for writing and executing unit tests.

## Overview

Car Organizer utilizes a Red-Black Tree data structure, a specialized form of Binary Search Tree, to efficiently organize and sort cars. The primary sorting criteria are based on the year of make. In the event that two cars have the same year, they are further sorted based on their brand and model.

## How It Works

1. **CSV Input:** To get started with Car Organizer, provide a CSV file containing information about various cars. Each row of the CSV file represents a car, and it must contain the following fields:
   - Brand
   - Model
   - Year of Make
   - Price
   - Mileage

2. **User Input:** The application prompts the user to enter specific criteria for listing cars. The user can:
   - Load CSV file containing list of cars.
   - Filter cars with mileages less than a certain value
   - Filter cars with mileages equal to or greater than a certain value
   
3. **Results:** After user input is processed, the Car Organizer will generate a list of cars that match the specified criteria. This list will be displayed to the user.

## Getting Started

To run the Car Organizer application on your local machine, follow these steps:

1. Clone the repository to your local environment:

   https://github.com/kshitijd-2004/Car-Organizer.git

2. Compile and run the program:

`javac CarInterface.java`

`javac BackendInterface.java`

`javac Backend.java`

`javac Frontend.java`

`java Backend.java`

4. Follow the on-screen prompts to load your CSV file and specify the criteria for listing cars.

## Contributions

Contributions to the Car Organizer project are welcome. If you have any ideas, improvements, or bug fixes, feel free to fork the repository, make your changes, and submit a pull request.

