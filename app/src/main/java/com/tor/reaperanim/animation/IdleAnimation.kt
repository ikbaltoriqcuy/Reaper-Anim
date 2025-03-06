package com.tor.reaperanim.animation

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import com.tor.reaperanim.R
import com.tor.reaperanim.manager.SpriteManager
import com.tor.reaperanim.model.SpriteObject

fun getIdleAnimation(context: Context, size: Float): List<SpriteObject> {
    return listOf(
        R.drawable.reaper_idle_1,
        R.drawable.reaper_idle_2,
        R.drawable.reaper_idle_3,
        R.drawable.reaper_idle_4,
        R.drawable.reaper_idle_5,
        R.drawable.reaper_idle_6,
        R.drawable.reaper_idle_7,
        R.drawable.reaper_idle_8,
        R.drawable.reaper_idle_9,
        R.drawable.reaper_idle_10,
        R.drawable.reaper_idle_11,
        R.drawable.reaper_idle_12,
        R.drawable.reaper_idle_13,
        R.drawable.reaper_idle_14,
        R.drawable.reaper_idle_15,
        R.drawable.reaper_idle_16,
        R.drawable.reaper_idle_17,
        R.drawable.reaper_idle_18
    ).map { drawableRes ->
        SpriteObject(
            "IdleAnimation",
            SpriteManager((context.getDrawable(drawableRes) as BitmapDrawable).bitmap)
        ).apply {
            sprite.setMaintainAspectRatio(true)
            sprite.size(width = size.toInt())
        }
    }
}
