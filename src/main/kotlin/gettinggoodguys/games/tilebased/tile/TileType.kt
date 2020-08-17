package gettinggoodguys.games.tilebased.tile

interface TileType {

    override fun toString(): String

    /**
     * Checks if the given tileType can be overridden by another tile tpye
     *
     * Example:
     * A TicTacToeTileType can never be overridden by a SnakeTileType
     * An empty TicTacToeTile can be overridden by PLAYER_1_TILE
     * A PLAYER_1_Tile (TicTacToe) can not be overridden by PLAYER_2_TILE
     */
    fun canBeOverriddenBy(tileType: TileType): Boolean

    fun extendedToString(): String
}