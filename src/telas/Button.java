package telas;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setFocusable(false);
        setBackground(new Color(149, 177, 196));
        setFont(new Font("Arial", Font.BOLD, 30));
        setForeground(Color.WHITE);
    }
}
