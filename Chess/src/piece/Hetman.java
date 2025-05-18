package piece;
import main.Screen;

public class Hetman extends Piece {
    public Hetman(int color, int col, int row) {
        super(color, col, row);

        image = (color == Screen.WHITE) ? getImage("/H") : getImage("/h_b");
    }
}