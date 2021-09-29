package nordlab05;

import edu.du.dudraw.DUDraw;

public class TicTacToe {

	//Enumerator for cell contents
    public enum Contents {
    	EMPTY,
		EX,
    	OH
    }
	
    //sets up the inner class to hold cell data in our tictactoe board.
	private class Cell {
		
		//Instance variables. Although private, can 
		private Contents contains;
		private int row;
		private int col;
		
		//Default constructor sets cell to empty to start
		public Cell(int row, int col) {
			this.contains = Contents.EMPTY;
			this.row = row;
			this.col = col;
		}
		//To string method for debugging purposes.
		public String toString() {
			return this.contains.toString() + " X:" + col + " Y:" + row;
		}
		
	}

	// Array of Cells for the Tic Tac Toe Board 
	private Cell[][] board;

	// Message that changes based on turn and game end condition
	private String message;

	// Boolean to determine turns
	private boolean Xturn;

	public TicTacToe() {
		DUDraw.setCanvasSize(600, 700);
		DUDraw.setXscale(0, 3);
		DUDraw.setYscale(0, 3.5);

		//Set ups board as a 3x3 with all empty cell objects
		board = new Cell[3][3];
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				board[r][c] = new Cell(r, c);
			}
		}
		System.out.println(board[2][1]);

		this.message = "It's X's turn";
		this.Xturn = true;
	}

	public void draw() {
		DUDraw.clear(DUDraw.WHITE);
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.line(1, 0, 1, 3);
		DUDraw.line(2, 0, 2, 3);
		DUDraw.line(0, 1, 3, 1);
		DUDraw.line(0, 2, 3, 2);
		DUDraw.text(1.5, 3.25, this.message);

		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if (board[r][c].contains == Contents.EX) {
					DUDraw.square(c + 0.5, r + 0.5, 0.1);
					}
				if (board[r][c].contains == Contents.OH) {
					DUDraw.circle(c + 0.5, r + 0.5, 0.1);
					}
				}
			}
		}

	public void playGame() {
		DUDraw.enableDoubleBuffering();
		do {

			//Checks to see if mouse is pressed. if it is, changes the clicked cell to the symbol of whoever's turn it is
			//If it isn't empty, dont write anything
			if(DUDraw.isMousePressed()) {
				if (board[(int)DUDraw.mouseY()][(int)DUDraw.mouseX()].contains == Contents.EMPTY) {
					if (Xturn) {
						board[(int)DUDraw.mouseY()][(int)DUDraw.mouseX()].contains = Contents.EX;
						this.message = "It's O's Turn";
					}
					else {
						board[(int)DUDraw.mouseY()][(int)DUDraw.mouseX()].contains = Contents.OH;
						this.message = "It's X's turn";
					}
					this.Xturn = !Xturn;
					System.out.println(Xturn);
					System.out.println(board[(int)DUDraw.mouseY()][(int)DUDraw.mouseX()].toString());
				}
				//Also toggles the turn message. And then toggles the turn bool
			}

			draw();
			DUDraw.show();
			DUDraw.pause(100);

			// Check game end conditions
		} while (!gameWon() && !allFilled());
		if (gameWon()) {
			message = Xturn ? "Game over, O wins!" : "Game over, X wins!";
		} else {
			message = "Game over, it's a tie!";
		}
		System.out.println("drew");
		draw();
		DUDraw.show();
	}

	// Check if someone has won the game
	public boolean gameWon() {
		// Returns true if a row, column or diagonal contains all O's or X's
		// Note: you may want to make use of the wins() method below.
		
		//Checks the rows
		for(int r = 0; r < 3; r++) {
			if(wins(board[r][0].contains, board[r][1].contains, board[r][2].contains)) {
				return true;
			}
		}
		
		//Checks the columns
		for(int c = 0; c < 3; c++) {
			if(wins(board[0][c].contains, board[1][c].contains, board[2][c].contains)) {
				return true;
			}
		}
		//Check the diagonals
		if(wins(board[0][0].contains, board[1][1].contains, board[2][2].contains)){
			return true;
		}
		
		if(wins(board[0][2].contains, board[1][1].contains, board[2][0].contains)){
			return true;
		}

		return false;

	}

	// Check if three squares are the same (and not empty) 
	private boolean wins(Contents c1, Contents c2, Contents c3) {
		if (c1 == Contents.EMPTY)
			return false;
		return c1 == c2 && c1 == c3;
	}

	private boolean allFilled() {
		boolean notFilled = true;
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(board[r][c].contains == Contents.EMPTY) {
					notFilled = false;
				}
			}
		}
		return notFilled;
	}
	
}