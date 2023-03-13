import java.util.Random;

/**
 *  Whatever Player implements Player
 */
public class WhateverPlayer implements Player {

    /**
     * This player operates with the strategy of choosing a slot randomly on the board
     * @param board board
     * @param mark mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        Random random = new Random();
        int row = random.nextInt(board.getSize());
        int col = random.nextInt(board.getSize());
        while (!board.putMark(mark, row, col)) {
            row = random.nextInt(board.getSize());
            col = random.nextInt(board.getSize());
        }
    }
}
