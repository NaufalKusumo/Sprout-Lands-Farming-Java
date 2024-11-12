package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }
    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCollisionX = entityLeftWorldX / gp.tileSize;
        int entityRightCollisionX = entityRightWorldX / gp.tileSize;
        int entityTopCollisionY = entityTopWorldY / gp.tileSize;
        int entityBottomCollisionY = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopCollisionY = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.overlayMapTileNum[entityLeftCollisionX][entityTopCollisionY];
                tileNum2 = gp.tileM.overlayMapTileNum[entityRightCollisionX][entityTopCollisionY];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomCollisionY = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.overlayMapTileNum[entityLeftCollisionX][entityBottomCollisionY];
                tileNum2 = gp.tileM.overlayMapTileNum[entityRightCollisionX][entityBottomCollisionY];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCollisionX = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.overlayMapTileNum[entityLeftCollisionX][entityTopCollisionY];
                tileNum2 = gp.tileM.overlayMapTileNum[entityLeftCollisionX][entityBottomCollisionY];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCollisionX = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.overlayMapTileNum[entityRightCollisionX][entityTopCollisionY];
                tileNum2 = gp.tileM.overlayMapTileNum[entityRightCollisionX][entityBottomCollisionY];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
