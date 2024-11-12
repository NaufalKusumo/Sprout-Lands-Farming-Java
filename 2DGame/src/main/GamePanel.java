package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Settings of the Screen
    final int originalTileSize = 16;
    final int scale = 3;

    // 16 x 16 is too small for our current screen to we multiply it by 3
    public final int tileSize = originalTileSize * scale;  // 48 x 48 (actual tile size that will be displayed on our screen)

    // maximum number of columns and rows on the screen
    // 16 x 12 is the maximum number of tiles that can fit on the screen
    public final int maxScreenCol = 16; //Default 16
    public final int maxScreenRow = 12; //Default 12

    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576
    int FPS = 60; // FPS

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public final int worldWidth = tileSize * maxWorldCol; // 2400
    public final int worldHeight = tileSize * maxWorldRow; // 1800

    TileManager tileM = new TileManager(this);
    Thread gameThread; //Can be used to start and stop the game. because we want to repeat some process again and again (drawing screen 60 times per second)
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker checker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    // Set player's default position
    public int playerX = 100;
    public int playerY = 100;
    public int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));    // Set the preferred size of the panel to the size of the screen that will be displayed on the user's computer
        this.setBackground(Color.BLACK); // This is the color that will be displayed on the screen, before any graphics are drawn
        this.setDoubleBuffered(true); // This is a technique used to reduce screen flickering

        // Set the focus to the panel
        // Focus is a state that means the component is ready to receive user input
        // In this case, we are setting the focus to the panel
        // So that the user can control the game
        this.setFocusable(true);
        requestFocus(); // It makes the component ready to receive user input
        this.addKeyListener(keyH); // So this gamePanel can recognize keyInput

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS; // 1 second divided by FPS (We are dividing one billion nanoseconds by FPS)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // We change the graphics to Graphics2D, because graphics2D has more features
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}












//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS; // 1 second divided by FPS (We are dividing one billion nanoseconds by FPS)
//        double nextDrawTime = System.nanoTime() + drawInterval; //
//
//        //Game Loop
//        while(gameThread != null) {
//
//            long currentTime = System.nanoTime();
//
//            //Update the game (Like moving the player)
//            update();
//            //Paint the game (like drawing the player on the screen)
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; //Convert remainingTime to milliseconds, because sleep accept only miliseconds
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime);
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
