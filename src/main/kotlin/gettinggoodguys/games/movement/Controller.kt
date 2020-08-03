package gettinggoodguys.games.movement

import gettinggoodguys.games.Game
import gettinggoodguys.games.MoveOptions
import gettinggoodguys.games.tilebased.snake.SnakeMoveOptions
import java.lang.IllegalStateException

class Controller(private val controllerType: Game.GameTypes) {

    private enum class EmptyMove: MoveOptions {
        NO_MOVE
    }

    var nextMove: MoveOptions = EmptyMove.NO_MOVE
        get() {
            // We don't have a next move -> we either do the default move or throw an exception
            if (field == EmptyMove.NO_MOVE) {
                // If there is a default move for the current game we set it as next move
                setDefaultMove()

                // If there is no default move we throw an exception
                if(field == EmptyMove.NO_MOVE) {
                    throw IllegalStateException("Unable to fetch next move for a game of type $controllerType")
                }
            }

            // Storing the original nextMove
            val toReturn = field

            // Resetting the field to an empty move
            field = EmptyMove.NO_MOVE

            return toReturn
        }
    set(value) {
        TODO("Not yet implemented")
    }

    /*
     * Some games have default moves if there is no input
     *
     * Example:
     * No input on TicTacToe -> We don't do anything
     * No input on Snake -> We move ahead
     */
    private fun setDefaultMove() {
        when(controllerType) {
            Game.GameTypes.TicTacToe -> {} // No default move
            Game.GameTypes.Snake -> {nextMove = SnakeMoveOptions.AHEAD} // Moving ahead
        }
    }




}