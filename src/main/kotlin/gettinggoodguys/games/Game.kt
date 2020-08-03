package gettinggoodguys.games

interface Game {

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