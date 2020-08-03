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

    var tileType: TileType = tileType
        set(value) {
        if(tileType.canBeOverriddenBy(value)) field = value
        else throw IllegalArgumentException("Could not override tileType of tile at x $posX y $posY\n" +
                "Old tileType $field\n" +
                "New tileType $value")
    }

}