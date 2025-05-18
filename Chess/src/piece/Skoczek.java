package piece;

import main.Screen;

public class Skoczek extends Piece {
    public Skoczek(int color, int col, int row) {
        super(color, col, row);

        image = (color == Screen.WHITE) ? getImage("/S") : getImage("/s_b");
    }
}