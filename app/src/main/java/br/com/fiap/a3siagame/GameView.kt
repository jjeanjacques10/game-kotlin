package br.com.fiap.a3siagame

import android.content.Context
import android.view.SurfaceView

class GameView(context: Context,
               val screenX:Int,
               val screenY:Int): SurfaceView(context), Runnable {

    private var gameThread: Thread? = null

    private var isRunning = false

    private val background = Background(screenX,screenY, resources)
    private val background2 = Background(screenX,screenY, resources).apply {
        x = screenX
    }

    override fun run() {
        //The game will run here
        while (isRunning){
            update()
            draw()
            sleep()
        }
    }

    private fun update(){
        background.update()
        background2.update()
    }

    private fun draw(){
        if(holder.surface.isValid){
            val canvas = holder.lockCanvas()

            background.draw(canvas)
            background2.draw(canvas)

            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun sleep(){
        Thread.sleep(1000/60)
    }

    fun resume(){
        isRunning = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause(){
        isRunning = false
        gameThread?.join()
    }


}