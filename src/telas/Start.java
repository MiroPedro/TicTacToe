package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame implements Constant, ActionListener {
    private JPanel startPanel;
    private Button startButton;
    private JPanel buttonPanel;
    public Start(){
       includeComponents();
    }

    public void includeComponents(){
        for(int i = 0; i < 5; i++){
            JPanel panel = new JPanel();
            occupPanel[i] = panel;
            panel.setBackground(new Color(100, 150, 100));
        }
        startButton = new Button("Jogar");
        startButton.setForeground(new Color(37, 48, 35));
        startButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));



        buttonPanel.add(occupPanel[0]);
        buttonPanel.add(startButton);
        buttonPanel.add(occupPanel[1]);

        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startPanel.setBackground(occupPanel[0].getBackground());
        startPanel.add(buttonPanel, BorderLayout.SOUTH);
        cardPanel.add( startPanel, "Start");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startButton = (Button) e.getSource();
        card.show(cardPanel, "Play");
    }
}
