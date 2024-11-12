package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); // To create the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // To make the window close
        window.setResizable(false); // To make it not resizable
        window.setTitle("Projek Responsi Game 2D");

        // Game Panel is a class that will be added to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // To add the gamePanel to the window
        gamePanel.startGameThread();

        window.pack();


        window.setLocationRelativeTo(null); // To make it appear in the middle of the screen
        window.setVisible(true); // To make it visible
    }
}