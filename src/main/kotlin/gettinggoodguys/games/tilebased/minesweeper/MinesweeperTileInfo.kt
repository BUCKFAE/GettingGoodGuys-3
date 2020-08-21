package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.tilebased.TileType

data class MinesweeperTileInfo(
    var clicked: Boolean = false,
    var flagged: Boolean = false,
    val type: MinesweeperTileType = MinesweeperTileType.EMPTY
) : TileType {
    /**
     * Every type can be override by any other minesweeper type
     * Empty can always be overridden
     * Every number can be overridden by any higher (or one lower number)
     * A bomb tile can be overridden if it is the first move
     */
    override fun canBeOverriddenBy(tileType: TileType): Boolean {
        return tileType is MinesweeperTileInfo
    }

    override fun toString(): String {
        return when {
            !flagged && !clicked -> "?"
            flagged && !clicked -> "F"
            clicked -> when (type) {
                MinesweeperTileType.EMPTY -> "?"
                MinesweeperTileType.ZERO -> " "
                MinesweeperTileType.ONE -> "1"
                MinesweeperTileType.TWO -> "2"
                MinesweeperTileType.THREE -> "3"
                MinesweeperTileType.FOUR -> "4"
                MinesweeperTileType.FIVE -> "5"
                MinesweeperTileType.SIX -> "6"
                MinesweeperTileType.SEVEN -> "7"
                MinesweeperTileType.EIGHT -> "8"
                MinesweeperTileType.BOMB -> "X"
            }
            else -> ""
        }
    }


    enum class MinesweeperTileType {
        EMPTY,
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        BOMB
    }
}
