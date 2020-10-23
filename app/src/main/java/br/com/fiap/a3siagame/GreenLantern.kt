package br.com.fiap.a3siagame

import android.content.res.Resources
import android.graphics.Bitmap

class GreenLantern(
    val screenX: Int,
    val screenY: Int,
    res: Resources
) : GameObject(res){
    override var x: Int = 0
    override var y: Int =0

    fun initGreenLantern(){
        x = screenX / 2 - (bitmap.width /2)
        y = screenY / 2 - (bitmap.height /2)
    }

    override val bitmap: Bitmap= loadBitmap(R.drawable.green_lantern, 4)

    override fun update() {
        y += 10

        if (y + bitmap.height >= screenY){
            initGreenLantern()
        }
    }

    fun jump(){
        y -= 80
    }

}