package com.tor.reaperanim.animation

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import com.tor.reaperanim.R
import com.tor.reaperanim.manager.SpriteManager
import com.tor.reaperanim.model.SpriteObject


fun getKickAnimation(context: Context, size: Float): List<SpriteObject> {
    return listOf(
        R.drawable.reaper_kick_1,
        R.drawable.reaper_kick_2,
        R.drawable.reaper_kick_3,
        R.drawable.reaper_kick_4,
        R.drawable.reaper_kick_5,
        R.drawable.reaper_kick_6,
        R.drawable.reaper_kick_7,
        R.drawable.reaper_kick_8,
        R.drawable.reaper_kick_9,
        R.drawable.reaper_kick_10,
        R.drawable.reaper_kick_11,
        R.drawable.reaper_kick_12,
        R.drawable.reaper_kick_1,
        R.drawable.reaper_kick_2,
        R.drawable.reaper_kick_3,
        R.drawable.reaper_kick_4,
        R.drawable.reaper_kick_5,
        R.drawable.reaper_kick_6,
        R.drawable.reaper_kick_7,
        R.drawable.reaper_kick_8,
        R.drawable.reaper_kick_9,
        R.drawable.reaper_kick_10,
        R.drawable.reaper_kick_11,
        R.drawable.reaper_kick_12,
    ).map { drawableRes ->
        SpriteObject(
            "KickAnimation",
            SpriteManager((context.getDrawable(drawableRes) as BitmapDrawable).bitmap)
        ).apply {
            sprite.setMaintainAspectRatio(true)
            sprite.size(width = size.toInt())
        }
    }
}
