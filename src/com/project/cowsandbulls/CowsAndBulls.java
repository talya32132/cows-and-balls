package com.project.cowsandbulls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;

public class CowsAndBulls extends JFrame implements ActionListener, MouseListener {
    private Piece[] secretCodeLabels;
    private JPanel guessesPanel;
    private JPanel resultsPanel;
    private JButton submitGuess;
    private Color[] colors;
    private Color[] secret;
    private int currentPosition = 0;
    private int currentGuess = 0;
    private final int maxGuesses = 10;
    private Piece[][] guessLabels;
    private Piece[][] resultLabels;

    private AISolver aiSolver;

    private boolean toggleShowGuess = false;

    public CowsAndBulls() {
        initialize();
        aiSolver = new AISolver(ServiceClass.colors);

        setVisible(true);
    }

    private void initialize() {

        colors = Arrays.copyOf(ServiceClass.colors, ServiceClass.colors.length);
        // shuffle colors
        secret = generateSecretCode();

        setTitle("Color Cows and Bulls");
        getContentPane().setBackground(new Color(218, 202, 148));
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        secretCodeLabels = new Piece[4];
        for (int i = 0; i < secretCodeLabels.length; i++) {
            secretCodeLabels[i] = new Piece(new Color(218, 202, 148));
            secretCodeLabels[i].setBounds(40 + i * 50, 35, 40, 40);
            secretCodeLabels[i].setOpaque(true);
        //    secretCodeLabels[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            getContentPane().add(secretCodeLabels[i]);
            secretCodeLabels[i].setVisible(false);
        }

        guessesPanel = new JPanel();
        guessesPanel.setBackground(new Color(218, 202, 148));
    //    JLabel guessesPanelBackground = new JLabel(new ImageIcon("images/guesses.png"));
    //    guessesPanelBackground.setBounds(0,0, 200,400);
    //    guessesPanel.add(guessesPanelBackground);
        guessesPanel.setLayout(new GridLayout(maxGuesses, 4, 5, 5));
        guessesPanel.setBounds(35, 85, 200, 400);
        getContentPane().add(guessesPanel);
        guessLabels = new Piece[maxGuesses][4];
        for (int i = 0; i < maxGuesses; i++) {
            for (int j = 0; j < 4; j++) {
                guessLabels[i][j] = new Piece(new Color(218, 202, 148));
                guessLabels[i][j].setOpaque(true);
                if (i % 2 == 0)
                    guessLabels[i][j].setBorder(BorderFactory.createLineBorder(new Color(49, 75, 163)));
                else
                     guessLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                guessesPanel.add(guessLabels[i][j]);
            }
        }

        resultsPanel = new JPanel();
        resultsPanel.setBackground(new Color(218, 202, 148));
        resultsPanel.setLayout(new GridLayout(maxGuesses * 2, 2, 20, 10));
        resultsPanel.setBounds(250, 90, 50, 390);
        getContentPane().add(resultsPanel);
        resultLabels = new Piece[maxGuesses][4];
        for (int i = 0; i < maxGuesses; i++) {
            for (int j = 0; j < 4; j++) {
                resultLabels[i][j] = new Piece(new Color(218, 202, 148));
                resultLabels[i][j].setOpaque(true);
                if (i % 2 == 0)
                    resultLabels[i][j].setBorder(BorderFactory.createLineBorder(new Color(70, 88, 153)));
                else
                    resultLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                resultsPanel.add(resultLabels[i][j]);
            }
        }

        for (Color color : colors) {
            Piece colorButton = new Piece(new Color(218, 202, 148));

           // colorButton.setBackground(getColorFromColorString(color));
            Color colorName = color;

            colorButton.setPieceIcon(colorName, 40);
            colorButton.setBounds(500 + Arrays.asList(colors).indexOf(color) * 50, 85, 40, 40);
            colorButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (currentPosition < 4) {
                        guessLabels[currentGuess][currentPosition].setColor(color);
                        guessLabels[currentGuess][currentPosition].setPieceIcon(colorName, 30);
                        currentPosition++;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            getContentPane().add(colorButton);
        }

        submitGuess = new JButton("Submit Guess");
        submitGuess.setBounds(500, 150, 200, 40);
        submitGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPosition == 4) {
                    Color[] playerGuess = new Color[4];
                    int i = 0;
                    for (Piece label : guessLabels[currentGuess]) {
                        playerGuess[i] = label.getColor();
                        i++;
                    }
                    int[] output = calculateBullsCows(playerGuess, secret);
                    displayResults(output);
                    currentPosition = 0;
                    currentGuess++;
                    if (output[0] == 4 || currentGuess == maxGuesses) {
                     //   submitGuess.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(CowsAndBulls.this, "Please select 4 colors.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton showGuess = new JButton("Show Secret Code");
        showGuess.setBounds(500, 30, 200, 40);
        add(showGuess);
        showGuess.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                for (Piece label : secretCodeLabels) {
                    if (toggleShowGuess)
                        label.setVisible(false);
                    else
                        label.setVisible(true);
                }
                if (toggleShowGuess) {
                    toggleShowGuess = false;
                    showGuess.setText("Show Secret Code");
                } else {
                    toggleShowGuess = true;
                    showGuess.setText("Hide Secret Code");
                }
            }
        });

        /*
        AI Solver
         */
        JButton aiGuessButton = new JButton("AI Guess");
        aiGuessButton.setBounds(500, 200, 200, 40);
        getContentPane().add(aiGuessButton);

        aiGuessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentGuess < maxGuesses) {
                    Color[] aiGuess = aiSolver.makeGuess();
                    int[] output = calculateBullsCows(aiGuess, secret);

                    for (int i = 0; i< 4; i++) {
                        guessLabels[currentGuess][i].setColor(colors[i]);
                        guessLabels[currentGuess][i].setPieceIcon(aiGuess[i], 30);
                    }        displayResults(output);
                    currentPosition = 0;
                    currentGuess++;

                    if (output[0] == 4 || currentGuess == maxGuesses) {
                      //  submitGuess.setEnabled(false);
                    } else {
                        aiSolver.updatePossibleCombinations(aiGuess, output[0], output[1]);
                    }
                } else {
                    JOptionPane.showMessageDialog(CowsAndBulls.this, "No more guesses left.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // shuffle again colors, and remove 4 colors
        secret = Arrays.copyOf(colors, colors.length);
        secret = generateSecretCode();

        getContentPane().add(submitGuess);
        for (int i = 0; i < secretCodeLabels.length; i++) {
            secretCodeLabels[i].setPieceIcon(secret[i], 30);
        }
    }


    private Color[] generateSecretCode() {
        Collections.shuffle(Arrays.asList(colors));
        return colors;
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
    private void displayResults(int[] output) {
        for (int i = 0; i < 4; i++) {
            resultLabels[currentGuess][i].setText("");
            if (i < output[0]) {
                resultLabels[currentGuess][i].setBackground(Color.GREEN);
            } else if (i < output[0] + output[1]) {
                resultLabels[currentGuess][i].setBackground(Color.YELLOW);
            } else {
                resultLabels[currentGuess][i].setBackground(Color.LIGHT_GRAY);
            }
        }
        if (output[0] == 4 || currentGuess == maxGuesses - 1) {
          //  submitGuess.setEnabled(false);
            String message = (output[0] == 4) ? "Congratulations! You won!" : "You lost! Better luck next time!";
            Object[] options = {"New Game", "Close"};

            int selectedOption = JOptionPane.showOptionDialog(this, message, "Game Over",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (selectedOption == 0) {
                startNewGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void startNewGame() {
        // Reset game state
        currentPosition = 0;
        currentGuess = -1;
      //  submitGuess.setEnabled(true);

        // Clear guess and result labels
        for (int i = 0; i < maxGuesses; i++) {
            for (int j = 0; j < 4; j++) {
                guessLabels[i][j].setColor(Color.WHITE);
                guessLabels[i][j].setPieceIcon(null, 30);

                resultLabels[i][j].setBackground(Color.WHITE);
                resultLabels[i][j].setText("");
            }
        }

        // Reset the secret code labels
        secret = generateSecretCode();
        for (int i = 0; i < secretCodeLabels.length; i++) {
            secretCodeLabels[i].setPieceIcon(secret[i], 30);
        }

        // Reset AI solver
        aiSolver = new AISolver(ServiceClass.colors);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}




