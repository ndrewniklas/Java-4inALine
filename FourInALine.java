/**
 * 
 */
package connect4;

/**
 * Jun 5, 2017
 * 
 * @author Andrew Niklas
 *
 */
public class FourInALine {

	final char BOT = 'X';
	final char HUMAN = 'O';
	char first;
	int thinkTime;
	AI ai;
	UserInterface ui;
	Board game;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FourInALine();
	}

	/**
	 * 
	 */
	public FourInALine() {
		start();
	}

	/**
	 * 
	 */
	private void start() {
		game = new Board();
		ai = new AI();
		char result = ui.welcome();

		if (result == 'y')
			first = HUMAN;
		else
			first = BOT;
		thinkTime = ui.thinkTime();
		gameloop();

	}

	/**
	 * @param game2
	 * @param ui2
	 */
	private void gameloop() {
		while (true) {
			if (BOT == first) {
				// Player 1 is
				boolean botWin = botTakeTurn(BOT);
				if (botWin) break;
				
				// Player 2 is
				boolean plrWin = plrTakeTurn(HUMAN);
				if (plrWin) break;
				
			} else {
				// Player 1 is Human
				boolean plrWin = plrTakeTurn(HUMAN);
				if (plrWin) break;
				
				// Player 2 is Bot
				boolean botWin = botTakeTurn(BOT);
				if (botWin) break;
			}
		}

	}

	/**
	 * @param bOT2
	 * @return
	 */
	private boolean plrTakeTurn(char player) {
		Position pos = ui.enterPosition();
		
		while (!game.setPiece(pos.getX(), pos.getY(), player)) {
			System.out.println("Not a legal move!\n");
			pos = ui.enterPosition();
		}
		System.out.println(game);
		System.out.println("Score:" + game.calculateScore());
		if (game.checkWinCondition(pos.getX(), pos.getY())) {
			System.out.println("Player wins the game!");
			return true;
		}
		return false;
	}
	
	private boolean botTakeTurn(char player) {
		Position pos = ai.search(game);
		
		while (!game.setPiece(pos.getX(), pos.getY(), player)) {
			System.out.println("Not a legal move!\n");
			pos = ui.enterPosition();
		}
		System.out.println(game);
		System.out.println("Score:" + game.calculateScore());
		if(game.checkWinCondition(pos.getX(), pos.getY())){
			System.out.println("Computer wins the game!");
			return true;
		}
		return false;
	}

}
