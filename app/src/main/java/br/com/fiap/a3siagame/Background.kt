package br.com.fiap.a3siagame

import android.content.res.Resources
import android.graphics.Bitmap

class Background(
    val width: Int,
    val height: Int,
    res: Resources
) : GameObject(res){

    override var x = 0
    override var y = 0

    private val velocity = 10

    override val bitmap: Bitmap = loadBitmap(R.drawable.game_background, width, height)

    override fun update() {
        x -= velocity

        if(x + bitmap.width <= 0) {
            x = width
        }
    }
}