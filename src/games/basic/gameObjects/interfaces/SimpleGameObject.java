package src.games.basic.gameObjects.interfaces;
import src.games.basic.position.interfaces.Positionable;


public interface SimpleGameObject {

	public abstract Positionable getPos();
	
	// hinzugefügt: Methoden setPos (in 2 Varianten) sowie setX/setY
	public abstract void setPos(Positionable pos);
	public abstract void setPos(int x, int y);
	public abstract void setX(int x);
	public abstract void setY(int Y);

	
	public abstract int getWidth();
	public abstract int getHeight();
	
}
