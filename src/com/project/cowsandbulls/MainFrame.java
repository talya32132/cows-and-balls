package com.project.cowsandbulls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    public MainFrame()  {
        //שינויים במסך
        super("Cows And Bulls");
        ImageIcon logo = new ImageIcon("Images/patut2.png");//יצירת אובייקט תמונה
        this.setIconImage(logo.getImage());//הוספת תמונה לאייקון מצד שמאל למעלה
        MainPanel myPanel = new MainPanel(this);//רקע
        add(myPanel, BorderLayout.CENTER);// Add the MyPanel panel to the center of the content pane


        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 620);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
}