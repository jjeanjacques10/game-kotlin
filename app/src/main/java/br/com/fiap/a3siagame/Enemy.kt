package br.com.fiap.a3siagame

import android.content.res.Resources
import android.graphics.Bitmap

class Enemy (
    val screenX: Int,
    val screenY: Int,
    res: Resources
) : GameObject(res){

    override var x: Int = 0
    override var y: Int = 0

    override val bitmap: Bitmap= loadBitmap(R.drawable.enemy, 2)

    override fun update() {

    }

}