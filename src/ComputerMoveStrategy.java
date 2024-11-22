import java.util.Random;

public class ComputerMoveStrategy implements MoveStrategy {

    @Override
    public int move(Board board) {
        // Get tiles, filter out tiles that are already marked
        var availableTiles = board.getAvailableTiles();

        // Pick a random tile to mark
        var random = new Random();
        var tileToMark = availableTiles.get(random.nextInt(availableTiles.size()));

        // Return index of chosen tile
        return board.getTiles().indexOf(tileToMark) + 1;
    }
}
