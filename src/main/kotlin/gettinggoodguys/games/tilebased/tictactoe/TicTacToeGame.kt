package gettinggoodguys.games.tilebased.tictactoe

import gettinggoodguys.games.MoveOptions
import gettinggoodguys.games.tilebased.TileBasedGame

class TicTacToeGame(gameSizeX: Int, gameSizeY: Int) : TileBasedGame(gameSizeX, gameSizeY, TicTacToeTileType.EMPTY_TILE) {


    override fun step(moveOption: MoveOptions) {
        TODO("Not yet implemented")
    }

    override fun isAlive(): Boolean {
        TODO("Not yet implemented")
    }
}