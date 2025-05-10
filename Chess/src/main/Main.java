package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame ("Chess"); //tytuł okna
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        Screen screen = new Screen(); // inicjalizacja ekranu
        window.add(screen);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        screen.launchGame();
    }
}
