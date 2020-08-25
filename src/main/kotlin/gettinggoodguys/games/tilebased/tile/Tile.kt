package gettinggoodguys.games.tilebased.tile

/**
 * Represents a single Tile on a TileBasedGame
 *
 * @param posX The x position of the tile on the game board
 * @param posY The y position of the tile on the game board
 * @param tileType the TileType of the tile
 */
class Tile(val posX: Int, val posY: Int, tileType: TileType) {

    var tileType: TileType = tileType
        set(value) {
        if(tileType.canBeOverriddenBy(value)) field = value
        else throw IllegalTileTypeOverrideException(this, value)
    }

    override fun toString(): String {
        return "Tile: x = $posX y = $posY tileType = \"${tileType.extendedToString()}\""
    }
}

