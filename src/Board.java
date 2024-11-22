import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int  SIZE = 3;

    private final int totalTiles;
    private ArrayList<Tile> tiles;

    public Board() {
        totalTiles = SIZE * SIZE;

        // Initiate board
        reset();
    }

    // Validate a marker placement
    private boolean validatePlacement(int index) {
        if (index < 0 || tiles.size() - 1 < index) return false;
        var tile = tiles.get(index);

        return !tile.isMarked();
    }

    // Attempts to place a marker in position (1-9)
    public boolean placeMarker(char character, int position) {
        var index = position - 1;

        // Check if placement can be performed
        if (!validatePlacement(index)) return false;

        // Update tile at position
        var tile = tiles.get(index);

        tile.mark(character);

        return true;
    }

    public void draw() {
        Logger.newline();

        for(int row = 0; row < SIZE; row++) {
            StringBuilder rowContent = new StringBuilder();
            for(var col = 0; col < SIZE; col++ ) {
                var index = row * 3 + col;
                var tile = tiles.get(index);
                var separator = col == SIZE - 1 ? "" : "|";

                rowContent.append(tile.toString()).append(separator);
            }
            Logger.print(rowContent.toString());

            if (row != SIZE - 1) {
                Logger.print("---+---+---");
            }
        }

        Logger.newline();
    }

    public List<Tile> getAvailableTiles() {
        return tiles.stream().filter(tile -> !tile.isMarked()).toList();
    }

    public Tile getTileAt(int index) {
        return tiles.get(index);
    }

    public void reset() {
        var newTiles = new ArrayList<Tile>();

        for(int i = 0; i < totalTiles; i++) {
            var tile = new Tile();
            newTiles.add(tile);
        }

        this.tiles = newTiles;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getSize() {
        return SIZE;
    }
}
