package SnakeGame;
import SnakeGame.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class UIPanel extends JPanel implements ActionListener{
    
    private SnakeFrame frame;
    private SnakePanel panel;
    private Timer timer;
    private int score = 0;
    private int timeAlive = 0;
    private JButton quitButton = new JButton("Quit");
    private JButton restartButton = new JButton("Restart");
    private JLabel scoreLabel = new JLabel();
    private JLabel timeLabel = new JLabel();
    private JTextArea instruction = new JTextArea("How to Play: Arrows Keys to move and press Enter to start. Collect apples to grow larger and get 100 to win!");
    private Font mainFont = new Font("Serif", Font.PLAIN, 28);
    private Font smallFont = new Font("Serif", Font.PLAIN, 20);
    
    UIPanel(SnakeFrame frame, SnakePanel panel) {
        this.frame = frame;
        this.panel = panel;
        this.setPreferredSize(new Dimension(250, SnakePanel.HEIGHT));
        this.setBackground(Color.GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        configureComponents();
        timer = new Timer(50, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e ) {
        score = panel.getScore();
        timeAlive = (int) panel.getTime();
        scoreLabel.setText("Score: " + Integer.toString(score));
        timeLabel.setText("Time: " + Integer.toString(timeAlive));
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
        add(restartButton);
        add(quitButton);
    }
}
