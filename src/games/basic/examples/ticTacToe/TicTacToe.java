package src.games.basic.examples.ticTacToe;

import src.games.basic.gameObjects.CircularGameObject;
import src.games.basic.gameObjects.CrossGameObject;
import src.games.basic.gameObjects.interfaces.GameObject;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class TicTacToe extends JFrame {


    int WIDTH;
    int HEIGHT;

    int MARGIN;


    TicPanel ticPanel;

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(1000, 1000, 30);
    }

    TicTacToe(int width, int height, int margin) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MARGIN = margin;
        createBoard();
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1980, 1020);
        frame.add(ticPanel, BorderLayout.CENTER);
        frame.add(setButtons(), BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();
    }

    private void createBoard() {
        ArrayList<GameObject> objectList = new ArrayList<>();
        objectList.add(new CircularGameObject(new Position(MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
        objectList.add(new CrossGameObject(new Position((int) (HEIGHT * 0.33) + MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
        objectList.add(new CrossGameObject(new Position((int) (HEIGHT * 0.66) + MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
        ticPanel = new TicPanel(objectList, 10, HEIGHT, WIDTH);
        ticPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }


    private JPanel setButtons() {
        JPanel buttons = new JPanel();
        Button newGame = new Button("New Game");
        Button endGame = new Button("End Game");
        newGame.addActionListener(
                event -> {
                    ticPanel.removeAll();
                    ticPanel.repaint();
                }
        );
        endGame.addActionListener(
                e -> {
                    exit(0);
                }
        );
        buttons.add(newGame);
        buttons.add(endGame);
        return buttons;
    }


}
