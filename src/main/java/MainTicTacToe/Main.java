package MainTicTacToe;

import java.util.Scanner;

public class Main {

	static final char BOT = 'O';
	static final char USER = 'X';

	Scanner scan = new Scanner(System.in);

	
	void clearScreen(){
		for(int i = 0 ; i < 25; i++)
			System.out.println();
	}
	
	public static void generateBoard(char[] board){
		for(int i = 1 ; i <= board.length ; i++){
			System.out.print("|"+board[i-1]+"| ");
			if(i % 3 == 0)
				System.out.println();
		}
	}
	
	public static boolean validatePosition(Board board, int position){
		if(board.getBoard()[position] == 'X' || board.getBoard()[position] == 'O')
			return false;
		else
			return true;
	}
	
	public static boolean isWin(Board board, char input){
		if((board.getBoard()[0] == input && board.getBoard()[1] == input && board.getBoard()[2] == input) ||
		 	(board.getBoard()[3] == input && board.getBoard()[4] == input && board.getBoard()[5] == input) ||
		 	(board.getBoard()[6] == input && board.getBoard()[7] == input && board.getBoard()[8] == input) ||
		 	(board.getBoard()[0] == input && board.getBoard()[3] == input && board.getBoard()[6] == input) ||
		 	(board.getBoard()[1] == input && board.getBoard()[4] == input && board.getBoard()[7] == input) ||
		 	(board.getBoard()[2] == input && board.getBoard()[5] == input && board.getBoard()[8] == input) ||
		 	(board.getBoard()[0] == input && board.getBoard()[4] == input && board.getBoard()[8] == input) ||
		 	(board.getBoard()[2] == input && board.getBoard()[4] == input && board.getBoard()[6] == input)){
			return true;
		 }else {
			 return false;
		 }
	}
	
	static int isWin(Board board){
		if((board.getBoard()[0] == BOT && board.getBoard()[1] == BOT && board.getBoard()[2] == BOT) ||
		 	(board.getBoard()[3] == BOT && board.getBoard()[4] == BOT && board.getBoard()[5] == BOT) ||
		 	(board.getBoard()[6] == BOT && board.getBoard()[7] == BOT && board.getBoard()[8] == BOT) ||
		 	(board.getBoard()[0] == BOT && board.getBoard()[3] == BOT && board.getBoard()[6] == BOT) ||
		 	(board.getBoard()[1] == BOT && board.getBoard()[4] == BOT && board.getBoard()[7] == BOT) ||
		 	(board.getBoard()[2] == BOT && board.getBoard()[5] == BOT && board.getBoard()[8] == BOT) ||
		 	(board.getBoard()[0] == BOT && board.getBoard()[4] == BOT && board.getBoard()[8] == BOT) ||
		 	(board.getBoard()[2] == BOT && board.getBoard()[4] == BOT && board.getBoard()[6] == BOT)){
			return -(10);
		 }else if((board.getBoard()[0] == USER && board.getBoard()[1] == USER && board.getBoard()[2] == USER) ||
			 	(board.getBoard()[3] == USER && board.getBoard()[4] == USER && board.getBoard()[5] == USER) ||
			 	(board.getBoard()[6] == USER && board.getBoard()[7] == USER && board.getBoard()[8] == USER) ||
			 	(board.getBoard()[0] == USER && board.getBoard()[3] == USER && board.getBoard()[6] == USER) ||
			 	(board.getBoard()[1] == USER && board.getBoard()[4] == USER && board.getBoard()[7] == USER) ||
			 	(board.getBoard()[2] == USER && board.getBoard()[5] == USER && board.getBoard()[8] == USER) ||
			 	(board.getBoard()[0] == USER && board.getBoard()[4] == USER && board.getBoard()[8] == USER) ||
			 	(board.getBoard()[2] == USER && board.getBoard()[4] == USER && board.getBoard()[6] == USER)){
			return 10;
		 }{
			 return 0;
		 }
	}
	

	public static int minimax(Board board, int depth, char input){
		int score = isWin(board);

		if(isWin(board) == 10) return score-depth;
		else if(isWin(board) == -10) return score+depth;
		
		if(!board.checkMovesLeft()) return 0;

		int bestValue;

		if(input == BOT){
			bestValue = 99999999;
			for(int i = 0 ; i < board.getBoard().length; i++){
				if(board.getBoard()[i] != 'X' && board.getBoard()[i] != 'O'){
					char before = board.getBoard()[i];
					board.getBoard()[i] = BOT;
					
					int value = minimax(board,depth++,USER);
					bestValue = Math.min(bestValue, value);

					board.getBoard()[i] = before;
				}
			}
			return bestValue;
		}else{
			bestValue = -99999999;
			for(int i = 0 ; i < board.getBoard().length; i++){
				if(board.getBoard()[i] != 'X' && board.getBoard()[i] != 'O'){
					char before = board.getBoard()[i];
					board.getBoard()[i] = USER;
					
					int value = minimax(board,depth++,BOT);
					bestValue = Math.max(bestValue, value);

					board.getBoard()[i] = before;
				}
			}
		}
		return bestValue;

	}
	
	public static int findBestMoves(Board board){
		int bestMoveValues =  999999999;
		int bestMove = -1;
		
		for(int i = 0 ; i < board.getBoard().length; i++){
			if(board.getBoard()[i] != 'X' && board.getBoard()[i] != 'O'){
				char before = board.getBoard()[i];
				board.getBoard()[i] = BOT;
				
				int bestValue = minimax(board,0,USER);
				
				board.getBoard()[i] = before;

				if(bestValue < bestMoveValues){
					bestMoveValues = bestValue;
					bestMove = i;
				}
			}
		}
		
		return bestMove;
	}
	
	public void game(){
		char[] boards = {'0','1','2','3','4','5','6','7','8'};
		int position;
		Board board = new Board(boards);
		board.initializeBoard(boards);
		
		while(true){
			clearScreen();
			if(isWin(board, 'X')){
				System.out.println("Congratulations !!! User Win.");
				break;
			}else if(isWin(board, 'O')){
				System.out.println("Congratulations !!! AI Win.");
				break;
			}
			
			if(!board.checkMovesLeft()){
				System.out.println("TIE !");
				break;
			}
			
			generateBoard(board.initializeBoard(boards));
			
			do{
				System.out.print("Place your X [0..8] : ");
				position = scan.nextInt(); scan.nextLine();
			}while(position < 0 || position > 8 || !validatePosition(board, position));
			board.setBoardPosition(position, 'X');
			
			if(!board.checkMovesLeft()){
				System.out.println("TIE !");
				break;
			}
			
			System.out.println("Thinking...");
			
			int bestMove = findBestMoves(board);
			board.setBoardPosition(bestMove, 'O');
			System.out.println(bestMove);
		}
	}
	
	public Main() {
		game();
	}
	

	public static void main(String[] args) {
		new Main();
	}

}
