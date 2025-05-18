package piece;

import main.Screen;

public class Wieza extends Piece {
    public Wieza(int color, int col, int row) {
        super(color, col, row);

        image = (color == Screen.WHITE) ? getImage("/W") : getImage("/w_b");
    }
}