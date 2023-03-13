/**
 * An instance of the class represents a single game. He must know when the game ended, who was the winner
 * and whether it ended in a draw.
 */
public class Game {

    /**
     * Fields
     */
    private Player playerX;
    private  Player playerO;
    private Renderer renderer;
    private int size = 4;
    private int winStreak = 3;
    private Board board;
    private int numFullSlot;

    /**
     * Constructor, defines a new game, with values depulative
     * @param playerX player x
     * @param playerO player 0
     * @param renderer console or none
     */
    public Game(Player playerX, Player playerO, Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.numFullSlot = 0;
        this.board = new Board();
    }

    /**
     * Another constructor, defines a size board, and a sequence a victory in the length of a win streak
     * If an invalid value is entered, the winning streak will be set to equal the size of the board
     * @param playerX player x
     * @param playerO player y
     * @param size size the board
     * @param winStreak win streak
     * @param renderer none or console
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.size = size;
        this.numFullSlot = 0;
        if (winStreak < 2 || winStreak > this.size){
            this.winStreak = this.size;
        }
        else {
            this.winStreak = winStreak;
        }
        this.renderer = renderer;
        this.board = new Board(this.size);
    }

    /**
     * get win streak
     * @return win streak
     */
    public int getWinStreak(){
        return this.winStreak;
    }

    /**
     * Goes through all the rows on the board, and checks if there is a victory of mark
     * @param board board
     * @param mark mark
     * @return if there is a victory of mark- true, else- false
     */
    private boolean checkWinRow(Board board, Mark mark){

        int counterMark =0;
        for (int row=0; row < this.board.getSize(); row++)
        {
            for (int col=0; col < this.board.getSize(); col++)
            {
                if (this.board.getMark(row,col) == mark){
                    counterMark ++;
                    if (counterMark == this.winStreak)
                    {
                        return true;
                    }
                }
                else {
                    counterMark = 0;
                }
            }
            counterMark = 0;
        }
        return false;
    }

    /**
     * Goes through all the columns on the board, and checks if there is a victory of mark
     * @param board board
     * @param mark mark
     * @return if there is a victory of mark- true, else- false
     */
    private boolean checkWinCol(Board board, Mark mark){
        int counterMark =0;
        for (int col=0; col < this.board.getSize(); col++)
        {
            for (int row=0; row < this.board.getSize(); row++)
            {
                if (this.board.getMark(row,col) == mark){
                    counterMark ++;
                    if (counterMark == this.winStreak)
                    {
                        return true;
                    }
                }
                else {
                    counterMark = 0;
                }
            }
            counterMark = 0;
        }
        return false;
    }

    /**
     * Checks if a right diagonal emerges from this coordinate, where victory appears
     * @param board board
     * @param mark mark
     * @param i row
     * @param j column
     * @return true if have, else- false
     */
    private boolean checkWinDiagonalRight(Board board, Mark mark, int i, int j){
        int counterMark =0;
        for (int row = i, col= j; (row < this.board.getSize()) && (col < this.board.getSize()) ; row++,
                col++) {
            if (this.board.getMark(row,col) == mark){
                counterMark++;
                if (counterMark == this.winStreak) {
                    return true;
                }
            }
            else {
                counterMark = 0;
            }
        }
        return false;
    }

    /**
     * Checks if a left diagonal emerges from this coordinate, where victory appears
     * @param board board
     * @param mark mark
     * @param i row
     * @param j col
     * @return  true if have, else- false
     */
    private boolean checkWinDiagonalLeft(Board board,Mark mark, int i, int j) {
        int counterMark = 0;
        while (i < this.board.getSize() && j >= 0) {
            if (this.board.getMark(i, j) == mark) {
                counterMark++;
                if (counterMark == this.winStreak) {
                    return true;
                }
            }
            else {
                counterMark = 0;
            }
            i++;
            j--;
        }
        return false;
    }

    /**
     *   * Goes through all the diagonals on the board, and checks if there is a victory of mark
     * @param board board
     * @param mark mark
     * @return if there is a victory of mark- true, else- false
     */
    private boolean checkWinDiagonal(Board board, Mark mark) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (checkWinDiagonalRight(board,mark, row, col)) {
                    return true;
                }
                if (checkWinDiagonalLeft(board, mark, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a win situation on the board for the mark player
     * @param board board
     * @param mark mark
     * @return if have- true, else- false
     */
    private boolean checkWinGame(Board board, Mark mark){
        return (checkWinRow(board,mark) || checkWinCol(board,mark) || checkWinDiagonal(board,mark));
    }

    /**
     * check if all the slots in bord is full
     * @return if have- true, else- false
     */
    private boolean checkFullBoard(){
        return (this.size * this.size) == this.numFullSlot;
    }

    /**
     Runs a game move - from start to finish, and returns the winner.
     The game ends when one of the players has a winning streak or when there are no empty spaces left on the
     board. In case the game ends in a tie, BLANK will be returned
     * @return the winner
     */
    public Mark run(){
        int counter = 0;
        this.renderer.renderBoard(this.board);
        while (!checkFullBoard()) {
            if (counter % 2 == 0) {
                this.playerX.playTurn(this.board, Mark.X);
                this.renderer.renderBoard(this.board);
                if ((counter/2 >= this.winStreak-1) && checkWinGame(this.board, Mark.X)){
                    return Mark.X;
                }
            }
            else {
                this.playerO.playTurn(this.board, Mark.O);
                this.renderer.renderBoard(this.board);
                if ((counter/2 >= this.winStreak-1) && checkWinGame(this.board, Mark.O)){
                    return Mark.O;
                }
            }
            counter++;
            this.numFullSlot++;
        }
        return Mark.BLANK;
    }
}
