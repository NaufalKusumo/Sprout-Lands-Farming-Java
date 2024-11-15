package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, idleDown, idleUp, idleLeft, idleRight, shiftPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) {
            upPressed = true;
            idleUp = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            downPressed = true;
            idleDown = false;
        }
        if(keyCode == KeyEvent.VK_A) {
            leftPressed = true;
            idleLeft = false;
        }
        if(keyCode == KeyEvent.VK_D) {
            rightPressed = true;
            idleRight = false;
        }
        if(keyCode == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) {
            upPressed = false;
            idleUp = true;
            idleDown = idleLeft = idleRight = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            downPressed = false;
            idleDown = true;
            idleUp = idleLeft = idleRight = false;
        }
        if(keyCode == KeyEvent.VK_A) {
            leftPressed = false;
            idleLeft = true;
            idleUp = idleDown = idleRight = false;
        }
        if(keyCode == KeyEvent.VK_D) {
            rightPressed = false;
            idleRight = true;
            idleUp = idleDown = idleLeft = false;
        }
        if(keyCode == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
    }
}
