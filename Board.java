/**
 * The department is responsible for the condition of the board: the size of the board,
 * marking squares and keeping everything marked on the board.
 * The numbering of the slots on the board starts with 0.
 */

public class Board {

    /**
     * Fields
     */
    private int size = 4;
    private Mark[][] board;

    /**
     * A dipole constructor, defines a new empty array of dipole size
     */
    public Board() {
        initializeBoard();
    }

    /**
     * Another constructor, defines a new empty board of size
     * @param size size the board
     */
    public Board(int size){
        this.size = size;
        initializeBoard();
    }

    /**
     * initialize the Board with BLANK
     */
    private void initializeBoard() {
        this.board =  new Mark[this.size][this.size];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    /**
     * get size of board
     * @return size of board
     */
    public int getSize(){
        return this.size;
    }

    /**
     * get the Board
     * @return board
     */
    public Mark[][] getBoard(){ return board; }

    /**
     * Trying to mark the slot (row,col) in Mark.
     * @param mark x or o
     * @param row row
     * @param col col
     * @return Returns true if you checked the box successfully. else, false
     */
    public boolean putMark(Mark mark, int row, int col){
        if (row < 0 || row >= this.size || col < 0 || col >= this.size){
            return false;
        }
        if (this.board[row][col] != Mark.BLANK){
            return false;
        }
        this.board[row][col] = mark;
        return true;
    }

    /**
     * Return the mark that is in the given slot. In case of Invalid coordinates will return BLANK
     * @param row row
     * @param col col
     * @return Mark
     */
    public Mark getMark(int row, int col){
        if (row < 0 || row >= this.size || col < 0 || col >= this.size){
            return Mark.BLANK;
        }
        return this.board[row][col];
    }

}
