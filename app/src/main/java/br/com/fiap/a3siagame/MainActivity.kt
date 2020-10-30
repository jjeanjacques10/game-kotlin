package br.com.fiap.a3siagame

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        val point = Point()
        windowManager.defaultDisplay.getSize(point)

        gameView.apply {
            screenX = point.x
            screenY = point.y
            initGame()
        }

        gameView.pontuacaoLiveData.observe(this, { value ->
            textViewPontuacao.text = "$value"

            gameView.gameOverLiveData.observe(this, {
                if(it){
                    textViewGameOver.visibility = View.VISIBLE
                }else{
                    textViewGameOver.visibility = View.GONE
                    textViewGameOver.text = "Game Over \n${value}"
                }
            })
        })
    }



    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

}