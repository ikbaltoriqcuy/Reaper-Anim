package com.tor.reaperanim.animation

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import com.tor.reaperanim.R
import com.tor.reaperanim.manager.SpriteManager
import com.tor.reaperanim.model.SpriteObject


fun getJumpAnimation(context: Context, size: Float): List<SpriteObject> {
    return listOf(
        R.drawable.reaper_jump_1,
        R.drawable.reaper_jump_2,
        R.drawable.reaper_jump_3,
        R.drawable.reaper_jump_4,
        R.drawable.reaper_jump_5,
        R.drawable.reaper_jump_1,
        R.drawable.reaper_jump_2,
        R.drawable.reaper_jump_3,
        R.drawable.reaper_jump_4,
        R.drawable.reaper_jump_5,
        R.drawable.reaper_jump_1,
        R.drawable.reaper_jump_2,
        R.drawable.reaper_jump_3,
        R.drawable.reaper_jump_4,
        R.drawable.reaper_jump_5,
        R.drawable.reaper_jump_1,
        R.drawable.reaper_jump_2,
        R.drawable.reaper_jump_3,
        R.drawable.reaper_jump_4,
        R.drawable.reaper_jump_5,
    ).map { drawableRes ->
        SpriteObject(
            "JumpAnimation",
            SpriteManager((context.getDrawable(drawableRes) as BitmapDrawable).bitmap)
        ).apply {
            sprite.setMaintainAspectRatio(true)
            sprite.size(width = size.toInt())
        }
    }
}
