package Snake;

import javax.swing.JFrame;
import java.awt.*;

public class SnakeFrame extends JFrame{
    SnakeFrame() {
        SnakePanel panel = new SnakePanel();
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