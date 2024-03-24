package main.java.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class UIPanel extends JPanel implements ActionListener {
    
    private GameFrame frame;
    private GamePanel panel;
    private Timer timer;
    private int score = 0;
    private int timeAlive = 0;
    private int wins = 0;
    private JButton quitButton = new JButton("Quit");
    private JButton restartButton = new JButton("Restart");
    private JLabel scoreLabel = new JLabel();
    private JLabel timeLabel = new JLabel();
    private JLabel winsLabel = new JLabel();
    private JTextArea instruction = new JTextArea("How to Play: Arrows Keys to move and press Enter to start. Collect apples to grow larger and get 100 to win!");
    private Font mainFont = new Font("Serif", Font.PLAIN, 28);
    private Font smallFont = new Font("Serif", Font.PLAIN, 20);
    
    UIPanel(GameFrame frame, GamePanel panel) {
        this.frame = frame;
        this.panel = panel;
        this.setPreferredSize(new Dimension(250, GamePanel.HEIGHT));
        this.setBackground(Color.GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        configureComponents();
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e ) {
        score = panel.getScore();
        timeAlive = (int) panel.getTime();
        wins = panel.getWins();
        scoreLabel.setText("Score: " + Integer.toString(score));
        timeLabel.setText("Time: " + Integer.toString(timeAlive));
        winsLabel.setText("Wins: " + Integer.toString(wins));
        if (e.getActionCommand() == "Quit") {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getActionCommand() == "Restart") {
            panel.setup();
        }
    }

    public void configureComponents() {
        quitButton.addActionListener(this);
        quitButton.setActionCommand("Quit");
        quitButton.setFont(mainFont);
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        quitButton.setFocusable(false);
        restartButton.addActionListener(this);
        restartButton.setActionCommand("Restart");
        restartButton.setFont(mainFont);
        restartButton.setAlignmentX(CENTER_ALIGNMENT);
        restartButton.setFocusable(false);
        scoreLabel.setFont(mainFont);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setFocusable(false);
        timeLabel.setFont(mainFont);
        timeLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeLabel.setFocusable(false);
        winsLabel.setFont(mainFont);
        winsLabel.setAlignmentX(CENTER_ALIGNMENT);
        winsLabel.setFocusable(false);
        instruction.setFont(smallFont);
        instruction.setLineWrap(true);
        instruction.setWrapStyleWord(true);
        instruction.setEditable(false);
        instruction.setHighlighter(null);
        instruction.setOpaque(false);
        instruction.setFocusable(false);
        add(scoreLabel);
        add(timeLabel);
        add(instruction);
        add(winsLabel);
        add(restartButton);
        add(quitButton);
    }
    
}