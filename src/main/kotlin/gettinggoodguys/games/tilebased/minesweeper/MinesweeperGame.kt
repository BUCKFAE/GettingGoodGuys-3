package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.tilebased.Tile
import gettinggoodguys.games.tilebased.TileBasedGame
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

    /**
     * Will be called to setup game
     */
    private fun setupBoard() {

        /**
         * Setup all bombs
         */
        var bombs = 0
        do {
            for (y in 0 until gameBoardSizeY) {
                for (x in 0 until gameBoardSizeX) {
                    val tile = getTileAt(x, y)
                    if ((tile.tileType as MinesweeperTileInfo).type != MinesweeperTileInfo.MinesweeperTileType.BOMB && random.nextDouble() <= bombChance) {
                        tile.tileType =
                            MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.BOMB)
                        bombs++
                        for (yMod in -1..1) {
                            for (xMod in -1..1) {
                                if (xMod != 0 || yMod != 0) {
                                    if (isTileAt(tile.posX + xMod, tile.posY + yMod)) {
                                        incrementBombCount(getTileAt(x + yMod, y + yMod))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while (bombAmount != -1 && bombAmount < bombs)
    }

    private fun incrementBombCount(tile: Tile): MinesweeperTileInfo {
        val newValue = when ((tile.tileType as MinesweeperTileInfo).type) {
            MinesweeperTileInfo.MinesweeperTileType.EMPTY, MinesweeperTileInfo.MinesweeperTileType.ZERO -> MinesweeperTileInfo.MinesweeperTileType.ONE
            MinesweeperTileInfo.MinesweeperTileType.ONE -> MinesweeperTileInfo.MinesweeperTileType.TWO
            MinesweeperTileInfo.MinesweeperTileType.TWO -> MinesweeperTileInfo.MinesweeperTileType.THREE
            MinesweeperTileInfo.MinesweeperTileType.THREE -> MinesweeperTileInfo.MinesweeperTileType.FOUR
            MinesweeperTileInfo.MinesweeperTileType.FOUR -> MinesweeperTileInfo.MinesweeperTileType.FIVE
            MinesweeperTileInfo.MinesweeperTileType.FIVE -> MinesweeperTileInfo.MinesweeperTileType.SIX
            MinesweeperTileInfo.MinesweeperTileType.SIX -> MinesweeperTileInfo.MinesweeperTileType.SEVEN
            MinesweeperTileInfo.MinesweeperTileType.SEVEN -> MinesweeperTileInfo.MinesweeperTileType.EIGHT
            MinesweeperTileInfo.MinesweeperTileType.EIGHT -> throw IllegalArgumentException("Cannot increment the amount of bombs, on a max bomb tile")
            MinesweeperTileInfo.MinesweeperTileType.BOMB -> MinesweeperTileInfo.MinesweeperTileType.BOMB
        }
        tile.tileType = MinesweeperTileInfo(type = newValue)
        return tile.tileType as MinesweeperTileInfo
    }

    private fun decrementBombCount(tile: Tile): MinesweeperTileInfo {
        val oldType = (tile.tileType as MinesweeperTileInfo)
        val newValue = when (oldType.type) {
            MinesweeperTileInfo.MinesweeperTileType.EMPTY, MinesweeperTileInfo.MinesweeperTileType.ZERO -> throw java.lang.IllegalArgumentException(
                "Cannot decrement the amount of bombs, on a min bomb tile"
            )
            MinesweeperTileInfo.MinesweeperTileType.ONE -> MinesweeperTileInfo.MinesweeperTileType.ZERO
            MinesweeperTileInfo.MinesweeperTileType.TWO -> MinesweeperTileInfo.MinesweeperTileType.ONE
            MinesweeperTileInfo.MinesweeperTileType.THREE -> MinesweeperTileInfo.MinesweeperTileType.TWO
            MinesweeperTileInfo.MinesweeperTileType.FOUR -> MinesweeperTileInfo.MinesweeperTileType.THREE
            MinesweeperTileInfo.MinesweeperTileType.FIVE -> MinesweeperTileInfo.MinesweeperTileType.FOUR
            MinesweeperTileInfo.MinesweeperTileType.SIX -> MinesweeperTileInfo.MinesweeperTileType.FIVE
            MinesweeperTileInfo.MinesweeperTileType.SEVEN -> MinesweeperTileInfo.MinesweeperTileType.SIX
            MinesweeperTileInfo.MinesweeperTileType.EIGHT -> MinesweeperTileInfo.MinesweeperTileType.SEVEN
            MinesweeperTileInfo.MinesweeperTileType.BOMB -> MinesweeperTileInfo.MinesweeperTileType.BOMB
        }
        tile.tileType = MinesweeperTileInfo(oldType.clicked, oldType.flagged, newValue)
        return tile.tileType as MinesweeperTileInfo
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
        val tile = getTileAt(move.x, move.y)
        if (firstStep) {
            if ((tile.tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
                tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.ZERO)
                for (yMod in -1..1) {
                    for (xMod in -1..1) {
                        if (isTileAt(move.x - xMod, move.y - yMod)) {
                            val newType = decrementBombCount(getTileAt(move.x - xMod, move.y - yMod))
                            if (newType.type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
                                incrementBombCount(tile)
                            }
                        }
                    }
                }
                (tile.tileType as MinesweeperTileInfo).clicked = true
            }
            firstStep = false
        } else {
            if ((tile.tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
                return true
            }
            (tile.tileType as MinesweeperTileInfo).clicked = true
        }
        return false
    }

    var currentMove: MinesweeperMoveOptions = MinesweeperMoveOptions.NO_MOVE
        get() {
            val returnValue = field
            field = MinesweeperMoveOptions.NO_MOVE
            return returnValue
        }
        set(value) {
            if (!isTileAt(value.x, value.y)) {
                //invalid tile -> next move is to do nothing
                return
            }
            if (value.moveType == MinesweeperMoveOptions.MoveType.TOGGLE_FLAG) {
                if ((getTileAt(value.x, value.y).tileType as MinesweeperTileInfo).clicked) {
                    //when we want to toggle a flag, and the place is already clicked do nothing
                    return
                }
            }
            field = value
        }

    /**
     * If the tile has no surrounding bombs, all surrounding bomb less tiles will be shown
     */
    private fun discoverTiles(center: Tile) {
        val tileType = center.tileType as MinesweeperTileInfo
        if (!tileType.clicked) {
            return
        }
        if (tileType.type != MinesweeperTileInfo.MinesweeperTileType.ZERO && tileType.type != MinesweeperTileInfo.MinesweeperTileType.EMPTY) {
            return
        }
        val toCheck = mutableListOf(center)
        while (toCheck.isNotEmpty()) {
            val current = toCheck.removeAt(0)
            toCheck.addAll(discoverTileStep(current))
        }
    }

    private fun discoverTileStep(tile: Tile): List<Tile> {
        val tileType = (tile.tileType as MinesweeperTileInfo)
        if (tileType.type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
            tileType.flagged = true
        } else {
            tileType.clicked = true
        }
        if (tileType.type != MinesweeperTileInfo.MinesweeperTileType.ZERO && tileType.type != MinesweeperTileInfo.MinesweeperTileType.EMPTY) {
            return listOf()
        }

        val toCheck = mutableListOf<Tile>()
        for (yMod in -1..1) {
            for (xMod in -1..1) {
                if (isTileAt(tile.posX + xMod, tile.posY + yMod)) {
                    val newTile = getTileAt(tile.posX + xMod, tile.posY + yMod)
                    val newTileType = newTile.tileType as MinesweeperTileInfo
                    if (!newTileType.clicked && (!newTileType.flagged && newTileType.type == MinesweeperTileInfo.MinesweeperTileType.BOMB)) {
                        toCheck += newTile
                    }
                }
            }
        }
        return toCheck
    }

    override fun step() {
        val move = currentMove
        when (move.moveType) {
            MinesweeperMoveOptions.MoveType.NOTHING -> Unit
            MinesweeperMoveOptions.MoveType.CLICK -> {
                alive = !bombCheck(move)
                //if the tile is a zero/empty tile check all nearby tiles
                discoverTiles(getTileAt(move.x, move.y))
            }

            MinesweeperMoveOptions.MoveType.TOGGLE_FLAG -> {
                val tileType = (getTileAt(move.x, move.y).tileType as MinesweeperTileInfo)
                tileType.flagged = !tileType.flagged
            }
        }
    }

    private var alive = true


    override fun isAlive(): Boolean {
        return alive
    }
}