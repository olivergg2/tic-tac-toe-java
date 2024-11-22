public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private int gameMode;
    private int totalGames;

    public Game() {
        setup();
    }

    public void setup() {
        totalGames = 0;

        // Create new board
        this.board = new Board();

        // Choose a game mode
        chooseGameMode();

        // Create players
        createPlayers();
    }

    public void chooseGameMode() {
        Logger.print("Välkommen till Tic-Tac-Toe!");
        Logger.print("1. Spela två spelare");
        Logger.print("2. Spela mot dator");
        Logger.print("Välj ett av alternativen ovan (1 eller 2):");

        var choice = 0;

        while(choice != GameModes.Human && choice != GameModes.Computer) {
            choice = Logger.readInt("123", "Ogiltigt input! Välj 1 eller 2.");
        }

        gameMode = choice;
    }

    // Prompt and create users depending on game mode
    public void createPlayers() {
        if (gameMode == GameModes.Human) {
            System.out.print("(Spelare 1) ");
            player1 = createHumanPlayer('X');

            System.out.print("(Spelare 2) ");
            player2 = createHumanPlayer('O');
        } else if (gameMode == GameModes.Computer) {
            player1 = createHumanPlayer('X');
            player2 = new Computer('O');
        }
    }

    // Prompt and create a human player
    public Player createHumanPlayer(char character) {
        var name = Logger.read("Vad heter du?");

        return new Human(character, name);
    }

    public void run() {
        // Draw board
        board.draw();

        // Begin game loop
        var currentPlayer = player1;
        Player winner = null;
        var gameEnding = 0;

        // Run game loop until a winner is present, or the game has ended in a draw
        while(gameEnding == 0) {
            var isWin = turn(currentPlayer);

            // End game if currentPlayer has won or the game is a draw
            if (isWin) {
                winner = currentPlayer;
                gameEnding = GameEndings.PlayerWin;
            } else if (checkDraw()) {
                gameEnding = GameEndings.Draw;
            }

            board.draw();

            // Pass over turn to next player
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }

        // Update the winner's stats if the game ended with a player win
        if (gameEnding == GameEndings.PlayerWin) {
            winner.won();
        }

        // Post game logic
        totalGames++;
        summarize(winner);
        restart();
    }

    private boolean turn(Player player) {
        // Let player know it is their turn and ask for marker placement
        Logger.print(String.format("%ss tur! Vilken ruta (1-9)?", player.getName()));

        boolean validMarkerPlacement = false;

        while(!validMarkerPlacement) {
            var placement = player.doMove(board);

            // Attempt marker placement at position
            validMarkerPlacement = board.placeMarker(player.getMark(), placement);
        }

        // Check if the player has won
        return checkWin(player);
    }

    private boolean checkDraw() {
        // Game ends in a draw when no moves are possible and there are no winner
        return board.getAvailableTiles().isEmpty();
    }

    // Check if a player meets the criteria for winning
    private boolean checkWin(Player player) {
        var size = board.getSize();
        var mark = player.getMark();

        // Check rows
        for (int row = 0; row < size; row++) {
            if (checkLine(mark, row * size, 1)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < size; col++) {
            if (checkLine(mark, col, size)) {
                return true;
            }
        }

        // Check diagonals
        // (0, 4, 8) och (6, 4, 2)
        return checkLine(mark, 0, size + 1) || checkLine(mark, size - 1, size - 1);
    }

    // Helper method for checking a sequence of tiles in the board
    private boolean checkLine(char mark, int start, int step) {
        var size = board.getSize();

        for (int i = 0; i < size; i++) {
            if (board.getTileAt(start + i * step).getMark() != mark) {
                return false;
            }
        }

        return true;
    }

    private void restart() {
        // Let players know a new game is starting
        Logger.newline();
        Logger.print("Startar en ny omgång...");

        // Reset board and run game again
        board.reset();
        run();
    }

    private void summarize(Player winner) {
        // Line break
        Logger.print("=".repeat(25));

        // Present round result
        var message = winner != null ? String.format("%s vann spelet!", winner.getName()) : "Spelet slutade i oavgjort!";
        Logger.print(message);
        Logger.newline();

        // Player 1's stats
        System.out.printf("%s har vunnit %d spel%n", player1.getName(), player1.getWins());

        // Player 2's stats
        Logger.print(player2.getName(), "har vunnit", Integer.toString(player2.getWins()), "spel");

        // Present total games played
        Logger.newline();
        Logger.print("Antal spel spelade: ", Integer.toString(totalGames));

        // Line break
        Logger.print("=".repeat(25));
    }

    // Game mode constants
    private static class GameModes {
        public static final int Human = 1;
        public static final int Computer = 2;
    }

    // Game endings constants
    private static class GameEndings {
        public static final int PlayerWin = 1;
        public static final int Draw = 2;
    }
}
