package br.com.fiap.a3siagame

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

class Enemy (
    val screenX: Int,
    val screenY: Int,
    res: Resources,
    val liveData: MutableLiveData<Int>
) : GameObject(res){

    override var x: Int = 0
    override var y: Int = 0
    var hasPontuation = false

    override val bitmap: Bitmap= loadBitmap(R.drawable.enemy, 2)


    fun initEnemy(){
        x = screenX
        y = (0..screenY - bitmap.height).random()
        hasPontuation = false
    }

    override fun update() {
        x -= 14

        if(x + bitmap.width <= 0){
            initEnemy()
        }

        if(!hasPontuation && x + bitmap.width <= screenX / 3){
            val currentValue = liveData.value ?: 0
            liveData.postValue(currentValue + 1)
            hasPontuation = true
        }

    }

}