package gettinggoodguys.games.tilebased.minesweeper

import gettinggoodguys.games.MoveOptions
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.games.tilebased.tile.Tile
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
     * Will be called by [step] to setup game on the first move
     */
    private fun setupBoard() {

        /**
         * Setup all bombs:
         * - Every tile has a [bombChance] chance to be a bomb
         * - Will set bombs until there are at least [bombAmount] bombs
         * - Will run at least once
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
        } while (bombAmount != -1 && bombs < bombAmount)
    }

    /**
     * Increments the bomb count of a tile by one
     * Sets the new bomb count to the tile and returns the new bomb count
     * @param tile The tile to increment the bomb count of
     * @return The new bomb count
     */
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

    /**
     * Decrements the bomb count of a tile by one
     * Sets the new bomb count to the tile and returns the new bomb count
     * @param tile The tile to decrement the bomb count of
     * @return The new bomb count
     */
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
     *
     * If it is the first step in the game and the player would click a bomb:
     * - Remove the bomb (by setting it to a [MinesweeperTileInfo.MinesweeperTileType.ZERO] type)
     * - Decrement the bomb count of all surrounding tiles
     * - Increment the bomb count of the clicked tile
     *
     * @param move The move performed by the player
     * @return True if the player would die, false otherwise
     */
    private fun bombCheck(move: MinesweeperMoveOptions): Boolean {
        if (move.moveType != MinesweeperMoveOptions.MoveType.CLICK) {
            return false
        }
        //the clicked tile
        val tile = getTileAt(move.x, move.y)
        //check if it is the first step of the game
        if (firstStep) {
            //if it is a bomb
            if ((tile.tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
                //remove the bomb and set the bomb count to zero
                tile.tileType = MinesweeperTileInfo(type = MinesweeperTileInfo.MinesweeperTileType.ZERO)
                //for every surrounding tile
                for (yMod in -1..1) {
                    for (xMod in -1..1) {
                        //if the current tile would be selected skip
                        if (xMod == 0 && yMod == 0) {
                            continue
                        }
                        //if there is a tile
                        if (isTileAt(move.x + xMod, move.y + yMod)) {
                            //decrement the bomb count of the other tile
                            val newType = decrementBombCount(getTileAt(move.x + xMod, move.y + yMod))
                            //if it is a bomb
                            if (newType.type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
                                //increment the own bomb count
                                incrementBombCount(tile)
                            }
                        }
                    }
                }
            }
            //mark the current tile as clicked
            (tile.tileType as MinesweeperTileInfo).clicked = true
            //set first step to false
            firstStep = false
        } else {
            //if it is not the first step and the player clicked on a bomb
            if ((tile.tileType as MinesweeperTileInfo).type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
                //return true i.e. the player dies
                return true
            }
            //mark the tile as clicked
            (tile.tileType as MinesweeperTileInfo).clicked = true
        }
        //return false as the player does not die
        return false
    }

    /**
     * If the tile has no surrounding bombs, all surrounding bombless tiles will be shown
     *
     * Uses [discoverTileStep] for the discovery
     * @param center The tile where the check is started
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

    /**
     * Will discover this tile if it does not have a bomb
     *
     * if this tile is  a bomb or has one or more surrounding bombs:
     *
     * return an empty list
     *
     * else:
     *
     * all surrounding tiles that are not already clicked
     *
     * or in case of a bomb flagged
     * @param tile The current tile to check
     * @return A list of tiles to check next
     */
    private fun discoverTileStep(tile: Tile): List<Tile> {
        val tileType = (tile.tileType as MinesweeperTileInfo) //current tileType
        //in case of a bomb flag else click the tile
        if (tileType.type == MinesweeperTileInfo.MinesweeperTileType.BOMB) {
            tileType.flagged = true
        } else {
            tileType.clicked = true
        }
        //if the tile type is not empty and is a bomb or is surrounded by one or more bombs
        //return an empty list
        if (tileType.type != MinesweeperTileInfo.MinesweeperTileType.ZERO && tileType.type != MinesweeperTileInfo.MinesweeperTileType.EMPTY) {
            return listOf()
        }

        //return list of tiles to check next
        val toCheck = mutableListOf<Tile>()
        //for every surrounding tile
        for (yMod in -1..1) {
            for (xMod in -1..1) {
                //if there is a tile at that location
                if (isTileAt(tile.posX + xMod, tile.posY + yMod)) {
                    //get the new tile
                    val newTile = getTileAt(tile.posX + xMod, tile.posY + yMod)
                    //get the tile type
                    val newTileType = newTile.tileType as MinesweeperTileInfo
                    //check if it has already been clicked or in case of a bomb flagged
                    if (!newTileType.clicked && (!newTileType.flagged && newTileType.type == MinesweeperTileInfo.MinesweeperTileType.BOMB)) {
                        //if not add it to the list
                        toCheck += newTile
                    }
                }
            }
        }
        //return the list
        return toCheck
    }

    //private boolean flag if the game is still running
    private var alive = true

    //private boolean flag if the setup of the board needs to be done
    private var setup = false

    /**
     * Performs the current move
     *
     * @param moveOption The move to perform in this step has to be of the [MinesweeperMoveOptions] type
     *
     * When the moveOption is not of the [MinesweeperMoveOptions] type this method instantly returns
     */
    override fun step(moveOption: MoveOptions) {
        if (moveOption !is MinesweeperMoveOptions) {
            return
        }
        if (setup) {
            setupBoard()
            setup = false
        }
        when (moveOption.moveType) {
            MinesweeperMoveOptions.MoveType.NOTHING -> Unit //in case of a nothing move do nothing
            MinesweeperMoveOptions.MoveType.CLICK -> { //when the player clicks on something
                alive = !bombCheck(moveOption) //do the bombCheck and set alive accordingly
                //if the tile is a zero/empty tile check all nearby tiles
                discoverTiles(getTileAt(moveOption.x, moveOption.y))
            }

            MinesweeperMoveOptions.MoveType.TOGGLE_FLAG -> { //when the player toggles the flag state of a tile
                val tileType = (getTileAt(moveOption.x, moveOption.y).tileType as MinesweeperTileInfo)
                tileType.flagged = !tileType.flagged
            }
        }
    }


    override fun isAlive(): Boolean {
        return alive
    }
}