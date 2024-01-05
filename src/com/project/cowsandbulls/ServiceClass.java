package com.project.cowsandbulls;

import javax.swing.*;
import java.awt.*;

public class ServiceClass {

    public static ImageIcon getIcon(Color color, int size) {
        String iconName;
        if (color == null) {
            return null;
        } else if (color.equals(Color.RED)) {
            iconName = "red";
        } else if (color.equals(Color.GREEN)) {
            iconName = "green";
        } else if (color.equals(Color.BLUE)) {
            iconName = "blue";
        } else if (color.equals(Color.YELLOW)) {
            iconName = "yellow";
        } else if (color.equals(Color.ORANGE)) {
            iconName = "orange";
        }
        else if (color.equals(PURPLE)) {
            iconName = "purple";
        }
        else {
            iconName = "red";
        }
        ImageIcon icon = new ImageIcon("Images/" + iconName + ".png");
        Image image = icon.getImage(); // transform it
        image = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(image);  // transform it back
        return icon;
    }

    public static Color PURPLE = new Color(91, 33, 122);

    public static Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE,
            PURPLE};

}
