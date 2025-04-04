package entity;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO; 
import java.io.IOException;
import main.GamePanel;
import main.keyHandle;

public class Player extends Entity {

    GamePanel gp;
    keyHandle keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, keyHandle keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle(8, 16, 28, 28);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    
    }

    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        
        if (keyHandler.upPressed || keyHandler.downPressed
            || keyHandler.leftPressed || keyHandler.rightPressed) {
            
        
            if (keyHandler.upPressed) {
                direction = "up";
                
            } else if (keyHandler.downPressed) {
                direction = "down";
                
            } else if (keyHandler.leftPressed) {
                direction = "left";
                
            } else if (keyHandler.rightPressed) {
                direction = "right";
                
            }
            collisionOn = false;

            gp.cChecker.checkTile(this);
            // if collision is false, player can move
            if (!collisionOn) {
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

            spritCounter++;
            if (spritCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spritCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {

            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                } 
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                    break;
                }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

}