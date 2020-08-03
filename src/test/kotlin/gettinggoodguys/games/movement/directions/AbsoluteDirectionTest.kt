package gettinggoodguys.games.movement.directions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AbsoluteDirectionTest {

    @Test
    fun toIntTest() {
    }

    @Test
    fun testToStringTest() {
        assertEquals("UP", AbsoluteDirection.UP.toString());
        assertEquals("LEFT", AbsoluteDirection.LEFT.toString());
        assertEquals("DOWN", AbsoluteDirection.DOWN.toString());
        assertEquals("RIGHT", AbsoluteDirection.RIGHT.toString());
    }

    @Test
    fun intToAbsoluteDirectionTest() {
        assertEquals(AbsoluteDirection.UP, AbsoluteDirection.intToAbsoluteDirection(0));
        assertEquals(AbsoluteDirection.RIGHT, AbsoluteDirection.intToAbsoluteDirection(1));
        assertEquals(AbsoluteDirection.DOWN, AbsoluteDirection.intToAbsoluteDirection(2));
        assertEquals(AbsoluteDirection.LEFT, AbsoluteDirection.intToAbsoluteDirection(3));
    }
}