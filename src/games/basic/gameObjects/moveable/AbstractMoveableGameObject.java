package src.games.basic.gameObjects.moveable;
import src.games.basic.gameObjects.AbstractGameObject;
import src.games.basic.gameObjects.interfaces.Moveable;
import src.games.basic.position.interfaces.Positionable;


public abstract class AbstractMoveableGameObject extends AbstractGameObject implements
		Moveable {

	// Bewegungsvektor
	private Positionable deltaPos;
	
	// Beachte: da die aktuelle Position pos bereits in AbstravtGameObject definiert ist, 
	//			muss auch die Unterklasse AbstractMoveableGameObject die Position setzen
	public AbstractMoveableGameObject(Positionable pos, Positionable deltaPos) {
		super(pos);		// Aufruf: AbstractGameObject(pos);
		this.setDeltaPos(deltaPos);
	}
	
	
	@Override
	public Positionable getDeltaPos() {
		return this.deltaPos;
	}

	@Override
	public void setDeltaPos(Positionable deltaPos) {
		this.deltaPos = deltaPos;
	}

	
	@Override
	public void move() {
		Positionable pos = this.getPos();
		int newX = this.getPos().getX() + this.getDeltaPos().getX();
		int newY = this.getPos().getY() + this.getDeltaPos().getY();
		// Setze Position auf (newX, newY): 
		//		verwende hinzugef�gte Methode setPos von SimpleGameObject
		this.setPos( newX, newY );

	}

	@Override
	public void reverseDirection() {
		this.reverseXDirection();
		this.reverseYDirection();
	}

	@Override
	public void reverseXDirection() {
		// �ndere x-Richtung: einfach Bewegungsvektor 'deltaPos' skalieren
		//		verwende hinzugef�gte Methode scaleX von Positionable
		this.deltaPos.scaleX(-1);
	}

	@Override
	public void reverseYDirection() {
		// �ndere x-Richtung: einfach Bewegungsvektor 'deltaPos' skalieren
		//		verwende hinzugef�gte Methode scaleY von Positionable
		this.deltaPos.scaleY(-1);
	}
}
