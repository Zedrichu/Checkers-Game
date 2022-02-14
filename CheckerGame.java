package  Assignment1___Checkers;
import java.util.Scanner;

/* Game of Checkers
 * 
 * Description: Simulation of a Checkers Game 
 * 
 * @__Author |--> Adrian Zvizdenco, s204683
 * @__Date & Time |--> 03/02/2022 12:46:13
 * @__Status |--> Test 
 * @__Version |--> 1.3
 * */
public class CheckerGame {
	//Initialize scanner for further user input
	public static Scanner scan = new Scanner(System.in);
    
	public static void main(String[] args) {
    	//Main loop of the game

    	//Initialize the board
		int[][] gameBoard = initBoard();
    	int counter = 0;
    	String key;
    	System.out.println("Welcome to our game! \n Press ENTER to advance and Q+ENTER to quit after a turn!");
    	showBoard(gameBoard);
    	while (true) {
    		// Take turns in the game
    		System.out.print("Let's complete turn number: "+(counter+1));
    		key = scan.nextLine();
    		if (key.equals("Q") || key.equals("q")) break; 
    		showBoard(updateBoard(counter%2+1, gameBoard));
    		counter++;
    	}
    	
    	//scan.close();
    }
    
    public static int[][] initBoard(){
        /**
		Method initializing the game board with the proper positions 
		for pieces of both players at the start of the game.

		Parameters: none
		Returns: none
		*/
		
    	/* 
    	 * Checkers can only be placed 
    	 * on cells with sum of indices odd 
    	*/
    	int[][] board = new int[8][8];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (i<3) {
                    if ((i+j)%2==0) {
                        board[i][j]=0;
                    } else {
                        board[i][j]=1;
                    }
                } else if (i<5) {
                    board[i][j]=0;
                } else {
                    if ((i+j)%2==0) {
                        board[i][j]=0;
                    } else {
                        board[i][j]=2;
                    }
                }
            }            
        }
        return board;
    }

    public static void showBoard(int[][] board){
		/**
		Method printing the configuration of the game board to the screen.

		Parameters: board - integer array, containing the current configuration
		Returns: void
		*/
    	// Print board delimiters 
    	String indexRow = " x->1 2 3 4 5 6 7 8    ";
    	String delimRow = "  +-----------------+  ";
    	System.out.println(indexRow);
    	System.out.println(delimRow);
    	for (int i=0;i<8;i++) {
    		System.out.print((i+1)+" | ");
    		for (int j=0;j<8;j++) {
    			if (board[i][j]==0) {
    				System.out.print("  ");
    			} else if (board[i][j]==1){
    				System.out.print("●"+" ");//Use "1" or "●" if circles not encoded properly
    			} else if (board[i][j]==2) {
    				System.out.print("○"+" ");//Use "2" or "○" if circles not encoded properly
    			}
    		}
    		System.out.println("| "+(i+1));
    	}
    	System.out.println(delimRow);
    	System.out.println(indexRow);
    }
    public static int[][] updateBoard(int turn,int[][] currBoard){
		/**
		Method updating the configuration of the game board based on 
		each player's input, following the order of turns.

		Parameters: currBoard - integer array, containing the current configuration
					turn - integer (1 or 2), containing the number of the player moving currently
		Returns: integer array, containing the updated configuration
		*/
		

    	System.out.println("Turn of player: "+turn);
    	int pcX = -1;
    	int pcY = -1;
    	
    	int dtX = -1;
    	int dtY = -1;
    	
   
    	while (true) {
    		// Request current position of the piece to be moved
    		System.out.println("Coordinates of piece to be moved:");
	    	System.out.print("    Enter coordinate X --> ");
	    	pcX = scan.nextInt()-1;
	    	System.out.print("    Enter coordinate Y --> ");
	    	pcY = scan.nextInt()-1;
	    	scan.nextLine();
	    	// Validate the input according to game rules
	    	boolean avlb;
	    	boolean bound;
	    	// Verify that the picked piece is within board limits
	    	if (pcX<=7 && pcX>=0 &&
	    			pcY<=7 && pcY>=0) {
	    		bound = true;
	    	} else {
				System.out.println("Going off board limits! Get back on board!☹");
				continue;
			}

	    	// Verify that the picked piece has possible moves
	    	if ((pcX>0 && pcY>0 && turn==2 && currBoard[pcY-1][pcX-1]==0) ||
	    		(pcY>0 && pcX<7 && turn==2 && currBoard[pcY-1][pcX+1]==0) ||
	    		(pcY<7 && pcX>0 && turn==1 && currBoard[pcY+1][pcX-1]==0) ||
	    		(pcY<7 && pcX<7 && turn==1 && currBoard[pcY+1][pcX+1]==0) ||
	    		// Possible moves checked
	    		(pcX>1 && pcY>1 && currBoard[pcY-1][pcX-1]==3-turn 
	    			&& currBoard[pcY-2][pcX-2]==0) || 
	    		(pcY>1 && pcX<6 && currBoard[pcY-1][pcX+1]==3-turn 
	    			&& currBoard[pcY-2][pcX+2]==0) ||
	    		(pcY<6 && pcX>1 && currBoard[pcY+1][pcX-1]==3-turn 
	    			&& currBoard[pcY+2][pcX-2]==0) ||
	    		(pcY<6 && pcX<6 && currBoard[pcY+1][pcX+1]==3-turn 
	    			&& currBoard[pcY+2][pcX+2]==0)) {
	    		//Possible jumps checked
	    		avlb = true;
	    	} else { 
	    		avlb = false;
	    		System.out.println("All directions are blocked! Try another piece.☹");
	    	}
	    	
	    	// Verify that the piece belongs to the player having the turn
	    	if (bound && avlb &&
	    			currBoard[pcY][pcX]==turn) {
	    		break;
	    	} else if (avlb){
	    		System.out.println("Invalid coordinates! There's no player \""+turn+"\"'s piece there.☹");
	    	}
    	}
    	while (true) {
	    	//Request destination position of the piece:
	    	System.out.println("Coordinates of final position of the piece:");
	    	System.out.print("    Enter coordinate X --> ");
	    	dtX = scan.nextInt()-1;
	    	System.out.print("    Enter coordinate Y --> ");
	    	dtY = scan.nextInt()-1;
	    	scan.nextLine();
	    	
	    	boolean bound;
	    	// Verify that the destination is within board limits
	    	if (dtX<=7 && dtX>=0 && dtY<=7 && dtY>=0) {
	    		bound = true;
	    	} else bound=false;
	    	
	    	if (bound 
	    		&& currBoard[dtY][dtX]==0 //Verify the cell is empty 
	    		&& (dtY+dtX)%2==1) { //Verify that the cell is valid (odd sum)
	    		if (Math.abs(dtX-pcX)==1
	    	    		&& Math.abs(dtY-pcY)==1) {
					if ((turn==1 && dtY>pcY) || (turn==2 && dtY<pcY)) {
						break;
					}
					else System.out.println("Going in the wrong direction!☹");
				}
	    		else if (Math.abs(dtX-pcX)==2
	    	    		&& Math.abs(dtY-pcY)==2
	    	    		&& currBoard[pcY+(dtY-pcY)/2][pcX+(dtX-pcX)/2]==3-turn) {
	    					currBoard[pcY+(dtY-pcY)/2][pcX-(pcX-dtX)/2]=0;
	    					System.out.println("Piece at "+(pcX-(pcX-dtX)/2+1)+":"+(pcY-(pcY-dtY)/2+1)+" is taken!");
	    					break; // Opponent piece taken
				}
	    		else System.out.println("Invalid move! Respect the rules!☹");
	    	} else if (!bound) {
	    		System.out.println("Going off board limits! Get back on board!☹");
    		} else {
	    		System.out.println("Invalid coordinates! Cell occupied or invalid cell color.☹");
    		}
    	}
    	currBoard[pcY][pcX] = 0;
    	currBoard[dtY][dtX] = turn;
    	
    	System.out.println("Game board updated! Piece moved!☻");
    	return currBoard;
    }
}