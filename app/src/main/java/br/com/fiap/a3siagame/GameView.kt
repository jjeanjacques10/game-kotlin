package br.com.fiap.a3siagame

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.lifecycle.MutableLiveData

class GameView(
    context: Context,
    attributeSet: AttributeSet
): SurfaceView(context, attributeSet), Runnable {

    private var gameThread: Thread? = null

    var screenX: Int = 0
    var screenY: Int = 0

    private var isRunning = false
    private val FPS = 30L

    var gameObjects: Array<GameObject>? = null

    val pontuacaoLiveData = MutableLiveData<Int>()
    val gameOverLiveData = MutableLiveData<Boolean>()

    fun initGame(){
         gameObjects = arrayOf<GameObject>(
            Background(screenX, screenY, resources) ,
            Background(screenX, screenY, resources).apply {
                x = screenX
            },
            GreenLantern(screenX, screenY, resources).apply {
                initGreenLantern()
            },
            Enemy(screenX, screenY, resources, pontuacaoLiveData).apply {
                initEnemy()
            }
        )
        gameOverLiveData.postValue(false)
    }

    override fun run() {
        while (isRunning) {

            update()
            draw()
            sleep()

        }
    }

    private fun update() {
        gameObjects?.forEach { it.update() }
        gameObjects?.let {
            collitionDetect(it[2], it[3])
        }
    }

    private fun draw() {

        if(holder.surface.isValid) {

            val canvas = holder.lockCanvas()

            gameObjects?.forEach { it.draw(canvas) }

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

                if(isRunning){
                    (gameObjects?.get(2) as? GreenLantern)?.jump()
                }else{
                    initGame()
                    resume()
                }

            }
            MotionEvent.ACTION_UP -> { }
        }
        return super.onTouchEvent(event)
    }

    private fun gameOver(){
        gameOverLiveData.postValue(true)
        pontuacaoLiveData.postValue(0)
        pause()
    }

    private fun collitionDetect(gameObject1: GameObject, gameObject2: GameObject ){
        if(gameObject1.x < gameObject2.x + gameObject2.bitmap.width - 120 &&
            gameObject1.x + gameObject1.bitmap.width - 100 > gameObject2.x &&
            gameObject1.y < gameObject2.y + gameObject2.bitmap.height - 100 &&
            gameObject1.y + gameObject1.bitmap.height - 100 > gameObject2.y)
        {
            gameOver()
        }
    }
}