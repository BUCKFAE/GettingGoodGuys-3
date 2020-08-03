package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.movement.directions.AbsoluteDirection
import gettinggoodguys.games.movement.directions.RelativeDirection
import gettinggoodguys.games.tilebased.Tile
import gettinggoodguys.games.tilebased.TileBasedGame

class SnakeGame(gameSizeX: Int, gameSizeY: Int) : TileBasedGame(gameSizeX, gameSizeY, defaultTileType = SnakeTileType.EMPTY_TILE) {

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
        for(currentBodyPieceID in 0 until initialSnakeLength) {

            // Calculating the coordinates for the head
            val headPosX = gameSizeX / 2
            val headPosY = gameSizeY / 2

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
    }

    fun moveToRelativeDir(relativeDirection: RelativeDirection) {

        // Storing the coordinates of the head
        val headPosX = snakeBodyTiles[0].posX
        val headPosY = snakeBodyTiles[0].posY

        // Getting the absolute direction to move
        val absoluteDirection = relativeDirection.toAbsoluteDirection(currentAbsoluteOrientation)

        val newHeadTile = getTileInAbsoluteDir(headPosX, headPosY, absoluteDirection)

        // The snake hit a wall
        if(newHeadTile == null) {
            println("Hit a wall")
        }
        // The snake did not hit a wall
        else {

            // Removing the last tile from the snake
            snakeBodyTiles[snakeBodyTiles.size - 1].tileType = SnakeTileType.EMPTY_TILE
            snakeBodyTiles.removeAt(snakeBodyTiles.size - 1)

            // If the snake did not hit itself
            if(newHeadTile.tileType != SnakeTileType.SNAKE_BODY_TILE) {

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



    private fun removeLastTileSnake() {

    }

    override fun step() {
        TODO("Not yet implemented")
    }

    override fun isAlive(): Boolean {
        return isAlive
    }
}