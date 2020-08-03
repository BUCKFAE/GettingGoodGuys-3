package gettinggoodguys.games.movement.directions

enum class AbsoluteDirection(// The int int representation of the given enum
    private val intRepresentation: Int
) {
    UP(0), RIGHT(1), DOWN(2), LEFT(3);

    /**
     * Returns the int representation of the given enum
     * @return the int representation of the given enum
     */
    fun toInt(): Int {
        return intRepresentation
    }

    override fun toString(): String {

        // returning string
        when (intRepresentation) {
            0 -> return "UP"
            1 -> return "RIGHT"
            2 -> return "DOWN"
            3 -> return "LEFT"
        }
        throw IllegalStateException(
            """
                Unable to create toString() for a absolute direction.
                intRepresentation: $intRepresentation
                """.trimIndent()
        )
    }

    companion object {
        /**
         * Converts an integer to the corresponding gettinggoodguys.games.movement.directions.AbsoluteDirection
         * @param value the integer to be converted
         * @return the corresponding gettinggoodguys.games.movement.directions.AbsoluteDirection
         * @throws IllegarArgumentException if there is no gettinggoodguys.games.movement.directions.AbsoluteDirection corresponding to the given value
         */
        fun intToAbsoluteDirection(value: Int): AbsoluteDirection {
            return when (value) {
                0 -> UP
                1 -> RIGHT
                2 -> DOWN
                3 -> LEFT
                else -> throw IllegalArgumentException("Unable to convert int $value to an absolute direction")
            }
        }
    }

}