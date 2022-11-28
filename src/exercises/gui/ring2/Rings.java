package src.exercises.gui.ring2;

import java.awt.Color;
import javax.swing.JFrame;
import java.util.LinkedList;

public class Rings {

	public static void main(String[] args) {
		JFrame myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SingleRing ring1 = new SingleRing(100, 100, 50, 5, Color.blue);
		SingleRing ring2 = new SingleRing(160, 100, 50, 5, Color.black);
		SingleRing ring3 = new SingleRing(220, 100, 50, 5, Color.red);
		SingleRing ring4 = new SingleRing(130, 130, 50, 5, Color.yellow);
		SingleRing ring5 = new SingleRing(190, 130, 50, 5, Color.green);
		
		LinkedList<SingleRing> liste = new LinkedList<SingleRing>();
		liste.add( ring1 );
		liste.add( ring2 );
		liste.add( ring3 );
		liste.add( ring4 );
		liste.add( ring5 );
		
		liste.remove(1);	// entferne ring2
		liste.add(  new SingleRing(160, 100, 50, 5, Color.black) );	// fehlenden Ring wieder hinzuf?gen
		
		RingPanel panel = new RingPanel( liste ); 
		
		myframe.add( panel );
		
		myframe.setSize(400, 400);
		myframe.setVisible(true);
	}
}
