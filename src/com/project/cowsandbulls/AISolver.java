package com.project.cowsandbulls;

/*
 AI class that can solve the Cows and Bulls game in the given code,
  we need to develop a strategy for the AI to guess the secret code.
 One approach is to use a simple elimination method.
 The AI will generate all possible combinations and remove the combinations that are not
   consistent with the guesses made so far and the corresponding results (bulls and cows).

 The AI can make guesses by clicking the "AI Guess" button.
 When you click the button, the AI will make a guess based
 on the possible combinations it has generated.
 After each guess, the AI will eliminate combinations that are inconsistent with the feedback (bulls and cows) received.
 This process will continue until the AI solves the puzzle or reaches the maximum number of guesses.

 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AISolver {
    private List<Color[]> possibleCombinations = new ArrayList<>();
    private Color[] colors;

    public AISolver(Color[] colors) {
        this.colors = colors;
        generateAllCombinations();
    }

    private void generateAllCombinations() {
        possibleCombinations = new ArrayList<>();
        for (Color color1 : colors) {
            for (Color color2 : colors) {
                for (Color color3 : colors) {
                    for (Color color4 : colors) {
                        if (!color1.equals(color2) && !color1.equals(color3) && !color1.equals(color4) && !color2.equals(color3) &&
                                !color2.equals(color4) && !color3.equals(color4)) {
                            Color[] combination = {color1, color2, color3, color4};
                            possibleCombinations.add(combination);
                        }
                    }
                }
            }
        }
    }

    public Color[] makeGuess() {
        return possibleCombinations.get(0);
    }

    public void updatePossibleCombinations(Color[] guess, int bulls, int cows) {
        List<Color[]> newPossibleCombinations = new ArrayList<>();

        for (Color[] combination : possibleCombinations) {
            int[] result = calculateBullsCows(guess, combination);
            if (result[0] == bulls && result[1] == cows) {
                newPossibleCombinations.add(combination);
            }
        }
        possibleCombinations = newPossibleCombinations;
    }

    private int[] calculateBullsCows(Color[] guess, Color[] secret) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i].equals(secret[i])) {
                bulls++;
            } else if (Arrays.asList(guess).contains(secret[i]) && !guess[i].equals(secret[i])) {
                cows++;
            }
        }
        return new int[]{bulls, cows};
    }
}