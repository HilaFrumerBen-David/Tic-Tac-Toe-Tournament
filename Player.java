/**
 * An interface that unites all players
 */
public interface Player {

    /**
     * Executes the player's strategy.
     * @param board board
     * @param mark mark
     */
    public void playTurn(Board board, Mark mark);
}
