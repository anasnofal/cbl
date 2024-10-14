package main;

import java.awt.*;
import javax.swing.*;

import Tile.TileManger;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; // 16*16 TILE
    int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = maxScreenCol * tileSize;
    public int screenHeight = tileSize * maxScreenRow;

    // world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    final int fps = 60;

    keyHandle keyHandler = new keyHandle();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    TileManger tileManger = new TileManger(this);
    

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            

        }
    }

    public void update(){
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManger.draw(g2);
        player.draw(g2);
        
        g2.dispose();
    }

}