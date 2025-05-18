package piece;

import main.Screen;

public class Pionek extends Piece {
    public Pionek(int color, int col, int row) {
        super(color, col, row);

        image = (color == Screen.WHITE) ? getImage("/P") : getImage("/p_b");
    }
}
