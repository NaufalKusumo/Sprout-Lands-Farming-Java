package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY;
    public int speed;

    public BufferedImage idleUp1, idleUp2, idleDown1, idleDown2, idleLeft1, idleLeft2, idleRight1, idleRight2;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea; // Set invisible rectangle for collision
    public boolean collisionOn = false;
}
