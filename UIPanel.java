package SnakeGame;
import SnakeGame.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class UIPanel extends JPanel implements ActionListener{
    
    SnakeFrame frame;
    SnakePanel panel;
    Timer timer;
    int score = 0;
    double timeAlive = 0;
    JButton quitButton = new JButton("Quit");
    JLabel scoreLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    Font myFont = new Font("Serif", Font.PLAIN, 28);
    
    UIPanel(SnakeFrame frame, SnakePanel panel) {
        this.frame = frame;
        this.panel = panel;
        this.setPreferredSize(new Dimension(250, SnakePanel.HEIGHT));
        this.setBackground(Color.GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        quitButton.addActionListener(this);
        quitButton.setActionCommand("Quit");
        scoreLabel.setFont(myFont);
        timeLabel.setFont(myFont);
        this.add(quitButton);
        this.add(scoreLabel);
        this.add(timeLabel);
        timer = new Timer(50, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e ) {
        score = panel.getScore();
        timeAlive = panel.getTime();
        int roundTime = (int) timeAlive;
        scoreLabel.setText("Score: " + Integer.toString(score));
        System.out.println(timeAlive);
        if (roundTime % 1 == 0) {
            timeLabel.setText("Time: " + Integer.toString(roundTime));
        }
        if (e.getActionCommand() == "Quit") {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
