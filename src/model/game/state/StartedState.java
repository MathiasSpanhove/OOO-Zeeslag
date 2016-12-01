package model.game.state;

import exception.ModelException;
import model.Game;
import model.enums.Orientation;
import model.enums.ShipType;

public class StartedState implements GameState {

	private Game game;
	
	public StartedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation) {
		
		
	}

	@Override
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation) throws ModelException {
		// TODO Auto-generated method stub
		
	}

}
