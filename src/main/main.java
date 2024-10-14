package main;

// Example of FlowLayout -- resize the window and see what happens
import javax.swing.JFrame;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("game test");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
    }


}
