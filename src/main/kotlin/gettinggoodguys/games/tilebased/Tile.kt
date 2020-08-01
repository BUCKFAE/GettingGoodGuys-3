package gettinggoodguys.games.tilebased

import java.lang.IllegalArgumentException

/**
 * Represents a single Tile on a TileBasedGame
 *
 * @param posX The x position of the tile on the game board
 * @param posY The y position of the tile on the game board
 * @param tileType the TileType of the tile
 */
class Tile(private val posX: Int, private val posY: Int, tileType: TileType) {

    private var tileType: TileType

    init {
        this.tileType = tileType
    }

    /**
     * Getting the TileType
     * @return the TileType of the tile
     */
    fun getTileType(): TileType {
        return tileType
    }

    /**
     * Setting the TileType for the tile
     * @param newTileType the new TileType for the tile
     * @throws IllegalArgumentException if the current TileType can't be overridden by the new TileType
     */
    fun setTileType(newTileType: TileType) {
        if(tileType.canBeOverriddenBy(newTileType)) tileType = newTileType
        else throw IllegalArgumentException("Could not override tileType of tile at x: $posX y: $posY\n" +
                "Old tileType: $tileType\n" +
                "New tileType: $newTileType")
    }
}