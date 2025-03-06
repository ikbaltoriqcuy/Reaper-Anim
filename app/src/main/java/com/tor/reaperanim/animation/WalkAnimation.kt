package com.tor.reaperanim.animation

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import com.tor.reaperanim.R
import com.tor.reaperanim.manager.SpriteManager
import com.tor.reaperanim.model.SpriteObject

fun getWalkAnimation(context: Context, size: Float): List<SpriteObject> {
    return listOf(
        R.drawable.reaper_walk_1,
        R.drawable.reaper_walk_2,
        R.drawable.reaper_walk_3,
        R.drawable.reaper_walk_4,
        R.drawable.reaper_walk_5,
        R.drawable.reaper_walk_6,
        R.drawable.reaper_walk_7,
        R.drawable.reaper_walk_8,
        R.drawable.reaper_walk_9,
        R.drawable.reaper_walk_10,
        R.drawable.reaper_walk_11,
        R.drawable.reaper_walk_12,
        R.drawable.reaper_walk_13,
        R.drawable.reaper_walk_14,
        R.drawable.reaper_walk_15,
        R.drawable.reaper_walk_16,
        R.drawable.reaper_walk_17,
        R.drawable.reaper_walk_18,
        R.drawable.reaper_walk_19,
        R.drawable.reaper_walk_20,
        R.drawable.reaper_walk_21,
        R.drawable.reaper_walk_22,
        R.drawable.reaper_walk_23,
        R.drawable.reaper_walk_24,
    ).map { drawableRes ->
        SpriteObject(
            "WalkAnimation",
            SpriteManager((context.getDrawable(drawableRes) as BitmapDrawable).bitmap)
        ).apply {
            sprite.setMaintainAspectRatio(true)
            sprite.size(width = size.toInt())
        }
    }
}
