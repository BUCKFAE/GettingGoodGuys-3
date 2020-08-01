package gettinggoodguys.games.tilebased

import gettinggoodguys.games.Game
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import gettinggoodguys.games.tilebased.tictactoe.TicTacToeTileType

abstract class TileBasedGame(private val sizeX: Int, private val sizeY: Int, gameType: Game.GameTypes) : Game {

    // Stores all tiles of the gameBoard
    private val tiles: MutableList<MutableList<Tile>> = mutableListOf()

    init {

        for (currentRow in 0 until sizeY) {

            val currentRowTiles = mutableListOf<Tile>()

            for (currentCol in 0 until sizeX) {

                val defaultTileType: TileType = when (gameType) {
                    Game.GameTypes.TicTacToe -> TicTacToeTileType.EMPTY_TILE
                    Game.GameTypes.Snake -> SnakeTileType.EMPTY_TILE
                }

                // Creating the new tile and adding it to the arraylist
                val newTile = Tile(currentCol, currentRow, defaultTileType)
                currentRowTiles.add(newTile)
            }

            tiles.add(currentRowTiles)

        }
    }


    fun getTileAt(x: Int, y: Int): Tile {
        // TODO: Check for bad x / y values
        return tiles[x][y]
    }

    /**
     * Returns a pretty String representing the current game
     * Should be used instead of toString() when the game
     * is played on commandline
     *
     * Example output:
     *
     *        X
     *   +-+-+-+-+-+
     *   |X|X|X|S|S|
     * Y +-+-+-+-+-+
     *   |X|X|X|S|X|
     *   +-+-+-+-+-+
     *
     * TODO: Write Tests
     */
    open fun toPrettyString(): String {

        // This does not include the X / Y marks on the grid
        val finalStringWidth: Int = tiles.size * 2 + 1
        val finalStringHeight: Int = tiles[0].size * 2 + 1
        val sb = StringBuilder()

        // Adding X up top
        for (i in 0 until finalStringWidth / 2 + 2) {
            sb.append(" ")
        }
        sb.append("X")
        sb.append("\n")

        // Adding the TileInfo
        for (y in 0 until finalStringHeight) {
            for (x in 0 until finalStringWidth) {

                // Adding "  " or the y mark
                if (x == 0) {
                    // Adding the y mark
                    if (y == finalStringHeight / 2) {
                        sb.append("Y ")
                    } else {
                        sb.append("  ")
                    }
                }

                // Adding "+-+-+-+"
                if (y % 2 == 0) {

                    // Adding "+"
                    if (x % 2 == 0) {
                        sb.append("+")
                    }
                    // Adding "-"
                    else {
                        sb.append("-")
                    }
                } else {
                    // Adding "|"
                    if (x % 2 == 0) {
                        sb.append("|")
                    }
                    // Adding the actual info
                    else {
                        // Calculating the coordinates of the current tile
                        val currTileX = x / 2
                        val currTileY = y / 2

                        // Appending the TileInfo of the current Tile
                        sb.append(tiles[currTileX][currTileY].getTileType().toString())
                    }
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }
}