package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.MoveOptions
import gettinggoodguys.games.movement.directions.AbsoluteDirection
import gettinggoodguys.games.movement.directions.RelativeDirection
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.games.tilebased.tile.NoTileAtCoordinatesException
import gettinggoodguys.games.tilebased.tile.Tile
import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class SnakeGame(gameSizeX: Int, gameSizeY: Int) :
    TileBasedGame(gameSizeX, gameSizeY, defaultTileType = SnakeTileType.EMPTY_TILE) {

    /*
     * Settings (TODO: Change this)
     */

    // The initial length of the snake
    private val initialSnakeLength = 3

    // Stores references to all tiles of the snake
    private val snakeBodyTiles = arrayListOf<Tile>()

    // Stores the current absolute orientation of the snake
    private var currentAbsoluteOrientation = AbsoluteDirection.DOWN

    // stores if the snake is currently alive
    private var isAlive = true

    init {

        /*
         * Creating the Snake on the Grid
         *
         * Currently the Snake will be placed looking downwards in the middle of the gameboard
         */

        // Creating the Snake
        for (currentBodyPieceID in 0 until initialSnakeLength) {

            // Calculating the coordinates for the head
            val headPosX = gameSizeX / 2
            val headPosY = gameSizeY / 2

            // Throws an error if there is no tile at the given spot, this will only
            // happen if the snake is to long for the given gameBoard bounds.
            if (!isTileAt(headPosX, headPosY - currentBodyPieceID)) {
                throw NoTileAtCoordinatesException(
                    headPosX, headPosY - currentBodyPieceID, this,
                    "The error occurred when trying to create a new snake game. Check if the snake " +
                            "is to long for the given gameBoard bounds."
                )
            }

            // Getting the tile from the gameboard as reference
            val newTile = getTileAt(headPosX, headPosY - currentBodyPieceID)

            // Setting the tileType for the snake
            if (currentBodyPieceID == 0) {
                newTile.tileType = SnakeTileType.SNAKE_HEAD_TILE
            } else {
                newTile.tileType = SnakeTileType.SNAKE_BODY_TILE
            }

            // Adding the tile to the list
            snakeBodyTiles.add(newTile)
        }

        spawnFood()
    }

    fun moveToRelativeDir(relativeDirection: RelativeDirection) {

        // TODO: Improve this
        if (!isAlive()) {
            throw IllegalStateException("Tried to move a dead Snake.")
        }

        // Storing the coordinates of the head
        val headPosX = snakeBodyTiles[0].posX
        val headPosY = snakeBodyTiles[0].posY

        // Getting the absolute direction to move
        val absoluteDirection = relativeDirection.toAbsoluteDirection(currentAbsoluteOrientation)

        val newHeadTile = getTileInAbsoluteDir(headPosX, headPosY, absoluteDirection)

        // The snake hit a wall
        if (newHeadTile == null) {
            isAlive = false
        }
        // The snake did not hit a wall
        else {

            // We stepped on food
            if (newHeadTile.tileType == SnakeTileType.FOOD_TILE) {

                //TODO: Check if we won the game

                spawnFood()
            } else {
                // Removing the last tile from the snake
                snakeBodyTiles[snakeBodyTiles.size - 1].tileType = SnakeTileType.EMPTY_TILE
                snakeBodyTiles.removeAt(snakeBodyTiles.size - 1)
            }

            // If the snake did not hit itself
            if (newHeadTile.tileType != SnakeTileType.SNAKE_BODY_TILE) {

                // Setting the tileType of the previous head to body
                snakeBodyTiles[0].tileType = SnakeTileType.SNAKE_BODY_TILE

                // Setting the tileType of the new head
                newHeadTile.tileType = SnakeTileType.SNAKE_HEAD_TILE

                // Adding the new headTile to the snakeBodyTiles
                snakeBodyTiles.add(0, newHeadTile)

                // Changing the absolute dir we are currently facing
                currentAbsoluteOrientation = absoluteDirection
            }
            // The snake did hit itself
            else {
                isAlive = false
            }
        }

    }


    private fun spawnFood() {
        var newFoodTile: Tile = getRandomTile()

        // Ensuring the new tile is not a Snake Tile
        while (newFoodTile.tileType == SnakeTileType.SNAKE_HEAD_TILE
            || newFoodTile.tileType == SnakeTileType.SNAKE_BODY_TILE
        ) {
            newFoodTile = getRandomTile()
        }

        newFoodTile.tileType = SnakeTileType.FOOD_TILE
    }

    override fun step(moveOption: MoveOptions) {
        when (moveOption) {
            SnakeMoveOptions.AHEAD -> {
                moveToRelativeDir(RelativeDirection.AHEAD)
            }
            SnakeMoveOptions.LEFT -> {
                moveToRelativeDir(RelativeDirection.LEFT)
            }
            SnakeMoveOptions.RIGHT -> {
                moveToRelativeDir(RelativeDirection.RIGHT)
            }
        }
    }

    override fun isAlive(): Boolean {
        return isAlive
    }

    override fun drawGame(target: EventTarget) {
        throw IllegalStateException("Cant draw game without GameData")
    }

    override fun drawGame(target: EventTarget, gameData: ObservableList<String>) {
        with(target)
        {
            anchorpane() {
                datagrid(gameData) {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        borderColor += box(
                            top = Color.RED,
                            right = Color.DARKGREEN,
                            left = Color.ORANGE,
                            bottom = Color.PURPLE
                        )


                    }

                    anchorpaneConstraints { leftAnchor = 5.0; topAnchor = 5.0 }

                    horizontalCellSpacing = 1.0
                    verticalCellSpacing = 1.0

                    cellHeight = 50.0
                    cellWidth = 50.0

                    prefHeight = gameBoardSizeY * cellHeight + 50
                    prefWidth = gameBoardSizeX * cellWidth + 50

                    maxCellsInRow = gameBoardSizeX

                    // The actual cell value
                    cellFormat {
                        graphic = cache {
                            if (it == "X") {
                                vbox { style { backgroundColor += c("red") } }
                            } else if (it == "H") {
                                vbox { style { backgroundColor += c("aqua") } }
                            } else if (it == "S") {
                                vbox { style { backgroundColor += c("blue") } }
                            } else if (it == " ") {
                                vbox { style { backgroundColor += c("white") } }
                            } else if (it == "F") {
                                vbox { style { backgroundColor += c("green") } }
                            } else {
                                label(it) { }
                            }
                        }
                    }
                }
            }
        }
    }
}