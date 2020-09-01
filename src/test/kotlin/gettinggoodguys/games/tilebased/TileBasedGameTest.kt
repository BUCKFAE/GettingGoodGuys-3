package gettinggoodguys.games.tilebased

import gettinggoodguys.games.movement.directions.AbsoluteDirection
import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import gettinggoodguys.games.tilebased.tile.NoTileAtCoordinatesException
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith


import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException
import kotlin.random.Random
import kotlin.test.assertNull

internal class TileBasedGameTest {

    @Test
    fun getTileAtTest() {
        val snakeGame = SnakeGame(4, 5)

        /*
         * Checking if all Tiles have the correct coordinates
         */

        // Tile at 0 0
        assertEquals(0, snakeGame.getTileAt(0,0).posX)
        assertEquals(0, snakeGame.getTileAt(0,0).posY)

        // Tile at 1 0
        assertEquals(1, snakeGame.getTileAt(1,0).posX)
        assertEquals(0, snakeGame.getTileAt(1,0).posY)

        // Tile at 0 1
        assertEquals(0, snakeGame.getTileAt(0,1).posX)
        assertEquals(1, snakeGame.getTileAt(0,1).posY)

        // Tile at 3 0
        assertEquals(3, snakeGame.getTileAt(3,0).posX)
        assertEquals(0, snakeGame.getTileAt(3,0).posY)

        // Tile at 0 4
        assertEquals(0, snakeGame.getTileAt(0,4).posX)
        assertEquals(4, snakeGame.getTileAt(0,4).posY)

        // Tile at 3 4
        assertEquals(3, snakeGame.getTileAt(3,4).posX)
        assertEquals(4, snakeGame.getTileAt(3,4).posY)

        /*
         * Checking modification of a tile
         */

        // Ensuring the testTile has the correct TileType
        assertEquals(SnakeTileType.EMPTY_TILE, snakeGame.getTileAt(0, 0).tileType)

        // Changing the TileType of the testTile
        snakeGame.getTileAt(0, 0).tileType = SnakeTileType.SNAKE_HEAD_TILE

        // Ensuring that this changed the testTile
        assertEquals(SnakeTileType.SNAKE_HEAD_TILE, snakeGame.getTileAt(0, 0).tileType)

        /*
         * Testing if exceptions are raised for invalid coordinates
         */

        assertFailsWith<NoTileAtCoordinatesException> { snakeGame.getTileAt(-1, 0) }
        assertFailsWith<NoTileAtCoordinatesException> { snakeGame.getTileAt(0, -1) }
        assertFailsWith<NoTileAtCoordinatesException> { snakeGame.getTileAt(-1, -1) }
        assertFailsWith<NoTileAtCoordinatesException> { snakeGame.getTileAt(4, 0) }
        assertFailsWith<NoTileAtCoordinatesException> { snakeGame.getTileAt(0, 5) }
        assertFailsWith<NoTileAtCoordinatesException> { snakeGame.getTileAt(4, 5) }

    }

    @Test
    fun isTileAtTest() {

        val snakeGame = SnakeGame(4, 5)

        assertTrue(snakeGame.isTileAt(0, 0))
        assertTrue(snakeGame.isTileAt(1, 0))
        assertTrue(snakeGame.isTileAt(0, 1))
        assertTrue(snakeGame.isTileAt(1, 1))
        assertTrue(snakeGame.isTileAt(3, 0))
        assertTrue(snakeGame.isTileAt(0, 4))
        assertTrue(snakeGame.isTileAt(3, 4))

        assertFalse(snakeGame.isTileAt(-1, 0))
        assertFalse(snakeGame.isTileAt(0, -1))
        assertFalse(snakeGame.isTileAt(-1, -1))
        assertFalse(snakeGame.isTileAt(4, 0))
        assertFalse(snakeGame.isTileAt(0, 5))
        assertFalse(snakeGame.isTileAt(4, 5))

    }

    @Test
    fun getTileInAbsoluteDirTest() {

        val snakeGame = SnakeGame(5, 4)

        /*
         * Testing all directions from the field 1 1
         */

        // UP
        assertEquals(1, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.UP)?.posX)
        assertEquals(0, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.UP)?.posY)

        // Left
        assertEquals(0, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.LEFT)?.posX)
        assertEquals(1, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.LEFT)?.posY)

        // Down
        assertEquals(1, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.DOWN)?.posX)
        assertEquals(2, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.DOWN)?.posY)

        // Right
        assertEquals(2, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.RIGHT)?.posX)
        assertEquals(1, snakeGame.getTileInAbsoluteDir(1, 1, AbsoluteDirection.RIGHT)?.posY)

        /*
         * Testing all directions from the field 0 0
         */

        // UP
        assertNull(snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.UP))
        assertNull(snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.UP))

        // Left
        assertNull(snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.LEFT))
        assertNull(snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.LEFT))

        // Down
        assertEquals(0, snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.DOWN)?.posX)
        assertEquals(1, snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.DOWN)?.posY)

        // Right
        assertEquals(1, snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.RIGHT)?.posX)
        assertEquals(0, snakeGame.getTileInAbsoluteDir(0, 0, AbsoluteDirection.RIGHT)?.posY)

        /*
         * Testing all directions from the field 4 0
         */

        // UP
        assertNull(snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.UP))
        assertNull(snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.UP))

        // Left
        assertEquals(3, snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.LEFT)?.posX)
        assertEquals(0, snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.LEFT)?.posY)

        // Down
        assertEquals(4, snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.DOWN)?.posX)
        assertEquals(1, snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.DOWN)?.posY)

        // Right
        assertNull(snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.RIGHT))
        assertNull(snakeGame.getTileInAbsoluteDir(4, 0, AbsoluteDirection.RIGHT))

        /*
         * Testing all directions from the field 0 3
         */

        // UP
        assertEquals(0, snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.UP)?.posX)
        assertEquals(2, snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.UP)?.posY)

        // Left
        assertNull(snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.LEFT))
        assertNull(snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.LEFT))

        // Down
        assertNull(snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.DOWN))
        assertNull(snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.DOWN))

        // Right
        assertEquals(1, snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.RIGHT)?.posX)
        assertEquals(3, snakeGame.getTileInAbsoluteDir(0, 3, AbsoluteDirection.RIGHT)?.posY)

        /*
         * Testing all directions from the field 4 3
         */

        // UP
        assertEquals(4, snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.UP)?.posX)
        assertEquals(2, snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.UP)?.posY)

        // Left
        assertEquals(3, snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.LEFT)?.posX)
        assertEquals(3, snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.LEFT)?.posY)

        // Down
        assertNull(snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.DOWN))
        assertNull(snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.DOWN))

        // Right
        assertNull(snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.RIGHT))
        assertNull(snakeGame.getTileInAbsoluteDir(4, 3, AbsoluteDirection.RIGHT))
    }

    @Test
    fun toPrettyStringTest() {
    }

    @Test
    fun getRandomTileTest() {

        val snakeGame = SnakeGame(5, 4)
        val random = Random(0)

        // Ensuring that getting a set random will always get the same tiles
        assertEquals("Tile: x = 4 y = 1 tileType = \"SnakeTile =  \"",
            snakeGame.getRandomTile(random = random).toString())
        assertEquals("Tile: x = 2 y = 2 tileType = \"SnakeTile = H\"",
            snakeGame.getRandomTile(random = random).toString())
    }

    @Test
    fun getTilesOfRowTest() {
    }

    @Test
    fun getTilesOfColTest() {
    }

    @Test
    fun getAllTilesInOrderTest() {
    }
}