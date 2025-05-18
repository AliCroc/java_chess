package piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Board;

public class Piece {
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;

    public Piece(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.x = getX(col);
        this.y = getY(row);
        
        // Przykładowe ładowanie obrazka - powinieneś to dostosować do swoich potrzeb
        // String imageName = (color == Board.WHITE) ? "white_pawn" : "black_pawn";
        // this.image = loadImage("/chess_piece/" + imageName + ".png");
    }

    public BufferedImage getImage(String path) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(path));
            if (img == null) {
                throw new IOException("Could not load image: " + path);
            }
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading image from: " + path);
            return null;
        }
    }

    public int getX(int col) {
        return col * Board._SQUARE_SIZE;
    }

    public int getY(int row) {  // Poprawione - było col zamiast row
        return row * Board._SQUARE_SIZE;
    }

    public void draw(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, Board._SQUARE_SIZE, Board._SQUARE_SIZE, null);
        }
    }
}