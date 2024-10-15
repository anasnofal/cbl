package main;
// test git hup
import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){

        this.gp = gp;
    }
    public void checkTile( Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1;

        int tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManger.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManger.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileManger.tile[tileNum1].collision
                    || gp.tileManger.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManger.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManger.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileManger.tile[tileNum1].collision
                    || gp.tileManger.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManger.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManger.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileManger.tile[tileNum1].collision
                    || gp.tileManger.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManger.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManger.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileManger.tile[tileNum1].collision
                    || gp.tileManger.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }




    }
}