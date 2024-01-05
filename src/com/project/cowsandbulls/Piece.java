package com.project.cowsandbulls;

import javax.swing.*;
import java.awt.*;

public class Piece extends JLabel {

        private Color color;
        private ImageIcon icon;

        public Piece(Color color) {
            this.color = color;
            setBackground(color);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public ImageIcon getIcon() {
            return icon;
        }

        public void setPieceIcon(Color iconName, int size) {
            ImageIcon icon = ServiceClass.getIcon(iconName, size);
            this.icon = icon;
            this.setIcon(icon);
        }
}
