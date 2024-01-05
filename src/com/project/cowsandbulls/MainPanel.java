package com.project.cowsandbulls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private Image image;
    private JButton button1;
    private JButton button2;
    private JButton insButton;
    private JFrame myFrame;

    public MainPanel(JFrame frame) {
        myFrame = frame;
        image = new ImageIcon("Images/patut2.png").getImage();
        button1 = new JButton("Start Game");
        insButton = new JButton("INSTRUCTION");
        setLayout(null);
        myButtons();
    }

    @Override
    public void paintComponent(Graphics g) {
        // Draw the image on the panel
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    private void myButtons()
    {
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setFocusable(false);
        button1.setBounds(200, 500, 150, 50);
        button1.setFont(new Font("Arial", Font.ITALIC, 25));
        button1.setForeground(new Color(236, 11, 167));
        button1.setBorder(BorderFactory.createLineBorder(new Color(231, 115, 0), 4));
        button1.addActionListener(this);

        insButton.setOpaque(false);
        insButton.setContentAreaFilled(false);
        insButton.setFocusable(false);
        insButton.setBounds(400, 500, 190, 50);
        insButton.setFont(new Font("Arial", Font.ITALIC, 25));
        insButton.setForeground(new Color(236, 11, 167));
        insButton.setBorder(BorderFactory.createLineBorder(new Color(231, 115, 0), 4));
        insButton.addActionListener(this);

        add(button1);
        add(insButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == insButton) {
            myFrame.dispose();
            new InstructionWindow().setVisible(true);
        }
        else if(e.getSource() == button1) {
            myFrame.dispose();
            new CowsAndBulls();
        }

    }
}
