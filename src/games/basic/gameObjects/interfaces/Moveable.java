package src.games.basic.gameObjects.interfaces;
import src.games.basic.position.interfaces.Positionable;


public interface Moveable {

	void move();
	
	void setDeltaPos(Positionable deltaPos);
	Positionable getDeltaPos();
	
	void reverseXDirection();
	void reverseYDirection();
	void reverseDirection();
	
}
