package piece;

import main.Screen;

public class Goniec extends Piece {
    public Goniec(int color, int col, int row) {
        super(color, col, row);

        image = (color == Screen.WHITE) ? getImage("/G") : getImage("/g_b");
    }
}
