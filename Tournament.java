/**
 * The role of the Tournament department is to run a tournament of several games.
 */
public class Tournament {

    /**
     * Fields
     */
    private int rounds;
    private Renderer renderer;
    private Player[] players = new Player[2];
    private  static  final  int ROUNDS = 0;
    private  static  final  int SIZE = 1;
    private static final int WINSTREAK = 2;
    private  static  final  int RENDER = 3;
    private  static  final  int PLAYER1 = 4;
    private  static  final  int PLAYER2 = 5;

    /**
     * constructor
     * @param rounds rounds of game
     * @param renderer none or console
     * @param players player x , player O
     */
    public Tournament(int rounds, Renderer renderer, Player[] players) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
    }

    /**
     * Called by main, and that's where all the logic of the rotations and calls to the game happens.
     * @param size size of board
     * @param winStreak win streak
     * @param playerNames player names
     */
    public void playTournament(int size, int winStreak, String[] playerNames)
    {
        int[] resPlayersArray = {0,0,0};
        for (int i=0; i < this.rounds; i++) {
            Game game = new Game(players[i%2], players[(i+1)%2], size, winStreak, this.renderer);
            Mark whoWin = game.run();
            if (whoWin == Mark.X) {
                resPlayersArray[i % 2]++;
            }
            if (whoWin == Mark.O) {
                resPlayersArray[(i + 1) % 2]++;
            }
            if (whoWin == Mark.BLANK) {
                resPlayersArray[2]++;
            }
        }
        System.out.println("######### Results #########");
        System.out.println("Player 1, " + playerNames[0] + " won: " + resPlayersArray[0] + " rounds");
        System.out.println("Player 2, " + playerNames[1] + " won: " + resPlayersArray[1] + " rounds");
        System.out.println("Ties: " + resPlayersArray[2]);
    }

    /**
     * the main
     * @param args Java Tournament [round count] [size] [win_streak] [render target: console/none]
     */
    public static void main(String[] args){
        int roundCount = Integer.parseInt(args[ROUNDS]);
        int size = Integer.parseInt(args[SIZE]);
        int winStreak = Integer.parseInt(args[WINSTREAK]);

        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(args[RENDER],size);

        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(args[PLAYER1].toLowerCase());
        Player player2 = playerFactory.buildPlayer(args[PLAYER2].toLowerCase());
        if (player1 == null || player2 == null){
            System.out.println("Choose a player, and start again");
            System.out.println("The players: [human, clever, whatever, genius]");
            return;
        }
        Player[] players = {player1, player2};
        String[] playerNames = {args[PLAYER1], args[PLAYER2]};

        Tournament tournament = new Tournament(roundCount,renderer,players);
        tournament.playTournament(size,winStreak,playerNames);
    }
}
