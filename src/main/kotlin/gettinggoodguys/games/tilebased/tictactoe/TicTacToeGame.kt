package gettinggoodguys.games.tilebased.tictactoe

import gettinggoodguys.games.Game
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.games.tilebased.TileType

class TicTacToeGame(gameSizeX: Int, gameSizeY: Int) : TileBasedGame(gameSizeX, gameSizeY, Game.GameTypes.TicTacToe) {

    override val defaultTileType: TileType = TicTacToeTileType.EMPTY_TILE

    override fun step() {
        TODO("Not yet implemented")
    }

    override fun isAlive() {
        TODO("Not yet implemented")
    }
}