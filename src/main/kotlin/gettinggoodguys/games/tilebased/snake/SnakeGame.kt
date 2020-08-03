package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.Game
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.games.tilebased.TileType

class SnakeGame(gameSizeX: Int, gameSizeY: Int) : TileBasedGame(gameSizeX, gameSizeY, Game.GameTypes.Snake) {

    override val defaultTileType: TileType = SnakeTileType.EMPTY_TILE

    override fun step() {
        TODO("Not yet implemented")
    }

    override fun isAlive() {
        TODO("Not yet implemented")
    }
}