package src.games.basic.examples.ticTacToe;

import src.games.basic.gameObjects.CircularGameObject;
import src.games.basic.gameObjects.CrossGameObject;
import src.games.basic.gameObjects.interfaces.GameObject;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToe extends JFrame {


    static int WIDTH = 1400;
    static int HEIGHT = 1400;

    static int MARGIN = 30;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1980, 1020);
        frame.add(createBoard(), BorderLayout.CENTER);
        frame.add(setButtons(), BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();
    }

    private static TicPanel createBoard() {
        ArrayList<GameObject> objectList = new ArrayList<>();
        objectList.add(new CircularGameObject(new Position(MARGIN, MARGIN), (int) (WIDTH * 0.30)-MARGIN, Color.BLUE, false, 6));
        objectList.add(new CrossGameObject(new Position((int) (HEIGHT * 0.33) + MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
        objectList.add(new CrossGameObject(new Position((int) (HEIGHT * 0.66) + MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
        TicPanel tk = new TicPanel(objectList, 10, HEIGHT, WIDTH);
        tk.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        return tk;
    }


    private static JPanel setButtons() {
        JPanel buttons = new JPanel();
        Button newGame = new Button("New Game");
        Button endGame = new Button("End Game");
        buttons.add(newGame);
        buttons.add(endGame);
        return buttons;
    }


}
