/**
 * 
 */
package connect4;


/**
 * Jun 5, 2017
 * @author Andrew Niklas
 *
 */
public class AI {
	
	private float alpha;
	private float beta;
	private boolean timerFlag;
	private Position currMove;

	/**
	 * @param game
	 * @return
	 */
	public Position search(Board current) {
		int depth = 1;
		float v;
		while (depth <= 4) 
		{
			v = MaxValue(current, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, depth);
			if (v >= 5000) 	return currMove;
			depth++;
		}
                
		timerFlag = false;
                int counter = 0;
                //If this is true then something went wrong...
                while(!current.isEmpty((int)(currMove.getX()-65),currMove.getY())){
                    currMove = new Position((char)(counter+65), counter);
                    counter++;
                }
		return currMove;
	}
        
	/**
	 * @param current
	 * @param negativeInfinity
	 * @param positiveInfinity
	 * @param depth
	 * @return
	 */
	private float MaxValue(Board currBoard, float alpha, float beta, int depth) {
		float score = currBoard.calculateScore();
		//cutoff test
		if (depth <= 0 || score >= 15000 || currBoard.isBoardFull())
		{
			currMove = currBoard.getMove();
			return evaluate(currBoard);
		}
		float v = Float.NEGATIVE_INFINITY;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Board temp = new Board(currBoard);
				if (temp.isEmpty(i,j)) {
					temp.setPiece(i, j, 'X');
					temp.setMyMove(new Position((char)(i+65), j));
					v = Math.max(v, MinValue(temp, alpha, beta, depth - 1));
					if (v >= beta) {
                                                currMove = currBoard.getMove();
						return v;
					}
					else {
						alpha = Math.max(alpha, v);
					}
				}
			}
		}
		return v;
	}

	/**
	 * @param temp
	 * @param alpha
	 * @param beta
	 * @param i
	 * @return
	 */
	private float MinValue(Board currBoard, float alpha, float beta, int depth) {
		float score = currBoard.calculateScore();
		//cutoff test
		if (depth <= 0 ||  score <= -15000 || currBoard.isBoardFull())
		{
			currMove = currBoard.getMove();
			return evaluate(currBoard);
		}
		float v = Float.POSITIVE_INFINITY;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Board temp = new Board(currBoard);
				if (temp.isEmpty(i,j)) {
					temp.setPiece(i, j, 'O');
					temp.setMyMove(new Position((char) (i+65), j));
					v = Math.min(v, MaxValue(temp, alpha, beta, depth - 1));
					if (v <= alpha) {
                                                //currMove = temp.getMove();
						return v;
					}
					else {
						beta = Math.min(beta, v);
					}
				}
			}
		}
		return v;
	}

	/**
	 * @param currBoard
	 * @return
	 */
	private float evaluate(Board currBoard) {
		return currBoard.calculateScore();
	}

}
