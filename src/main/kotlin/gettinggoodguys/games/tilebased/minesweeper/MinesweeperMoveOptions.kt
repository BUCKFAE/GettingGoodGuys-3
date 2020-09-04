package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.MoveOptions

class MinesweeperMoveOptions(val x: Int, val y: Int, val moveType: MoveType) : MoveOptions {

    companion object {
        val NO_MOVE = MinesweeperMoveOptions(-1, -1, MoveType.NOTHING)
    }

    enum class MoveType {
        NOTHING,
        TOGGLE_FLAG,
        CLICK
    }

}