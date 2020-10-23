package br.com.fiap.a3siagame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.annotation.DrawableRes

abstract class GameObject(
    val res: Resources
)  {

    abstract  var x:Int
    abstract  var y:Int
    abstract  val bitmap:Bitmap

    fun loadBitmap(@DrawableRes imagesRes: Int, width:Int, height:Int): Bitmap{
        return BitmapFactory.decodeResource(res, imagesRes).let {
            Bitmap.createScaledBitmap(it, width, height, false)
        }
    }

    fun loadBitmap(@DrawableRes imagesRes: Int, scaleDownSize:Int): Bitmap{
        return BitmapFactory.decodeResource(res, imagesRes).let {
            val newWidth = it.width/scaleDownSize
            val newHeight = it.height/scaleDownSize

            Bitmap.createScaledBitmap(it, newWidth, newHeight, false)
        }
    }

    abstract fun update()
    fun draw(canvas: Canvas){
        canvas.drawBitmap(bitmap, x.toFloat(), y.toFloat(), null)
    }
}