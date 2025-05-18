package main;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

//piece import
import piece.Piece;
import piece.Pionek;
import piece.Wieza;
import piece.Goniec;
import piece.Hetman;
import piece.Krol;
import piece.Skoczek;

public class Screen extends JPanel implements Runnable { // Runnable - wątkowanie
    public static final int _WIDTH = 1200;
    public static final int _HEIGHT = 800;
    final int _FPS = 10;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();


    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;


    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> symulPieces = new ArrayList<>();
    Piece activePiece;
    
    //// SETUP
    public Screen() {
        setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, symulPieces);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {
        // białe
        pieces.add(new Pionek(WHITE, 0, 6));
        pieces.add(new Pionek(WHITE, 1, 6));
        pieces.add(new Pionek(WHITE, 2, 6));
        pieces.add(new Pionek(WHITE, 3, 6));
        pieces.add(new Pionek(WHITE, 4, 6));
        pieces.add(new Pionek(WHITE, 5, 6));
        pieces.add(new Pionek(WHITE, 6, 6));
        pieces.add(new Pionek(WHITE, 7, 6));

        pieces.add(new Wieza(WHITE, 0, 7));
        pieces.add(new Skoczek(WHITE, 1, 7));
        pieces.add(new Goniec(WHITE, 2, 7));
        pieces.add(new Hetman(WHITE, 3, 7));
        pieces.add(new Krol(WHITE, 4, 7));
        pieces.add(new Goniec(WHITE, 5, 7));
        pieces.add(new Skoczek(WHITE, 6, 7));
        pieces.add(new Wieza(WHITE, 7, 7));
        
        // czarne
        pieces.add(new Pionek(BLACK, 0, 1));
        pieces.add(new Pionek(BLACK, 1, 1));
        pieces.add(new Pionek(BLACK, 2, 1));
        pieces.add(new Pionek(BLACK, 3, 1));
        pieces.add(new Pionek(BLACK, 4, 1));
        pieces.add(new Pionek(BLACK, 5, 1));
        pieces.add(new Pionek(BLACK, 6, 1));
        pieces.add(new Pionek(BLACK, 7, 1));

        pieces.add(new Wieza(BLACK, 0, 0));
        pieces.add(new Skoczek(BLACK, 1, 0));
        pieces.add(new Goniec(BLACK, 2, 0));
        pieces.add(new Hetman(BLACK, 3, 0));
        pieces.add(new Krol(BLACK, 4, 0));
        pieces.add(new Goniec(BLACK, 5, 0));
        pieces.add(new Skoczek(BLACK, 6, 0));
        pieces.add(new Wieza(BLACK, 7, 0));
    }

    private void copyPieces(ArrayList<Piece> src, ArrayList<Piece> target) {
        target.clear();
        for(Piece p : src) target.add(p);
    }

    ////
    /// 
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
        if(mouse.pressed) {
            if(activePiece == null) // gracz nie ma figury "w ręce"
                for(Piece p : symulPieces) {
                    if(p.color == currentColor &&
                    p.col == mouse.x/Board._SQUARE_SIZE &&
                    p.row == mouse.x/Board._SQUARE_SIZE) {
                        activePiece = p;
                    } else { //gracz już ma figure w "ręce"
                        symulate();
                    } 
            }
        }
    }

    private void symulate() {
        activePiece.x = mouse.x - Board._HALF_SQUARE_SIZE;
        activePiece.y = mouse.y - Board._HALF_SQUARE_SIZE;
    }

    @Override
    protected void paintComponent(Graphics c) {
        super.paintComponent(c);
        Graphics2D g = (Graphics2D)c;
        board.draw(g);

        for(Piece p : symulPieces) p.draw(g);

        if(activePiece != null) {
            g.setColor(Color.white);
            g.setComposite(AlphaComposite.getInstance());
        }
    }
}
