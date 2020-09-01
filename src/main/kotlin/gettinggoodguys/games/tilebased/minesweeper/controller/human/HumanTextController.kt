package gettinggoodguys.games.tilebased.minesweeper.controller.human

import gettinggoodguys.games.MoveOptions
import gettinggoodguys.games.tilebased.minesweeper.MinesweeperGame
import gettinggoodguys.games.tilebased.minesweeper.MinesweeperMoveOptions

class HumanTextController(width: Int, height: Int) : HumanController(MinesweeperGame(width, height)) {

    private var move = MinesweeperMoveOptions.NO_MOVE

    fun parseText(text: String) {
        val args = text.split("\\s+".toRegex(), 3)
        if (args.size != 3) {
            return
        }
        val x = args[0].toIntOrNull() ?: return
        val y = args[1].toIntOrNull() ?: return
        val operation = args[2].trim().toLowerCase()
        val move = when {
            operation == "f" || operation == "flag" || (operation.startsWith("toggle") && operation.endsWith("flag")) -> MinesweeperMoveOptions(
                x,
                y,
                MinesweeperMoveOptions.MoveType.TOGGLE_FLAG
            )
            operation == "c" || operation == "click" -> MinesweeperMoveOptions(
                x,
                y,
                MinesweeperMoveOptions.MoveType.TOGGLE_FLAG
            )
            else -> MinesweeperMoveOptions.NO_MOVE
        }
        this.move = move
    }

    override fun getNextMove(): MoveOptions {
        return move
    }

}