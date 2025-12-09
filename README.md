# Cows and Bulls

This project is a color-based variation of the classic “Cows and Bulls” logic game.  
The computer generates a hidden combination of colored pegs, and the player attempts to guess the correct sequence and positions.

## How the Game Works
- The computer randomly selects a sequence of colors.
- The player submits guesses until the correct sequence is found.
- After every guess, feedback is returned:
  - **Bulls** — correct color in the correct position  
  - **Cows** — correct color in the wrong position
 
## AI Component
The game includes a simple AI logic layer:  
after each player guess, the computer analyzes the Cows/Bulls feedback and eliminates all color combinations that do not match the history of previous clues.  
This creates a constraint-based reasoning system, where the AI “learns” from every round and continuously narrows down the valid solution space.


## Features
- Player vs. computer logic challenge
- Random color combination generation
- Input validation for legal guesses
- Immediate feedback (Cows/Bulls) after each attempt
- Play continues until the player finds the exact combination

## Technologies
- HTML  
- CSS  
- JavaScript  

## Project Structure
A simple, frontend-only project:
- No backend
- No build tools
- Runs directly in the browser

## How to Run
Open the main HTML file in any browser:
index.html
or run locally with a simple static server:

```bash
npx http-server .
