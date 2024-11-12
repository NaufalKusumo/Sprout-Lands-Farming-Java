package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    int[][] baseMapTileNum;
    public int[][] overlayMapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        baseMapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        overlayMapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadBaseMap("/maps/base_map_50x50.txt");
        loadOverlayMap("/maps/overlay_map_50x50.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassCornerTopLeft.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassTop.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassCornerTopRight.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassLeft.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassRight.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassCornerBottomLeft.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassBottom.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassCornerBottomRight.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBaseMap(String mapPath) {
        loadMap(mapPath, baseMapTileNum);
    }

    public void loadOverlayMap(String mapPath) {
        loadMap(mapPath, overlayMapTileNum);
    }

    private void loadMap(String mapPath, int[][] mapTileNum) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine(); // Read line
                String[] numbers = line.split(" "); // Split by spaces

                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        drawLayer(g2, baseMapTileNum);   // Draw base layer first
        drawLayer(g2, overlayMapTileNum); // Draw overlay layer on top
    }

    private void drawLayer(Graphics2D g2, int[][] mapTileNum) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Only draw if the tile is defined
            if (tileNum >= 0 && tile[tileNum] != null) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
