package gettinggoodguys.games.tilebased

import gettinggoodguys.games.movement.directions.AbsoluteDirection
import gettinggoodguys.games.Game
import gettinggoodguys.games.tilebased.tile.NoTileAtCoordinatesException
import gettinggoodguys.games.tilebased.tile.Tile
import gettinggoodguys.games.tilebased.tile.TileType
import java.lang.IllegalArgumentException

abstract class TileBasedGame(val gameBoardSizeX: Int, val gameBoardSizeY: Int, defaultTileType: TileType) : Game {

    // Stores all tiles of the gameBoard
    private  val tiles: Array<Array<Tile>> = Array(gameBoardSizeX) { row -> Array(gameBoardSizeY) { column ->
        Tile(
            row,
            column,
            defaultTileType
        )
    } }

    /**
     * Gets the tile at the given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the tile at the given coordinate
     */
    fun getTileAt(x: Int, y: Int): Tile {
        if (!isTileAt(x, y)) {
            throw NoTileAtCoordinatesException(x, y, this)
        }

        return tiles[x][y]
    }

    /**
     * Checks if there is a tile a the given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if there is a tile, false if there is no tile
     */
    fun isTileAt(x: Int, y: Int): Boolean {
        if(x < 0 || x >= gameBoardSizeX || y < 0 || y >= gameBoardSizeY) {
            return false
        }
        return true
    }

    /**
     * Gets the tile in the given absolute direction
     * This is useful if you want to e.g. get the tile above another tile
     * @param tileX the x coordinate of the start tile
     * @param tileY the y coordinate of the start tile
     * @param absoluteDirection the direction where we want to get the tile
     * @return returns either the Tile in the given direction or null if there is no tile in that direction
     */
    fun getTileInAbsoluteDir(tileX: Int, tileY: Int, absoluteDirection: AbsoluteDirection): Tile? {

        var newX = tileX
        var newY = tileY

        when (absoluteDirection) {
            AbsoluteDirection.UP -> { newY-- }
            AbsoluteDirection.RIGHT -> { newX++ }
            AbsoluteDirection.DOWN -> { newY++ }
            AbsoluteDirection.LEFT -> { newX-- }
        }

        // Return either the tile or null if there is no tile in this direction
        if(isTileAt(newX, newY)) return getTileAt(newX, newY)
        return null
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
                        sb.append(tiles[currTileX][currTileY].tileType.toString())
                    }
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    // TODO: Write better toString
    override fun toString(): String {
        return toPrettyString()
    }
}