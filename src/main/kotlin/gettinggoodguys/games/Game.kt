package gettinggoodguys.games

interface Game {

    /**
     * Performs one step in the given Game
     */
    fun step(moveOption: MoveOptions)

    fun isAlive(): Boolean
}