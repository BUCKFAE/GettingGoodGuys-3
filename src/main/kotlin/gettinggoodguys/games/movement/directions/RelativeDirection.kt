package gettinggoodguys.games.movement.directions


enum class RelativeDirection(// The int int representation of the given enum
    private val intRepresentation: Int
) {
    AHEAD(0), RIGHT(1), LEFT(2);

    /**
     * Returns the int representation of the given enum
     * @return the int representation of the given enum
     */
    fun toInt(): Int {
        return intRepresentation
    }

    fun toAbsoluteDirection(currentPerspective: AbsoluteDirection): AbsoluteDirection {
        // Converting relative dir to absolute dir
        when (intRepresentation) {
            0 -> return currentPerspective
            1 -> return AbsoluteDirection.intToAbsoluteDirection((currentPerspective.toInt() + 1) % 4)
            2 -> return AbsoluteDirection.intToAbsoluteDirection((currentPerspective.toInt() + 3) % 4)
        }
        throw IllegalStateException(
            """
                Unable to create toAbsoluteDirection() for a relative direction.
                intRepresentation: $intRepresentation
                """.trimIndent()
        )
    }

    override fun toString(): String {

        // Returning string
        when (intRepresentation) {
            0 -> return "AHEAD"
            1 -> return "RIGHT"
            2 -> return "LEFT"
        }
        throw IllegalStateException(
            """
                Unable to create toString() for a relative direction.
                intRepresentation: $intRepresentation
                """.trimIndent()
        )
    }

}