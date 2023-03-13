import java.util.Random;

/**
 * Clever Player that implements Player
 */
public class CleverPlayer implements Player {

    /**
     * This player has a strategy that is smarter than the whatever player and beats him most of the times.
     * @param board board
     * @param mark mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for(int i = 0; i < 2; i++){
            for(int j=0; j< board.getSize(); j++){
                if(board.putMark(mark, i, j)){
                    return;
                }
            }
        }
        Random random = new Random();
        int min = 2;
        int max = board.getSize();
        int row = random.nextInt(max - min) + min;
        int col = random.nextInt(max);
        while (!board.putMark(mark, row, col)) {
            row = random.nextInt(max - min) + min;
            col = random.nextInt(max);
        }
    }
}
