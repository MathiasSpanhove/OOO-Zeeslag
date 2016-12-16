package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import model.game.Game;
import model.game.GameObservable;
import model.game.GameObserver;
import model.player.Player;
import model.board.Board;
import model.facade.ModelFacade;

public class GameFrame extends JFrame implements View, GameObserver {
	
	private static final long serialVersionUID = 1L;
	private GamePanel panel1, panel2;
	private SelectionPanel selectionPanel;
	private ModelFacade modelFacade;
	
	public static final int WIDTH = 940;
	public static final int HEIGHT = 360;
	public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

	public GameFrame(ModelFacade modelFacade) {
		super();
		
		this.modelFacade = modelFacade;
		this.modelFacade.registerGameObserver(this);
		String playerName = this.modelFacade.getPlayerName();
		String aiName = this.modelFacade.getAIName();
		int playerScore = this.modelFacade.getPlayerScore();
		int aiScore = this.modelFacade.getAIScore();
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		
		this.setLayout(new GridLayout(1,3));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		this.selectionPanel = new SelectionPanel();
		this.selectionPanel.setBorder(padding);
		this.add(this.selectionPanel);
		
		Board board1 = this.modelFacade.getBoardPlayer();
		this.panel1 = new GamePanel(this, playerName, playerScore, board1);
		this.panel1.setBorder(padding);
		this.add(this.panel1);
		
		Board board2 = this.modelFacade.getBoardAI();
		this.panel2 = new GamePanel(this, aiName, aiScore, board2);
		this.panel2.setBorder(padding);
		this.add(this.panel2);
	
		revalidate();
	}

	public GamePanel getPanel1() {
		return this.panel1;
	}

	public GamePanel getPanel2() {
		return this.panel2;
	}
	
	public SelectionPanel getSelectionPanel() {
		return this.selectionPanel;
	}

	@Override
	public void startView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void gameChanged(Game game) {
		if(game.getWinner() != null) {
			JOptionPane.showMessageDialog(null, "Game over!\n" +  game.getWinner().getName() + " won met " +  game.getWinner().getScore() + " punten.");
		}
	}

}
