import java.util.Scanner;

// Java Oracle JDK 17.0.2
// Project in IntelliJ IDEA™
// -*- coding: utf-8 -*-"
/**
    Checkers Game
	Description: Simulation of a checkers game
	@__Author --> Created by Adrian Zvizdenco aka Zedrichu
	@__Date&Time --> Created on 3/2/2022 11:19
	@__Email --> adrzvizdencojr@gmail.com
	@__Version --> 1.2
	@__Status --> Dev
*/

/**
 * Main class of the application, denoting the game as a whole.
 */
public class Game {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Board.initBoard();
        int counter = 0;
        String key;
        System.out.println("Welcome to our game! \n Press ENTER to advance and Q+ENTER to quit after a turn!");
        Board.showBoard();
        while (true) {
            // Take turns in the game
            System.out.print("Let's complete turn number: "+(counter+1));
            key = scan.nextLine();
            if (key.equals("Q") || key.equals("q")) break;
            Board.updateBoard(counter%2+1);
            Board.showBoard();
            counter++;
        }
        System.out.println("Good bye!");
        scan.close();
    }
}

/**
 * Class denoting the board on which the game of checkers is played.
 *
 * -> Holds a matrix of Squares that may or may not be occupied by some Piece.
 * -> The constructor is implicit, no parameters needed.
 * -> The board can be initialized, updated and printed to the screen.
 */
class Board {
    static private Square[][] board;
    final static private Scanner s = new Scanner(System.in);

    public static Square[][] getBoard() {
        return board;
    }

    public static void setBoard(Square[][] brd) {board = brd;}

    /**
     * Class static method to initialize the board for the start of the game.
     * -> Creates the matrix that holds the squares
     * */
    public static void initBoard() {
        board = new Square[8][8];
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++){
                if (i<3) {
                    if ((i+j)%2==0) {
                        board[i][j] = new EmptySquare(i,j);
                    } else {
                        board[i][j] = new BlackPiece(i,j);
                    }
                } else if (i<5) {
                    board[i][j] = new EmptySquare(i,j);
                } else {
                    if ((i+j)%2==0) {
                        board[i][j] = new EmptySquare(i,j);
                    } else {
                        board[i][j] = new WhitePiece(i,j);
                    }
                }
            }
        }
    }

    /**
     * Class static method to print the board game on the screen.
     * */
    public static void showBoard() {
        String indexRow = " x->1 2 3 4 5 6 7 8    ";
        String delimRow = "  +-----------------+  ";
        System.out.println(indexRow);
        System.out.println(delimRow);
        for (int i=0;i<8;i++) {
            System.out.print((i+1)+" | ");
            for (int j=0;j<8;j++) {
                if (board[i][j] instanceof EmptySquare) {
                    System.out.print("  ");
                } else if (board[i][j] instanceof Piece) {
                    System.out.print(((Piece) board[i][j]).getPlayer().getMarker()+" ");
                }
            }
            System.out.println("| "+(i+1));
        }
        System.out.println(delimRow);
        System.out.println(indexRow);
    }

    /**
     * Class static method to update the board game after each player's turn.
     * */
    public static void updateBoard(int turn) {
        Player p = Player.getSettings(turn);
        System.out.println("Turn of player: " + p.getName());

        Piece cp;
        EmptySquare es;
        Position cppos,espos;

        while(true) {
            cp = getCurrent(p);
            if (cp == null) continue;
            cppos = cp.getPos();
            es = getDestination();
            if (es == null) continue;
            espos = es.getPos();

            if (cp.validMove(espos)) {
                break;
            } else if (cp.validJump(espos)) {
                break;
            } else {
                System.out.println("Going in the wrong direction!❌");
            }
            System.out.println("Choose wisely this time!");
        }

        es.setPos(cppos);
        cp.setPos(espos);
        board[cppos.getY()][cppos.getX()] = es;
        board[espos.getY()][espos.getX()] = cp;
        System.out.println("Game board updated! Piece moved!✓");

    }

    /**
     * Class static method to obtain a valid piece to be moved according to user input.
     * -> Input: a type of enumeration Player
     * -> Output: a Piece object from the board
     * */
    private static Piece getCurrent(Player p) {
        int pcX;
        int pcY;

        System.out.println("Coordinates of piece to be moved:");
        System.out.print("    Enter coordinate X --> ");
        pcX = s.nextInt() - 1;
        System.out.print("    Enter coordinate Y --> ");
        pcY = s.nextInt() - 1;
        s.nextLine();
        if (pcX<0 || pcX>7 || pcY<0 || pcY>7) {
            System.out.println("Going off board limits! Get back on board!❌");
        }
        Square pc = board[pcY][pcX];
        if ((p==Player.BLACK? pc instanceof BlackPiece : pc instanceof WhitePiece)) {
            if (((Piece) pc).checkPossible() && pc.onBoardDig()) {
                return (Piece) board[pcY][pcX];
            } else {
                System.out.println("All directions are blocked! Try another piece.❌");
            }
        } else {
            System.out.println("Invalid coordinates! There's no player \""+
                    p.getName()+"\"'s piece there.❌");
        }

        return null;
    }

    /**
     * Class static method to obtain a valid piece to be moved according to user input.
     * -> Output: an EmptySquare object denoting the final destination of a piece.
     * */
    private static EmptySquare getDestination() {
        int dtX;
        int dtY;
        //Request destination position of the piece:
        System.out.println("Coordinates of final position of the piece:");
        System.out.print("    Enter coordinate X --> ");
        dtX = s.nextInt()-1;
        System.out.print("    Enter coordinate Y --> ");
        dtY = s.nextInt()-1;
        s.nextLine();
        if (dtX<0 || dtX>7 || dtY<0 || dtY>7) {
            System.out.println("Going off board limits! Get back on board!❌");
        }
        Square ds = board[dtY][dtX];
        if (ds instanceof EmptySquare && ds.onBoardDig()) {
            return (EmptySquare) board[dtY][dtX];
        } else {
            System.out.println("Invalid coordinates! Square occupied or invalid square color.❌");
        }
        return null;
    }

}
/**
 * Abstract class denoting each square on the board, occupied or not by a specific Piece.
 *
 * -> Holds a field for the position of a square (corresponds to matrix indices on Board).
 * -> The constructor takes a Position object as input
 * -> Getters and setters for Position are available, as well as methods
 * to check if the Square is on board and if it is on valid positions to hold a Piece.
 */
abstract class Square {
    //Position
    private Position pos;

    Square(Position pos){
        this.pos = pos;
    }

    protected void setPos(Position pos) {
        this.pos = pos;
    }

    protected Position getPos() {
        return pos;
    }

    /**
     * Class method to check if a Square is within Board limits.
     * -> Output: boolean value if checker passed.
     * */
    protected boolean onBoard(){
        return pos.getX() <= 7 && pos.getX() >= 0
                && pos.getY() <= 7 && pos.getY() >= 0;
    }

    /**
     * Class method to check if a Square can validly hold a Piece.
     * -> Output: boolean value if checker passed.
     * */
    protected boolean onBoardDig() {
        return this.onBoard()
                && (this.getPos().getX() + this.getPos().getY())%2==1;
    }
}

/**
 * Class denoting each empty Square on the board, inheriting Square.
 */
class EmptySquare extends Square{
    EmptySquare(int y,int x) {super(new Position(x,y));}
}

/**
 * Abstract class denoting each Piece on the board, inheriting Square.
 * -> Holds a field for type of enumeration Player, to which the Piece is assigned
 * -> Getters and setters for Player are available
 */
abstract class Piece extends Square{
    Piece(int y, int x) {super(new Position(x,y));}
    private Player plr;

    protected Player getPlayer() {
        return plr;
    }

    protected void setPlayer(Player plr) {
        this.plr = plr;
    }

    /**
     * Class method to check if a Piece can perform any valid jumps around.
     * -> Output: boolean value if jumps are available
     * */
    protected boolean checkJumps(){
        Square[][] board = Board.getBoard();
        int y = getPos().getY();
        int x = getPos().getX();
        int t = this.getPlayer().getTurn();

        return ((x>1 && y>1 && board[y-1][x-1] instanceof Piece &&
                    ((Piece) board[y-1][x-1]).getPlayer().getTurn()==3-t
                    && board[y-2][x-2] instanceof EmptySquare) || //Direction: NW

                (y>1 && x<6 && board[y-1][x+1] instanceof Piece &&
                    ((Piece) board[y-1][x+1]).getPlayer().getTurn()==3-t
                    && board[y-2][x+2] instanceof EmptySquare) || //Direction: NE

                (y<6 && x>1 && board[y+1][x-1] instanceof Piece &&
                    ((Piece) board[y+1][x-1]).getPlayer().getTurn()==3-t
                    && board[y+2][x-2] instanceof EmptySquare) || //Direction: SW

                (y<6 && x<6 && board[y+1][x+1] instanceof Piece &&
                    ((Piece) board[y+1][x+1]).getPlayer().getTurn()==3-t
                    & board[y+2][x+2] instanceof EmptySquare)); //Direction: SE
    }
    /**
     * Abstract class method to check if a Piece can perform any valid moves around.
     * -> Output: boolean value if moves are available
     * */
    abstract boolean checkPossible();

    /**
     * Class method to check if a Piece can perform a valid move to Position specified.
     * -> Output: boolean value if the move is valid
     * */
    protected boolean validMove(Position newp) {
        return Math.abs(getPos().getX()-newp.getX())==1
                && Math.abs(getPos().getY()- newp.getY())==1;
    }

    /**
     * Class method to check if a Piece can perform a valid jump to Position provided.
     * -> Input: Position newp, where the Piece might jump to
     * -> Output: boolean value if the jump is valid
     * */
    protected boolean validJump(Position newp){
        Square[][] board = Board.getBoard();
        int x1 = getPos().getX();
        int y1 = getPos().getY();
        int x2 = newp.getX();
        int y2 = newp.getY();
        if (Math.abs(x1-x2)==2 && Math.abs(y1-y2)==2
            && board[y1+(y2-y1)/2][x1+(x2-x1)/2] instanceof Piece
            && ((Piece) board[y1+(y2-y1)/2][x1+(x2-x1)/2]).getPlayer().
                getTurn()==3-getPlayer().getTurn()) {
            board[y1+(y2-y1)/2][x1+(x2-x1)/2] = new EmptySquare(y1+(y2-y1)/2,x1+(x2-x1)/2);
            Board.setBoard(board);
            System.out.println("Piece at "+x1+(x2-x1)/2+":"+y1+(y2-y1)/2+" is taken!✓");
            return true;
        }
        return false;
    }
}

/**
 * Enumeration type to denote the 2 players (Black and White) participating in the checkers game.
 * -> One can get the marker, turn and name of a Player
 * -> getSettings method to match a player with the input integer (turn)
 * */
enum Player {
    BLACK ('#',2,"Black"),
    WHITE ('●',1,"White"),
    DEF('_',0,"");

    final private char marker;
    final private int turn;
    final private String name;

    Player (char marker, int turn, String name) {
        this.marker = marker;
        this.turn = turn;
        this.name = name;
    }

    public static Player getSettings(int x) {
        for (Player col : Player.values()) {
            if (col.getTurn()==x) return col;
        }
        return Player.DEF;
    }

    public char getMarker() {
        return this.marker;
    }

    public String getName() {
        return this.name;
    }

    public int getTurn(){
        return this.turn;
    }
}

/**
 * Class denoting each white Piece on the board, inheriting Piece.
 */
class WhitePiece extends Piece{
    WhitePiece(int x,int y) {super(x,y); this.setPlayer(Player.WHITE);}

    @Override
    boolean checkPossible() {
        Square[][] board = Board.getBoard();
        int y = getPos().getY();
        int x = getPos().getX();
        return ( (y>=1 && x<=6 && board[y-1][x+1] instanceof EmptySquare)
                || (y>=1 && x>=1 && board[y-1][x-1] instanceof EmptySquare))
                || checkJumps();
    }

    @Override
    protected boolean validMove(Position newp) {
        return super.validMove(newp) && newp.getY() < getPos().getY();
    }

}

/**
 * Class denoting each black Piece on the board, inheriting Piece.
 */
class BlackPiece extends Piece {
    BlackPiece(int x,int y) {super(x,y); this.setPlayer(Player.BLACK);}

    @Override
    boolean checkPossible() {
        Square[][] board = Board.getBoard();
        int y = getPos().getY();
        int x = getPos().getX();
        return ( (y<=6 && x<=6 && board[y+1][x+1] instanceof EmptySquare)
                || (y<=6 && x>=1 && board[y+1][x-1] instanceof EmptySquare))
                || checkJumps();
    }

    @Override
    protected boolean validMove(Position newp) {
        return super.validMove(newp) && newp.getY() > getPos().getY();
    }

}

/**
 * Class denoting the position on the board of a certain object (Square or Piece).
 * -> Keeps 2 integer coordinates with getters & setters, constructor setting the coordinates.
 */
class Position {
    private int xcord;
    private int ycord;

    Position(int x, int y) {
        this.xcord = x;
        this.ycord = y;
    }

    protected int getX() {
        return xcord;
    }

    protected void setX(int xcord) {
        this.xcord = xcord;
    }

    protected int getY() {
        return ycord;
    }

    protected void setY(int ycord) {
        this.ycord = ycord;
    }

    @Override
    public String toString() {
        return "Position{" +
                "xcord=" + xcord +
                ", ycord=" + ycord +
                '}';
    }
}