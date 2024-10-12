package telas;

import javax.swing.*;

public class Window extends JFrame implements Constant{

    public Window(){
        setTitle("Tic Tac Toe");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        Start start = new Start();
        Play play = new Play();
        play.executar();

        add(cardPanel);
        setVisible(true);
    }
}
