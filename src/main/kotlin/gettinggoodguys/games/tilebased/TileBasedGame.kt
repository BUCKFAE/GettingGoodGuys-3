package gettinggoodguys.games.tilebased

import gettinggoodguys.games.Game
import gettinggoodguys.games.movement.directions.AbsoluteDirection
import gettinggoodguys.games.tilebased.tile.NoTileAtCoordinatesException
import gettinggoodguys.games.tilebased.tile.Tile
import gettinggoodguys.games.tilebased.tile.TileType
import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*
import kotlin.random.Random

abstract class TileBasedGame(val gameBoardSizeX: Int, val gameBoardSizeY: Int, defaultTileType: TileType) : Game {

    // Stores all tiles of the gameBoard
    /**
     * Example: 3 width 4 height
     * - Format: x/width Array: [Array, Array, Array] contains [gameBoardSizeX] height arrays with [gameBoardSizeY] length
     * -               [y]    [y]    [y]
     * -               [y]    [y]    [y]
     * -               [y]    [y]    [y]
     * -               [y]    [y]    [y]
     */
    private val tiles: Array<Array<Tile>> = Array(gameBoardSizeX) { column ->
        Array(gameBoardSizeY) { row ->
            Tile(
                column,
                row,
                defaultTileType
            )
        }
    }

    /**
     * This method changes how a cell of the game dataGrid is rendered
     */
    open fun DataGrid<String>.gameCellFormat() {
        cellFormat {
            graphic = cache {
                label(it) { }
            }
        }
    }

    override fun drawGame(target: EventTarget) {
        throw IllegalStateException("Cannot draw game without gameData")
    }

    override fun drawGame(target: EventTarget, gameData: ObservableList<String>) {
        with(target) {
            datagrid(gameData) {
                paddingLeft = 10.0
                paddingRight = 0.0
                paddingTop = 10.0
                paddingBottom = 0.0

                style {
                    fontWeight = FontWeight.EXTRA_BOLD
                    borderColor += box(
                        top = Color.RED,
                        right = Color.DARKGREEN,
                        left = Color.ORANGE,
                        bottom = Color.PURPLE
                    )
                }
                horizontalCellSpacing = 1.0
                verticalCellSpacing = 1.0

                cellHeight = 50.0
                cellWidth = 50.0
                prefHeight = gameBoardSizeY * cellHeight + 20 + (gameBoardSizeY + 2) * verticalCellSpacing
                prefWidth = gameBoardSizeX * cellWidth + 20 + (gameBoardSizeX + 2) * horizontalCellSpacing

                maxCellsInRow = gameBoardSizeX

                // The actual cell value
                gameCellFormat()
            }
        }
    }

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
        if (x < 0 || x >= gameBoardSizeX || y < 0 || y >= gameBoardSizeY) {
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
            AbsoluteDirection.UP -> {
                newY--
            }
            AbsoluteDirection.RIGHT -> {
                newX++
            }
            AbsoluteDirection.DOWN -> {
                newY++
            }
            AbsoluteDirection.LEFT -> {
                newX--
            }
        }

        // Return either the tile or null if there is no tile in this direction
        if (isTileAt(newX, newY)) return getTileAt(newX, newY)
        return null
    }

    /**
     * Getting a random Tile on the gameBoard
     */
    fun getRandomTile(random: Random = Random): Tile {

        // Coordinates of the random tile
        val tileX = random.nextInt(gameBoardSizeX)
        val tileY = random.nextInt(gameBoardSizeY)

        // Returning the tile
        return getTileAt(tileX, tileY)
    }

    /**
     * Returns all tiles of the given row
     */
    fun getTilesOfRow(row: Int): Array<Tile> {
        return Array(gameBoardSizeX) { currentX -> getTileAt(currentX, row) }
    }

    /**
     * Returns all tiles of the given col
     */
    fun getTilesOfCol(col: Int): Array<Tile> {
        return Array(gameBoardSizeY) { currentY -> getTileAt(col, currentY) }
    }

    /**
     * Returns all tiles in order
     */
    fun getAllTilesInOrder(): Array<Tile> {
        return Array(gameBoardSizeX * gameBoardSizeY) { id ->
            val currY = id / gameBoardSizeX
            val currX = id % gameBoardSizeX
            getTileAt(currX, currY)
        }
    }

    /**
     * Returns a pretty String representing the current game
     * Should be used instead of toString() when the game
     * is played on commandline
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
                        sb.append(getTileAt(currTileX, currTileY).tileType.toString())
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