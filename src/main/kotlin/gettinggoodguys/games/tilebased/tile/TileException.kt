package gettinggoodguys.games.tilebased.tile

import gettinggoodguys.games.tilebased.TileBasedGame

/**
 * Gets raised when trying to access a tile on invalid coordinates
 */
class NoTileAtCoordinatesException (posX: Int, posY: Int, gameBoard: TileBasedGame):
    Exception(
        "An error occurred while trying to access a tile\n" +
                "Reason: There is no tile at the given coordinates\n" +
                "Coordinates: x = $posX y = $posY\n" +
                getGameBoardData(gameBoard)
    )


class IllegalTileTypeOverrideException(tile: Tile, newTileType: TileType):
    Exception(
        "An error has occurred while trying to change the tileType of a tile\n" +
                "Reason: The current tileType could not be overridden by the new tileType\n" +
                "New tileType = $newTileType\n" +
                "$tile"
    )

private fun getGameBoardData(gameBoard: TileBasedGame): String {
    return "GameBoard bounds: x = ${gameBoard.gameBoardSizeX} y = ${gameBoard.gameBoardSizeY} [Exclusive]\n" +
            "Gameboard toPrettyString:\n${gameBoard.toPrettyString()}"
}
