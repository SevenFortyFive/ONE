package com.example.one.helper

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * @author yooo_fan
 * 用于解析字符串的像素信息
 * 返回一个二维数组，字符中黑色为1，白色为0
 */
object PixelAnalyzer {
    fun analyzeCharacterPixels(character: String): Array<Array<Int>> {
        // 创建一个 Bitmap 对象来保存字符的像素信息
        val bitmap = Bitmap.createBitmap(50 * character.length, 50, Bitmap.Config.ARGB_8888)
        // 创建一个 Canvas 对象，用于在 Bitmap 上绘制字符
        val canvas = Canvas(bitmap)
        // 清空 Bitmap，设置全白背景
        canvas.drawColor(Color.WHITE)
        // 绘制字符在画布的中间位置
        drawCharacterInCenter(character, canvas)

        // 创建一个二维数组来存储像素信息
        val pixelArray = Array(bitmap.height) { Array(bitmap.width) { 0 } }

        // 遍历 Bitmap 中的像素，获取像素信息并存储到二维数组中
        for (x in 0 until bitmap.width) {
            for (y in 0 until bitmap.height) {
                val pixel = bitmap.getPixel(x, y)
                // 判断像素颜色是否为黑色
                val isBlack = Color.red(pixel) == 0 && Color.green(pixel) == 0 && Color.blue(pixel) == 0
                // 将黑色像素输出为1，白色像素输出为0
                pixelArray[y][x] = if (isBlack) 1 else 0
            }
        }
        return pixelArray
    }

    /**
     * @author yooo_fan
     * 在画布上画上字符
     * 返回bitmap
     */
    private fun drawCharacterInCenter(character: String, canvas: Canvas) {
        // 获取画布的宽度和高度
        val canvasWidth = canvas.width.toFloat()
        val canvasHeight = canvas.height.toFloat()

        // 创建一个 Paint 对象，设置字符的颜色、大小等信息
        val paint = Paint()
        paint.color = Color.BLACK // 设置字符颜色为黑色
        paint.textSize = 50f // 设置字符大小为 50 像素
        paint.style = Paint.Style.FILL

        // 计算字符的宽度和高度
        val textWidth = paint.measureText(character.toString())
        val textHeight = paint.descent() - paint.ascent()

        // 计算绘制字符的起始位置
        val x = (canvasWidth - textWidth) / 2
        val y = (canvasHeight - textHeight) / 2 - paint.ascent()

        // 在 Canvas 上绘制字符
        canvas.drawText(character, x, y, paint)
    }
}
