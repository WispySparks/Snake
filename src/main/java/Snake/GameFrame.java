package main.java.Snake;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
    GameFrame() {
        GamePanel panel = new GamePanel();
        UIPanel sidepanel = new UIPanel(this, panel);
        this.setLayout(new BorderLayout());     //layout for having multiple panels
        this.add(panel, BorderLayout.WEST);
        this.add(sidepanel, BorderLayout.EAST);
        this.addKeyListener(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}