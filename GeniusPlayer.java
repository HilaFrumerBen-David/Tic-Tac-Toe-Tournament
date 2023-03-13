/**
 * Genius Player implements Player
 */
public class GeniusPlayer implements Player {

    /**
     * This player has a strategy that is smarter than the clever player and beats him most of the times.
     * @param board board
     * @param mark mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.putMark(mark, i, j)) {
                    return;
                }
            }
        }
    }
}
