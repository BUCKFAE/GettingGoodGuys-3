package gettinggoodguys.games.movement.directions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class RelativeDirectionTest {

    @Test
    fun toIntTest() {
        assertEquals(0, RelativeDirection.AHEAD.toInt());
        assertEquals(1, RelativeDirection.RIGHT.toInt());
        assertEquals(2, RelativeDirection.LEFT.toInt());
    }

    @Test
    fun toAbsoluteDirectionTest() {
        val ahead = RelativeDirection.AHEAD
        val left = RelativeDirection.LEFT
        val right = RelativeDirection.RIGHT

        /*
         * Testing conversions from relative to absolute
         */

        // Testing conversion from ahead
        assertEquals(AbsoluteDirection.UP, ahead.toAbsoluteDirection(AbsoluteDirection.UP))
        assertEquals(AbsoluteDirection.RIGHT, ahead.toAbsoluteDirection(AbsoluteDirection.RIGHT))
        assertEquals(AbsoluteDirection.DOWN, ahead.toAbsoluteDirection(AbsoluteDirection.DOWN))
        assertEquals(AbsoluteDirection.LEFT, ahead.toAbsoluteDirection(AbsoluteDirection.LEFT))

        // Testing conversion from left
        assertEquals(AbsoluteDirection.LEFT, left.toAbsoluteDirection(AbsoluteDirection.UP))
        assertEquals(AbsoluteDirection.UP, left.toAbsoluteDirection(AbsoluteDirection.RIGHT))
        assertEquals(AbsoluteDirection.RIGHT, left.toAbsoluteDirection(AbsoluteDirection.DOWN))
        assertEquals(AbsoluteDirection.DOWN, left.toAbsoluteDirection(AbsoluteDirection.LEFT))

        // Testing conversion from right
        assertEquals(AbsoluteDirection.RIGHT, right.toAbsoluteDirection(AbsoluteDirection.UP))
        assertEquals(AbsoluteDirection.DOWN, right.toAbsoluteDirection(AbsoluteDirection.RIGHT))
        assertEquals(AbsoluteDirection.LEFT, right.toAbsoluteDirection(AbsoluteDirection.DOWN))
        assertEquals(AbsoluteDirection.UP, right.toAbsoluteDirection(AbsoluteDirection.LEFT))
    }

    @Test
    fun testToStringTest() {
        assertEquals("AHEAD", RelativeDirection.AHEAD.toString());
        assertEquals("LEFT", RelativeDirection.LEFT.toString());
        assertEquals("RIGHT", RelativeDirection.RIGHT.toString());
    }
}