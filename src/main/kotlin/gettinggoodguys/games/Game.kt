package gettinggoodguys.games

interface Game {

    // TODO: This is not needed anymore once we have a controller for each game / ai
    enum class GameTypes {
        Snake,
        TicTacToe
    }

    /**
     * Performs one step in the given Game
     */
    fun step()

    fun isAlive(): Boolean
}