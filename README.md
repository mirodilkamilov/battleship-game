# Battleship Game

A simple console-based implementation of the classic Battleship game in Java. The objective is to locate and destroy all hidden ships on a 7x7 game board using as few guesses as possible.

## Features

- Randomized placement of 3 battleships (horizontal or vertical).
- Visual representation of the game board with feedback for hits, misses, and destroyed ships.
- Input validation for coordinates.
- Game status updates after each guess.

## How to Play

1. Clone or download the repository.
2. Compile and run the `BattleshipGame` class using a Java compiler.
3. Enter your guesses as coordinates in the format `A1`, `B5`, etc.
4. The game provides feedback:
    - `.` - Unknown cell
    - `O` - Miss
    - `*` - Hit
    - `X` - Destroyed (all parts of a ship have been hit)
5. Continue guessing until all ships are destroyed.

![Screenshot from the game](Screenshot%20from%20the%20game.png)


## Setup Instructions

1. Ensure you have Java installed on your machine.
2. Place all Java files (`BattleshipGame.java`, `Battleship.java`, `BoardStatusType.java`) in the same directory.
3. Compile and run the program:
   ```bash
   javac *.java
   java BattleshipGame
   
## Create for yourself

The `create-for-yourself` branch contains a boilerplate/template code for anyone who wants to try building this game from scratch. This is a great starting point for learning and practicing Java. It covers core language syntax up to Object-Oriented Programming (OOP) principles. Once you finish, you can compare with my implementation in `main` branch