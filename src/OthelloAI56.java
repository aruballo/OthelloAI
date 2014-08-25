


public class OthelloAI56 implements OthelloAI{
	
	public boolean firstRun = true;
	public boolean blackAI = true;	
	public int turn = 0;
	//The AI constructor. Initializes a new object of the AI type.
	//Bad moves results in Good wins.
	public OthelloAI56(){

	}
	
	//The search algorithm. My evaluation is basically an int measuring the amount of corners a state has, 
	//the number of pucks, the number of edges, 
	
    public int search(OthelloGameState state, int depth, int max, int min){
 
		int evaluation = 0;
		int puckScore = 0;
		int cornerScore = 0;
		int edgeScore = 0;
		int badCorner = 0;

		
		//My evaluation logic. In essence, corners are worth top notch, then edges, then number of pucks gained.
		//Equivalent points are subtracted if in this current state the opponent has a corner, an edge, or a higher
		//score, etc.
		if(depth == 0 || state.gameIsOver()){
			if(blackAI){
				
				//Corners
				if(state.getCell(0,0) == OthelloCell.BLACK || state.getCell(7, 0) == OthelloCell.BLACK
						|| state.getCell(0, 7) == OthelloCell.BLACK || state.getCell(7,7) == OthelloCell.BLACK){	
					cornerScore = 10000;
				}
				if(state.getCell(0,0) == OthelloCell.WHITE || state.getCell(7, 0) == OthelloCell.WHITE
						|| state.getCell(0, 7) == OthelloCell.WHITE || state.getCell(7,7) == OthelloCell.WHITE){
					cornerScore = -10000;
				}
				
				
				//Pucks
				puckScore = state.getBlackScore() - state.getWhiteScore();
			
				if(state.getWhiteScore() == 0){
					puckScore = 100000000;
				}
				if(state.getBlackScore() == 0){
					puckScore = -100000000;
				}
				
				
				//Edges
				for(int i = 2; i <=5; i++){
					if(state.getCell(0, i) == OthelloCell.BLACK || state.getCell(i, 0) == OthelloCell.BLACK
							|| state.getCell(7, i) == OthelloCell.BLACK || state.getCell(i, 7) == OthelloCell.BLACK){
						edgeScore += 6;
					}
					if(state.getCell(0, i) == OthelloCell.WHITE || state.getCell(i, 0) == OthelloCell.WHITE
							|| state.getCell(7, i) == OthelloCell.WHITE || state.getCell(i, 7) == OthelloCell.WHITE){
						edgeScore -= 6;
					}
					if(state.getCell(0, i+1) == OthelloCell.WHITE || state.getCell(i+1, 0) == OthelloCell.WHITE
					|| state.getCell(7, i+1) == OthelloCell.WHITE || state.getCell(i+1, 7) == OthelloCell.WHITE
					|| state.getCell(0, i-1) == OthelloCell.WHITE || state.getCell(i-1, 0) == OthelloCell.WHITE
					|| state.getCell(7, i-1) == OthelloCell.WHITE || state.getCell(i-1, 7) == OthelloCell.WHITE){
						edgeScore -= 4;
					}
					
				}
				
				if(state.getCell(0, 1) == OthelloCell.BLACK || state.getCell(1, 0) == OthelloCell.BLACK ||
						state.getCell(1, 1) == OthelloCell.BLACK || state.getCell(0, 6) == OthelloCell.BLACK ||
						state.getCell(1, 6) == OthelloCell.BLACK || state.getCell(1, 7) == OthelloCell.BLACK ||
						state.getCell(6, 0) == OthelloCell.BLACK || state.getCell(6, 1) == OthelloCell.BLACK
						||state.getCell(7, 1) == OthelloCell.BLACK || state.getCell(6, 6) == OthelloCell.BLACK
						|| state.getCell(6, 7) == OthelloCell.BLACK || state.getCell(7, 6) == OthelloCell.BLACK){
					
					badCorner = -20;
				}
				
				if(state.getCell(0, 1) == OthelloCell.WHITE || state.getCell(1, 0) == OthelloCell.WHITE ||
						state.getCell(1, 1) == OthelloCell.WHITE || state.getCell(0, 6) == OthelloCell.WHITE ||
						state.getCell(1, 6) == OthelloCell.WHITE || state.getCell(1, 7) == OthelloCell.WHITE ||
						state.getCell(6, 0) == OthelloCell.WHITE || state.getCell(6, 1) == OthelloCell.WHITE
						||state.getCell(7, 1) == OthelloCell.WHITE || state.getCell(6, 6) == OthelloCell.WHITE
						|| state.getCell(6, 7) == OthelloCell.WHITE || state.getCell(7, 6) == OthelloCell.WHITE){
					
					badCorner = 20;
					
				}
				
				
				
				evaluation = puckScore + cornerScore + edgeScore + badCorner;
//				System.out.println("BLACK EVAL " + evaluation);
				return evaluation;
				
			}
			else{
				
				if(state.getCell(0,0) == OthelloCell.WHITE || state.getCell(7, 0) == OthelloCell.WHITE
						|| state.getCell(0, 7) == OthelloCell.WHITE || state.getCell(7,7) == OthelloCell.WHITE){
					cornerScore = 10000;
				}
				
				if(state.getCell(0,0) == OthelloCell.BLACK || state.getCell(7, 0) == OthelloCell.BLACK
						|| state.getCell(0, 7) == OthelloCell.BLACK || state.getCell(7,7) == OthelloCell.BLACK){	
					cornerScore = -10000;
				}
				
				puckScore = state.getWhiteScore()- state.getBlackScore();
				
				if(state.getBlackScore() == 0){
					puckScore = 100000000;
				}
				
				if(state.getWhiteScore() == 0){
					puckScore = -100000000;
				}
				
				for(int i = 2; i <=5; i++){
					if(state.getCell(0, i) == OthelloCell.WHITE || state.getCell(i, 0) == OthelloCell.WHITE
							|| state.getCell(7, i) == OthelloCell.WHITE || state.getCell(i, 7) == OthelloCell.WHITE){
						edgeScore += 6;
					}
					if(state.getCell(0, i) == OthelloCell.BLACK || state.getCell(i, 0) == OthelloCell.BLACK
							|| state.getCell(7, i) == OthelloCell.BLACK || state.getCell(i, 7) == OthelloCell.BLACK){
						edgeScore -= 6;
					}
					if(state.getCell(0, i+1) == OthelloCell.BLACK|| state.getCell(i+1, 0) == OthelloCell.BLACK
							|| state.getCell(7, i+1) == OthelloCell.BLACK || state.getCell(i+1, 7) == OthelloCell.BLACK
							|| state.getCell(0, i-1) == OthelloCell.BLACK || state.getCell(i-1, 0) == OthelloCell.BLACK
							|| state.getCell(7, i-1) == OthelloCell.BLACK || state.getCell(i-1, 7) == OthelloCell.BLACK){
								edgeScore -= 4;
							}
					
				}
				
				if(state.getCell(0, 1) == OthelloCell.WHITE || state.getCell(1, 0) == OthelloCell.WHITE ||
						state.getCell(1, 1) == OthelloCell.WHITE || state.getCell(0, 6) == OthelloCell.WHITE ||
						state.getCell(1, 6) == OthelloCell.WHITE || state.getCell(1, 7) == OthelloCell.WHITE ||
						state.getCell(6, 0) == OthelloCell.WHITE || state.getCell(6, 1) == OthelloCell.WHITE
						||state.getCell(7, 1) == OthelloCell.WHITE || state.getCell(6, 6) == OthelloCell.WHITE
						|| state.getCell(6, 7) == OthelloCell.WHITE || state.getCell(7, 6) == OthelloCell.WHITE){
					
					badCorner = -20;
					
				}
				if(state.getCell(0, 1) == OthelloCell.BLACK || state.getCell(1, 0) == OthelloCell.BLACK ||
						state.getCell(1, 1) == OthelloCell.BLACK || state.getCell(0, 6) == OthelloCell.BLACK ||
						state.getCell(1, 6) == OthelloCell.BLACK || state.getCell(1, 7) == OthelloCell.BLACK ||
						state.getCell(6, 0) == OthelloCell.BLACK || state.getCell(6, 1) == OthelloCell.BLACK
						||state.getCell(7, 1) == OthelloCell.BLACK || state.getCell(6, 6) == OthelloCell.BLACK
						|| state.getCell(6, 7) == OthelloCell.BLACK || state.getCell(7, 6) == OthelloCell.BLACK){
					
					badCorner = 20;
					
				}
				
				evaluation = puckScore + cornerScore + edgeScore + badCorner;
				return evaluation;
			}
		}
		
		//Move simulations..
		else{
			//If BLACK AI's turn...
				if(blackAI && state.isBlackTurn()){
					for(int i = 0; i < 8; i++){
						for(int j = 0; j < 8; j++){
							if(state.isValidMove(i, j)){
								OthelloGameState tempState = state.clone();
								tempState.makeMove(i, j);
								int nextEval = search(tempState, depth - 1, max, min);
								
								if(nextEval > max){
									max = nextEval;
								}
								
								//Prune
								if(max >= min){
									return max;
								}
							}
						}
					}
					return max;
				}
				
				
				//If WHITE AI's turn...
				else if(!blackAI && !state.isBlackTurn()){
					for(int i = 0; i < 8; i++){
						for(int j = 0; j < 8; j++){
							if(state.isValidMove(i, j)){
								OthelloGameState tempState = state.clone();
								tempState.makeMove(i, j);
								int nextEval = search(tempState, depth - 1, max, min);
								if(nextEval > max){
									max = nextEval;
								}
								//Prune
								if(max >= min){
									return max;
								}
							}
						}
					}
					return max;
				}
				
				else{
					//If enemy's turn...
					for(int i = 0; i < 8; i++){
						for(int j = 0; j < 8; j++){
							if(state.isValidMove(i, j)){
								OthelloGameState tempState = state.clone();
								tempState.makeMove(i, j);
								int nextEval = search(tempState, depth - 1, max, min);
								if(nextEval < min){
									min = nextEval;
								}
								//Prune
								if(min <= max){
									return min;
								}
							}
						}
					}
					return min;
				}
		}
	}
	
	@Override
	public OthelloMove chooseMove(OthelloGameState state) {
		
		//Depth integer. 
		int depth = 5;
		
		//Up the turn counter, since it is our turn.
		turn++;
		//In order to check which color this AI is, we grab the scores first.
		int blackScore = state.getBlackScore();  
		int whiteScore = state.getWhiteScore();
		
		
		if(turn >= 7){
			depth = 4;
		}
		
		else if(turn <= 20){
			depth = 5;
		}
		
		//If it is the firstRun, and the black Score is larger than the white score,
		//then we know this AI is not the black pucks, and therefore, this AI is the 
		//white pucks. We are no longer in the first run, so we change that to false.
		if(firstRun){
			if(blackScore > whiteScore ){
				blackAI = false;
				firstRun = false;
			}
			else if(blackScore == whiteScore){
				blackAI = true;
				firstRun = false;
			}
		}
		
		//Place holders for the best move's row and column.
		int bestR = 0;
		int bestC = 0;
		
		//Current MaxMove
		int maxE = -999999;
	
		//Max and min numbers (alpha and beta)
		int max = -99999999;
		int min = 99999999;
		
		//Play out the very first possibilities,and search each one for the
		//best.
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(state.isValidMove(i, j)){
					OthelloGameState temp = state.clone();
					temp.makeMove(i, j);
					int eval = search(temp, depth, max, min);
					if(eval > maxE){
						maxE = eval;
						bestR = i;
						bestC = j;
					}
				}
			}	
		}
		
		System.out.println(turn);
		return new OthelloMove(bestR, bestC);
	}

}
