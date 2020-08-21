package gettinggoodguys.loop

interface MainLoop {

    /**
     * Starting the MainLoop
     */
    fun startLoop()

    /**
     * Stopping the MainLoop
     */
    fun stopLoop()

    /**
     * Manually do one step in the Loop
     */
    fun stepLoop()
}