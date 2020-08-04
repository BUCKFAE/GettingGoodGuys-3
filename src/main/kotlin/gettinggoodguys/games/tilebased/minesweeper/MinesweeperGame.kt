package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.Game
import gettinggoodguys.games.tilebased.Tile
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import kotlin.random.Random

/**
 * @param random The Random generator used to setup the board
 * @param bombChance The chance a given tile is a bomb from 0 to 1
 * @param bombAmount Set this to something other than -1 if you want exactly this amount of bombs
 */
class MinesweeperGame(
    gameBoardSizeX: Int,
    gameBoardSizeY: Int,
    val random: Random = Random,
    val bombChance: Double = 0.10,
    val bombAmount: Int = -1
) :
    TileBasedGame(gameBoardSizeX, gameBoardSizeY, MinesweeperTileInfo()) {

    private val setUpBombFunction = { tile: Tile ->
        if ((tile.tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.EMPTY && random.nextDouble() <= bombChance) {
            tile.tileType =
                MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.BOMB)
            1
        } else {
            0
        }
    }

    private val countNearBombsFunction = { tile: Tile ->
        val isBomb = (tile.tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.BOMB

        var bombs = 0
        if (!isBomb) {
            for (yMod in -1..1) {
                for (xMod in -1..1) {
                    if (xMod != 0 || yMod != 0) {
                        if (isTileAt(tile.posX + xMod, tile.posY + yMod)) {
                            if ((getTileAt(
                                    tile.posX + xMod,
                                    tile.posY + yMod
                                ).tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.BOMB
                            ) {
                                bombs++
                            }
                        }
                    }
                }
            }
            when (bombs) {
                0 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.ZERO)
                1 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.ONE)
                2 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.TWO)
                3 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.THREE)
                4 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.FOUR)
                5 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.FIVE)
                6 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.SIX)
                7 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.SEVEN)
                8 -> tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.EIGHT)
            }
        } else {
            tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.BOMB)
        }
        Unit
    }

    /**
     * Will be called to setup game
     */
    private fun setupBoard() {

        /**
         * Setup all bombs
         */
        var bombs = 0
        do {
            bombs = applyToAllTiles(setUpBombFunction, { count, bomb -> count + bomb }, bombs)
        } while (bombAmount != -1 && bombAmount < bombs)

        /**
         * Setup all other tiles
         */
        applyToAllTiles(countNearBombsFunction)
    }

    private fun <T> applyToAllTiles(
        function: (tile: Tile) -> T,
        transform: (T, T) -> T? = { _, _ -> null },
        initial: T
    ): T {
        var value = initial
        for (y in 0 until gameBoardSizeY) {
            for (x in 0 until gameBoardSizeX) {
                value = transform(value, function(getTileAt(x, y))) ?: value
            }
        }
        return value
    }

    private fun applyToAllTiles(function: (tile: Tile) -> Unit) {
        for (y in 0 until gameBoardSizeY) {
            for (x in 0 until gameBoardSizeX) {
                function(getTileAt(x, y))
            }
        }
    }

    /**
     * On first step the player cannot click on a bomb
     */
    private var firstStep = true

    /**
     * Returns true if the player would die
     * @return If the player would die, false otherwise
     */
    private fun bombCheck(move: MinesweeperMoveOptions): Boolean {
        if (move.moveType != MinesweeperMoveOptions.MoveType.CLICK) {
            return false
        }
        if (firstStep) {

        } else {

        }
        return false
    }

    override fun step() {
        TODO("Not yet implemented")
    }

    override fun isAlive(): Boolean {
        TODO("Not yet implemented")
    }
}