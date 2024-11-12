package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {  // Set default values for the player (Location,Speed,Direction)
        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 15;
        speed = 4;
        direction = "idleDown"; // Set default animation to idle

        solidArea = new Rectangle();
        // Starting points of the solid area
        solidArea.x = 8;
        solidArea.y = 16;

        // Size of the solid area
        solidArea.width = 32;
        solidArea.height = 40;

    }

    public void getPlayerImage(){
        try{
            idleUp1 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleUp1.png"));
            idleUp2 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleUp2.png"));
            idleDown1 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleDown1.png"));
            idleDown2 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleDown2.png"));
            idleLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleLeft1.png"));
            idleLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleLeft2.png"));
            idleRight1 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleRight1.png"));
            idleRight2 = ImageIO.read(getClass().getResourceAsStream("/player/player_idleRight2.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_runUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_runUp2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_runDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_runDown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_runLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_runLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_runRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_runRight2.png"));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Check if our images are in the right folder!!!!");
            System.out.println("Check if getPlayerImage get called in the constructor!!!!");
        }
    }

    public void update() {
        int movingFrame;        // Declare the frame for the animation\

        if(keyH.shiftPressed) speed = 8;
        else speed = 4;

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            movingFrame = 10;  // Fast frame (1 picture per 10 frames)
            if(keyH.upPressed) {
                direction = "up";
            } else if(keyH.downPressed) {
                direction = "down";
            } else if(keyH.leftPressed) {
                direction = "left";
            } else if(keyH.rightPressed) {
                direction = "right";
            }
        }else{
            movingFrame = 25; //Slow frame (1 picture per 25 frames)
            if(keyH.idleUp) direction = "idleUp";
            if(keyH.idleDown) direction = "idleDown";
            if(keyH.idleLeft) direction = "idleLeft";
            if(keyH.idleRight) direction = "idleRight";

        }
        // Check collision on tiles
        collisionOn = false;
        gp.checker.checkTile(this);

        // If collision is false, player can move
        if(collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

            spriteCounter++;
            if(spriteCounter > movingFrame) {
                if (spriteNum == 1) {
                    spriteNum++;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }


    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1) image = up1;
                else image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                else image = down2;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                else image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                else image = right2;
                break;
            case "idleUp":
                if(spriteNum == 1) image = idleUp1;
                else image = idleUp2;
                break;
            case "idleDown":
                if(spriteNum == 1) image = idleDown1;
                else image = idleDown2;
                break;
            case "idleLeft":
                if(spriteNum == 1) image = idleLeft1;
                else image = idleLeft2;
                break;
            case "idleRight":
                if(spriteNum == 1) image = idleRight1;
                else image = idleRight2;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
