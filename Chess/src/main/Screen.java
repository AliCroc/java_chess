package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Screen extends JPanel implements Runnable { // Runnable - wątkowanie
    public static final int _WIDTH = 1100;
    public static final int _HEIGHT = 800;
    final int _FPS = 10;
    Thread gameThread;
    Board board = new Board();
    
    public Screen() {
        setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
        setBackground(Color.black);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    public void run() {
        // pętla gry (Game loop)
        double drawInterval = 1000000000/_FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {

    }

    @Override
    protected void paintComponent(Graphics c) {
        super.paintComponent(c);
        Graphics2D g = (Graphics2D)c;
        board.draw(g);
    }
}
