package br.com.fiap.a3siagame

import android.content.Context
import android.view.MotionEvent
import android.view.SurfaceView

class GameView(
    context: Context,
    val screenX: Int,
    val screenY: Int
): SurfaceView(context), Runnable {

    private var gameThread: Thread? = null

    private var isRunning = false
    private val FPS = 30L

    val gameObject = arrayOf<GameObject>(
        Background(screenX, screenY, resources) ,
        Background(screenX, screenY, resources).apply {
            x = screenX
        },
        GreenLantern(screenX, screenY, resources).apply {
            initGreenLantern()
        },
        Enemy(screenX, screenY, resources)
    )

    override fun run() {
        while (isRunning) {

            update()
            draw()
            sleep()

        }
    }

    private fun update() {
        gameObject.forEach { it.update() }
    }

    private fun draw() {

        if(holder.surface.isValid) {

            val canvas = holder.lockCanvas()

            gameObject.forEach { it.draw(canvas) }

            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun sleep() {
        Thread.sleep(1000 / FPS)
    }

    fun resume() {
        isRunning = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        isRunning = false
        gameThread?.join()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                (gameObject[2] as? GreenLantern)?.jump()
            }
            MotionEvent.ACTION_UP -> { }
        }
        return super.onTouchEvent(event)
    }
}