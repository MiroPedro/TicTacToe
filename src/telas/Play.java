package telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
public class Play extends JFrame implements ActionListener, Constant {
    private int steps_counter;
    private int points_of_O;
    private int points_of_X;
    private boolean gameOver;
    private final String palyer_X = "X";
    private final String player_O = "O";
    private final JPanel points_panel;
    private String currentPlayer;
    private final JPanel mainPanel;
    private final Button [][] buttons;
    private final JPanel screenPanel;
    private final JPanel buttonsPanel;
    JPanel controlPanel;
    private final JLabel playerLabel;
    private final JLabel points_of_O_label;
    private final JLabel points_of_X_label;

    private final Button restart;
    private final Button exit;

    public Play(){
        steps_counter = 9;
        gameOver = false;
        points_of_O = 0;
        points_of_X = 0;
        playerLabel = new JLabel();
        screenPanel = new JPanel();
        points_of_X_label = new JLabel("Pontos de X: " + points_of_X);
        points_of_O_label = new JLabel("Pontos de O: " + points_of_O);
        buttons = new Button[3][3];
        exit = new Button("Sair");
        restart = new Button("Reiniciar");
        currentPlayer = (Math.random() < 0.5) ? palyer_X : player_O;
        points_panel = new JPanel();
        buttonsPanel = new JPanel();
        controlPanel = new JPanel();
        mainPanel = new JPanel();
    }

    public void executar(){
        player();
        pointsPanel();
        screenPanel();
        buttonsPanel();
        controlPanel();
        mainPanel();
    }
    private void mainPanel(){
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(screenPanel);
        mainPanel.add(buttonsPanel);
        mainPanel.add(controlPanel);
        cardPanel.add(mainPanel, "Play");
    }

    private JPanel [] getOcupp(){
        for(int i = 0; i < occupPanel.length; i++){
            JPanel panel = new JPanel();
            occupPanel[i] = panel;
            panel.setBackground(new Color(20, 110, 50));
        }

        return occupPanel;
    }

    private void player(){
        playerLabel.setText("Jogador " + currentPlayer);
        playerLabel.setForeground(Color.BLACK);
        playerLabel.setFont(new Font("ARIAL", Font.BOLD, 25));
    }

    private void pointsPanel(){
        points_of_X_label.setForeground(Color.BLACK);
        points_of_X_label.setFont(new Font("ARIAL", Font.BOLD, 15));
        points_of_O_label.setForeground(Color.BLACK);
        points_of_O_label.setFont(points_of_X_label.getFont());
        points_panel.setLayout(new BoxLayout(points_panel, BoxLayout.Y_AXIS));
        points_panel.setBackground(new Color(20, 110, 50));
        points_panel.add(getOcupp()[3]);
        points_panel.add(points_of_X_label);
        points_panel.add(getOcupp()[2]);
        points_panel.add(points_of_O_label);
        points_panel.add(getOcupp()[2]);
    }

    private void screenPanel(){
        screenPanel.setSize(500, 100);
        screenPanel.setBackground(new Color(20, 110, 50));
        screenPanel.setPreferredSize(new Dimension(width, 150));
        screenPanel.setLayout(new BorderLayout());
        screenPanel.add(playerLabel, BorderLayout.WEST);
        screenPanel.add(points_panel, BorderLayout.EAST);
    }

    private void buttonsPanel(){
        buttonsPanel.setLayout(new GridLayout(3, 3, 2, 2));
        buttonsPanel.setPreferredSize(new Dimension(500, 450));

        for(int lin = 0; lin < 3; lin ++){
            for(int col = 0; col < 3; col++){
                Button button = new Button("");
                buttons[lin][col] = button;
                buttonsPanel.add(button);
                button.addActionListener(this);
                button.setBackground(new Color(37, 48, 35));
            }
        }
    }

    private void controlPanel() {
        controlPanel.setBackground(new Color(37, 48, 35));

        restart.setForeground(new Color(37, 48, 35));
        restart.addActionListener(this);
        exit.setForeground(restart.getForeground());
        exit.addActionListener(this);
        controlPanel.setPreferredSize(new Dimension(500, 50));
        controlPanel.add(exit);
        controlPanel.add(restart);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(Button[] b: buttons){
            for(int i = 0; i < 3; i++){
                if(e.getSource() == b[i]){
                    steps_counter--;
                    if(gameOver){
                        return;
                    }
                    if(b[i].getText().isEmpty()){
                        b[i].setText(currentPlayer);
                        seeWinner();
                        if (!gameOver){
                            currentPlayer = (Objects.equals(currentPlayer, player_O)) ? palyer_X : player_O;
                            playerLabel.setText("Jogador " + currentPlayer);
                        }
                    }
                }
            }
        }

        if(e.getSource() == restart){
            restart();
        }
        if(e.getSource() == exit){
            exit();
        }
    }

    private void seeWinner(){
        //Linhas
        for(int i = 0; i < 3; i++){
            if (buttons[i][0].getText().isEmpty()) continue;
            if(Objects.equals(buttons[i][0].getText(), buttons[i][1].getText()) && Objects.equals(buttons[i][1].getText(), buttons[i][2].getText())){
                for(int j = 0; j < 3; j++){
                    setWinner(buttons[i][j]);
                }
                gameOver = true;
                addPoints();
                alertWinner();
                return;
            }
        }
        //Colunas
        for(int i = 0; i < 3; i++){
            if(buttons[0][i].getText().isEmpty()) continue;
            if (Objects.equals(buttons[0][i].getText(), buttons[1][i].getText()) && Objects.equals(buttons[1][i].getText(), buttons[2][i].getText())){
                for (int j = 0; j < 3; j++){
                    setWinner(buttons[j][i]);
                }
                gameOver = true;
                addPoints();
                alertWinner();
                return;
            }
        }

        //Diagonal principal

        if(Objects.equals(buttons[0][0].getText(), buttons[1][1].getText()) && Objects.equals(buttons[1][1].getText(), buttons[2][2].getText()) && !buttons[1][1].getText().isEmpty()){
            for (int i = 0; i < 3; i++){
                setWinner(buttons[i][i]);
            }
            gameOver = true;
            addPoints();
            alertWinner();
            return;
        }

        //Diagonal secundária
        if(Objects.equals(buttons[2][0].getText(), buttons[1][1].getText()) && Objects.equals(buttons[1][1].getText(), buttons[0][2].getText()) && !buttons[1][1].getText().isEmpty()){
            setWinner(buttons[2][0]);
            setWinner(buttons[1][1]);
            setWinner(buttons[0][2]);
            gameOver = true;
            addPoints();
            alertWinner();
            return;
        }

        //Em casos de empate
        if(steps_counter == 0){
            defEmpate();
            gameOver = true;
            return;
        }

    }
    private void setWinner(JButton b){
       b.setBackground(Color.RED);
       b.setForeground(Color.GREEN);
    }
    private void restart(){
        gameOver = false;
        steps_counter = 9;
        currentPlayer = (Math.random() < 0.5) ? palyer_X : player_O;
        playerLabel.setForeground(Color.BLACK);
        playerLabel.setText("Jogador " + currentPlayer);
        for(Button [] b : buttons){
            for(int i = 0; i < 3; i++){
                b[i].setText("");
                b[i].setBackground(new Color(37, 48, 35));
                b[i].setForeground(Color.WHITE);
            }
        }
    }
    private void exit(){
        restart();
        card.show(cardPanel, "Start");
    }

    private void addPoints(){

        if(currentPlayer.equalsIgnoreCase(player_O)){
            points_of_O += 1;
            points_of_O_label.setText("Pontos de O: " + (points_of_O));
        }
        if(currentPlayer.equalsIgnoreCase(palyer_X)){
            points_of_X += 1;
            points_of_X_label.setText("Pontos de X: " + points_of_X);
        }
    }

    private void defEmpate(){
        for(Button [] b: buttons){
            for(int i = 0; i < 3; i++){
                b[i].setBackground(Color.GRAY);
                b[i].setForeground(Color.WHITE);
            }
            playerLabel.setForeground(Color.GREEN);
            playerLabel.setText("Empate!");
        }
    }

    private void alertWinner(){
            playerLabel.setForeground(Color.GREEN);
            playerLabel.setText("Jogador " + currentPlayer + " venceu!");

    }
}
