package model.game.state;

import javax.swing.JOptionPane;

import exception.ModelException;
import model.Game;
import model.Position;
import model.Ship;
import model.board.Board;
import model.enums.Orientation;
import model.enums.ShipType;
import model.facade.ModelFacade;
import view.GameFrame;
import view.View;

public class StartedState implements GameState {

	private Game game;
	
	
	public StartedState(Game game) {
		this.game = game;
	}
	
	@Override
	public void squareClicked(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {	
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().attack(x, y);
		
		boolean won = false;
		if(this.game.getPlayer().getMyBoard().getShipCounter() == 0 || this.game.getPlayer().getEnemyBoard().getShipCounter() == 0){
			won = true;
		}
		
		if(won) { 
			this.game.setState(this.game.getFinishedState());
			System.out.println("Game finished");
			this.game.finishedGame();
			System.out.println("EndGame is True");
		} else {
			this.game.getAI().attack();
		}
		
		
		// since view observes model, it will draw the squares differently
	}
	@Override
	public void squareEntered(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().focus(x, y, true);
	}

	@Override
	public void squareExited(int x, int y, ShipType shipType, Orientation orientation, Board board) throws ModelException {
		if (!board.equals(this.game.getPlayer().getEnemyBoard())) {
			return;
		}
		
		this.game.getPlayer().getEnemyBoard().focus(x, y, false);
	}
	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
