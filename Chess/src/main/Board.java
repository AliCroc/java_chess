package main;

import java.awt.Graphics2D;
import java.awt.Color;

public class Board {
    final int _MAX_COL = 8;
    final int _MAX_ROW = 8;

    public static final int _SQUARE_SIZE = 100;
    public static final int _HALF_SQUARE_SIZE = _SQUARE_SIZE/2;
    
    Color cLight = new Color(235, 161, 52); // 235, 161, 52 - jasny
    Color cDark = new Color(168, 104, 8); // 168, 104, 8 - ciemny
    
    int leftPanelWidth = 200;

    public void draw(Graphics2D g) {
        int colorFlag = 0;
        for (int r = 0; r < _MAX_ROW; r++) {
            for (int c = 0; c < _MAX_COL; c++) {

                if(colorFlag == 0) {
                    g.setColor(cLight);
                    colorFlag = 1;
                } else {
                    g.setColor(cDark);
                    colorFlag = 0;
                }

            g.fillRect(leftPanelWidth+c*_SQUARE_SIZE, r*_SQUARE_SIZE, _SQUARE_SIZE, _SQUARE_SIZE);

            }
            colorFlag = (colorFlag == 0) ?  1 : 0;

        }
    }
}
