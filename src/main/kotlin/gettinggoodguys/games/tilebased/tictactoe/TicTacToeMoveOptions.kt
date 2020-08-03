package gettinggoodguys.games.tilebased.tictactoe

import gettinggoodguys.games.MoveOptions

data class TicTacToeMoveOptions(val posX: Int, val posY: Int): MoveOptions {

    override fun toString(): String {
        return "TicTacToeMove: x $posX y $posY"
    }
}