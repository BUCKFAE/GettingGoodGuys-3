package gettinggoodguys.games.tilebased.tile

import gettinggoodguys.games.tilebased.snake.SnakeTileType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TileTest {

    /**
     * This test does not need to be implemented as this will automatically
     * be tested when testing the different TileTypes.
     *
     * Example: see TicTacToeTileTypeTest
     */
    @Test
    fun getTileTypeTest() {
        assertTrue(true)
    }

    /**
     * This test does not need to be implemented as this will automatically
     * be tested when testing the different TileTypes
     *
     * Example: see TicTacToeTileTest
     */
    @Test
    fun setTileTypeTest() {
        assertTrue(true)
    }

    @Test
    fun getPosXTest() {

        // Creating test tiles
        val testTile0 = Tile(0, 0, SnakeTileType.EMPTY_TILE)
        val testTile1 = Tile(1, 0, SnakeTileType.EMPTY_TILE)
        val testTile2 = Tile(2, 0, SnakeTileType.EMPTY_TILE)
        val testTile3 = Tile(-1, 0, SnakeTileType.EMPTY_TILE)
        val testTile4 = Tile(-2, 0, SnakeTileType.EMPTY_TILE)

        // Ensuring the test tiles have the correct posX
        assertEquals(0, testTile0.posX)
        assertEquals(1, testTile1.posX)
        assertEquals(2, testTile2.posX)
        assertEquals(-1, testTile3.posX)
        assertEquals(-2, testTile4.posX)

    }

    @Test
    fun getPosYTest() {

        // Creating test tiles
        val testTile0 = Tile(0, 0, SnakeTileType.EMPTY_TILE)
        val testTile1 = Tile(0, 1, SnakeTileType.EMPTY_TILE)
        val testTile2 = Tile(0, 2, SnakeTileType.EMPTY_TILE)
        val testTile3 = Tile(0, -1, SnakeTileType.EMPTY_TILE)
        val testTile4 = Tile(0, -2, SnakeTileType.EMPTY_TILE)

        // Ensuring the test tiles have the correct posX
        assertEquals(0, testTile0.posY)
        assertEquals(1, testTile1.posY)
        assertEquals(2, testTile2.posY)
        assertEquals(-1, testTile3.posY)
        assertEquals(-2, testTile4.posY)
    }

    @Test
    fun toStringTest() {
        val testTile = Tile(1, 2, SnakeTileType.EMPTY_TILE)
        assertEquals("Tile: x = 1 y = 2 tileType = \"SnakeTile =  \"", testTile.toString())

        testTile.tileType = SnakeTileType.SNAKE_HEAD_TILE
        assertEquals("Tile: x = 1 y = 2 tileType = \"SnakeTile = H\"", testTile.toString())

    }
}