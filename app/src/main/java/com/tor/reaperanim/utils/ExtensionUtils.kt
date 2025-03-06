package com.tor.reaperanim.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable

/**
Created by ikbaltoriq on 03,March,2025
 **/

fun Context.loadImageBitmap(imageRes: Int): Bitmap {
    val drawable = getDrawable(imageRes) as BitmapDrawable
    return drawable.bitmap
}