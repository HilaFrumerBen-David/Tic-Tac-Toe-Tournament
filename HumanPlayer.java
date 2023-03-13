import  java.util.Scanner;

/**
 * Human Player implements Player
 */
public class HumanPlayer implements Player {

    /**
     * Fields
     */
    private final Scanner scanner;

    /**
     * Constructor
     */
    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Requests coordinates from the user and places the mark in a measure
     * And the coordinates are "good" - normal and not occupied, If not, it will ask for additional input.
     * @param board board
     * @param mark mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {

        System.out.println("Player " + mark + ", type coordinates:");
        int num = scanner.nextInt();
        int row = num / 10;
        int col = num % 10;
        while (!board.putMark(mark, row, col)) {
            System.out.println("Invalid coordinates, type again: ");
            num = scanner.nextInt();
            row = num / 10;
            col = num % 10;
        }
    }
}
