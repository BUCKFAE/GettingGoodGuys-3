package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.MoveOptions

class MinesweeperMoveOptions(val x: Int, val y: Int, val moveType: MoveType) : MoveOptions {

    enum class MoveType {
        TOGGLE_FLAG,
        CLICK
    }

}