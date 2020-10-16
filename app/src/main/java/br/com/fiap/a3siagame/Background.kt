package br.com.fiap.a3siagame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas

class Background(
    val width: Int,
    val height: Int,
    val res: Resources
) {
    var x = 0
    var y = 0

    private  val velocity = 10

    val bitmap: Bitmap = BitmapFactory.decodeResource(res, R.drawable.game_background).let {
        Bitmap.createScaledBitmap(it, width, height, false)
    }

    fun update(){
        x -= velocity

        if(x + bitmap.width <= 0){
            x = width
        }
    }

    fun draw(canvas: Canvas){
        canvas.drawBitmap(bitmap, x.toFloat(), y.toFloat(), null)
    }


}