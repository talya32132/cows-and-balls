package com.project.cowsandbulls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionWindow extends JFrame {
    private JButton closeButton;

    public InstructionWindow() {
        setTitle("Instructions");

        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon("Images/imghoraot.jpg");
        label.setIcon(imageIcon);

        getContentPane().add(label);
        setSize(605, 775);
        setLocationRelativeTo(null);

        closeButton = new JButton("Let's play");
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusable(false);
        closeButton.setBounds(300, 400, 200, 50);
        closeButton.setFont(new Font("Arial", Font.ITALIC, 25));
        closeButton.setForeground(Color.white);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // סגירת החלון הנוכחי

                // פתיחת המשחק - אם CowsAndBulls הוא המחלקה הנכונה של המשחק
                new CowsAndBulls();
            }
        });
        add(closeButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
