package src.games.basic.examples.ticTacToe;

import src.games.basic.gameObjects.CircularGameObject;
import src.games.basic.gameObjects.interfaces.GameObject;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicPanel extends JPanel {
    public static void main(String[] args) {
        ArrayList<GameObject> objectList = new ArrayList<GameObject>();
        objectList.add(new CircularGameObject(new Position(10,10),30,Color.BLUE,false,8));
        TicPanel tk = new TicPanel(objectList,10,1000,1000);
        JFrame frame = new JFrame("TicTacToe");
        frame.setSize(800,800);
        frame.add(tk);
        frame.setVisible(true);
    }

    ArrayList<GameObject> objectList;

    int lineWidth;

     int height,width;
    TicPanel(ArrayList<GameObject> objectList, int lineWidth,int height,int width){
        this.objectList = objectList;
        this.lineWidth = lineWidth;
        this.height = height;
        this.width = width;
    }

    GameObject get(int index){
        return objectList.get(index);
    }

    int getArrSize(){
        return objectList.size();
    }

    boolean remove(GameObject element){
        for (GameObject obj: objectList){
            if(element.equals(obj)){
                objectList.remove(obj);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(this.lineWidth));
        g2.setColor(Color.BLACK);
        g2.drawLine(0,(int) (width*0.33),width, (int) (width*0.33));
        g2.drawLine(0,(int) (width*0.66),width,(int) (width*0.66));
        g2.drawLine((int) (height*0.33),0,(int) (height*0.33),height);
        g2.drawLine((int) (height*0.66),0,(int) (height*0.66),height);
        if(getArrSize()!= 0) {
            for (GameObject obj : objectList) {
                obj.paintComponent(g2);
            }
        }
    }
}
