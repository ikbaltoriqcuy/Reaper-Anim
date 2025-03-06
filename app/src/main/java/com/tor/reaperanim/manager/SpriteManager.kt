package com.tor.reaperanim.manager

import android.graphics.Bitmap

class SpriteManager() {

    private var maintainAspectRatio: Boolean = false

    lateinit var image: Bitmap

    constructor(sprite: Bitmap) : this() {
        this.image = sprite
    }

    fun setMaintainAspectRatio(isAspectRatio: Boolean) {
        this.maintainAspectRatio = isAspectRatio
    }

    fun size(width: Int = image.width, height: Int = image.height) {

        if (image.width <= 0 || image.height  <= 0)
            return

        var newWidth: Int = width
        var newHeight: Int = height

        if (maintainAspectRatio ) {
            if (width != image.width && height == image.height) {
                newHeight = calculateHeightRatio(image.width, image.height, newWidth)
            }

            if (width == image.width && height != image.height) {
                newWidth = calculateWidthRatio(image.width, image.height, newHeight)
            }
        }

        image = Bitmap.createScaledBitmap(image, newWidth, newHeight, true)
    }

    private fun calculateHeightRatio(originalWidth: Int, originalHeight: Int, newWidth: Int): Int {
        val aspectRatio = originalHeight.toFloat() / originalWidth.toFloat()
        return Math.round(aspectRatio * newWidth)
    }

    private fun calculateWidthRatio(originalWidth: Int, originalHeight: Int, newHeight: Int): Int {
        val aspectRatio = originalWidth.toFloat() / originalHeight.toFloat()
        return Math.round(aspectRatio * newHeight)
    }

}