package gettinggoodguys.games.tilebased.tile

import java.lang.IllegalArgumentException

/**
 * Represents a single Tile on a TileBasedGame
 *
 * @param posX The x position of the tile on the game board
 * @param posY The y position of the tile on the game board
 * @param tileType the TileType of the tile
 */
class Tile(val posX: Int, val posY: Int, tileType: TileType) {

    // TODO: Test this
    var tileType: TileType = tileType
        set(value) {
        if(tileType.canBeOverriddenBy(value)) field = value
        else throw IllegalTileTypeOverrideException(this, value)
    }

    //TODO: Write Tests for this
    override fun toString(): String {
        return "Tile: x = $posX y = $posY tileType = \"${tileType.toString()}\""
    }
}

