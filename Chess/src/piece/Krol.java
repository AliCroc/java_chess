package piece;

import main.Screen;

public class Krol extends Piece {
    public Krol(int color, int col, int row) {
        super(color, col, row);

        image = (color == Screen.WHITE) ? getImage("/K") : getImage("/k_b");
    }
}


