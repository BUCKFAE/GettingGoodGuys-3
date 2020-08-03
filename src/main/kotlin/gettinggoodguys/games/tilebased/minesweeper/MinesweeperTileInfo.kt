package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.tilebased.TileType

data class MinesweeperTileInfo(
    var clicked: Boolean = false,
    var flagged: Boolean = false,
    val type: MinesweeperTileType
) : TileType {
    override fun canBeOverriddenBy(tileType: TileType): Boolean {
        if (type == MinesweeperTileType.EMPTY) {
            return true
        }
        return false
    }

    override fun toString(): String {
        return when {
            !flagged && !clicked -> "?"
            flagged && !clicked -> "F"
            !flagged && clicked -> when (type) {
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
