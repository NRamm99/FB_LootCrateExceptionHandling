# LootCrate System (Java)

A small Java assignment demonstrating exception handling and basic OOP through a simple lootcrate system.

## Getting Started

This project shows how to use custom exceptions, `try-catch`, and object-oriented structure in Java.  
The system includes players, lootcrates, and multiple error scenarios handled safely.

## Folder Structure

The workspace is organized as follows:

- `src/filehandler`: reads and writes to .txt files
- `src/exceptions`: custom exception classes  
- `src/models`: core model classes (`Player`, `LootCrate`, `Item`)  
- `src/LootCrateSystem.java`: main class for testing the system  

## Exception Overview

The project uses three custom exceptions:

- `NotEnoughCreditsException` (checked): thrown when a player cannot afford a crate  
- `NegativeAmountException` (unchecked): thrown when adding negative credits  
- `PlayerNotFoundException` (checked): thrown when searching for a missing player  

## Demonstrated Scenarios

The main program tests:

- Opening a crate successfully  
- Opening a crate without enough credits  
- Adding negative credits  
- Attempting actions on a non-existing player  

All handled using proper exception handling.

