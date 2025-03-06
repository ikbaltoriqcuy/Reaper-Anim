package com.tor.reaperanim.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tor.reaperanim.R
import com.tor.reaperanim.animation.getIdleAnimation
import com.tor.reaperanim.animation.getJumpAnimation
import com.tor.reaperanim.animation.getKickAnimation
import com.tor.reaperanim.animation.getWalkAnimation
import com.tor.reaperanim.manager.SpriteManager
import com.tor.reaperanim.model.SpriteObject
import com.tor.reaperanim.utils.loadImageBitmap

private const val ANIMATION_SPEED = 300f
private const val POSITION_OFFSET = 10f

private const val DELAY_24 = 24
private const val DELAY_40 = 40

private const val POSE_JUMP = 2

const val NANOSECONDS_PER_SECOND = 1_000_000_000f
const val NANOSECONDS_PER_MILLISECOND = 1_000_000f

@Preview(name = "Tablet", device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun ReaperCharacterAnimView() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx()}
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }

    val backgroundBitmap = context.loadImageBitmap(R.drawable.illustration_background)
    val backgroundSpriteOne = createBackgroundSprite(backgroundBitmap, screenHeightPx)
    val backgroundSpriteTwo = createBackgroundSprite(backgroundBitmap, screenHeightPx)
    val backgroundSpriteThree = createBackgroundSprite(backgroundBitmap, screenHeightPx)

    var buttonSelected by remember { mutableIntStateOf(0) }
    var lastFrameTime by remember { mutableLongStateOf(0L) }
    var positionBackgroundOne by remember { mutableFloatStateOf(0f) }
    var positionBackgroundTwo by remember { mutableFloatStateOf(backgroundSpriteOne.sprite.image.width.toFloat()) }
    var positionBackgroundThree by remember { mutableFloatStateOf(backgroundSpriteOne.sprite.image.width.toFloat() * 2) }

    val widthCharacter = screenWidthPx * 0.3f

    val idleAnim = getIdleAnimation(context, widthCharacter)
    val walkAnim = getWalkAnimation(context, widthCharacter)
    val kickAnim = getKickAnimation(context, widthCharacter)
    val jumpAnim= getJumpAnimation(context, widthCharacter)
    var frameIndex by remember { mutableIntStateOf(0) }

    val characterPoses = listOf(
        idleAnim,
        walkAnim,
        jumpAnim,
        kickAnim,
    )
    var poseIndex by remember { mutableIntStateOf(0) }


    LaunchedEffect(Unit) {
        var totalTime = 0f
        while (true) {
            withFrameNanos { frameTime ->
                if (lastFrameTime != 0L) {
                    val deltaTime = (frameTime - lastFrameTime) / NANOSECONDS_PER_SECOND

                    val xLongDistance = maxOf(positionBackgroundOne, positionBackgroundTwo, positionBackgroundThree)
                    positionBackgroundOne = updatePositionBackground(positionBackgroundOne, backgroundSpriteOne.sprite.image.width, deltaTime, xLongDistance)
                    positionBackgroundTwo = updatePositionBackground(positionBackgroundTwo, backgroundSpriteTwo.sprite.image.width, deltaTime, xLongDistance)
                    positionBackgroundThree = updatePositionBackground(positionBackgroundThree, backgroundSpriteTwo.sprite.image.width, deltaTime, xLongDistance)

                    totalTime += (frameTime - lastFrameTime) / NANOSECONDS_PER_MILLISECOND
                    if (totalTime >= if (poseIndex == POSE_JUMP) DELAY_40 else DELAY_24) {
                        if (frameIndex < characterPoses[poseIndex].size - 1) {
                            frameIndex++
                        } else {
                            frameIndex = 0
                        }
                        totalTime = 0f
                    }
                }

                lastFrameTime = frameTime
            }
        }
    }


    Box(modifier = Modifier.background(Color.Black)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawImage(
                image = backgroundSpriteOne.sprite.image.asImageBitmap(),
                topLeft = Offset(positionBackgroundOne, 0f)
            )
            drawImage(
                image = backgroundSpriteTwo.sprite.image.asImageBitmap(),
                topLeft = Offset(positionBackgroundTwo, 0f)
            )
            drawImage(
                image = backgroundSpriteThree.sprite.image.asImageBitmap(),
                topLeft = Offset(positionBackgroundThree, 0f)
            )

            if (frameIndex < characterPoses[poseIndex].size) {
                drawImage(
                    image = characterPoses[poseIndex][frameIndex].sprite.image.asImageBitmap(),
                    topLeft = Offset(
                        (screenWidthPx / 2) - (widthCharacter / 2),
                        screenHeightPx - characterPoses[poseIndex][frameIndex].sprite.image.height
                    )
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                listOf("Idle", "Walk", "Jump", "Kick").forEachIndexed { index, text ->
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            buttonSelected = index
                            poseIndex = index
                            frameIndex = 0
                        }
                    ) {
                        Text(text = text)
                    }
                }
            }
        }
    }
}

private fun createBackgroundSprite(bitmap: Bitmap, screenHeightPx: Float): SpriteObject {
    return SpriteObject(
        name = "Background",
        sprite = SpriteManager(bitmap)
    ).apply {
        sprite.setMaintainAspectRatio(true)
        sprite.size(height = screenHeightPx.toInt())
    }
}

private fun updatePositionBackground(currentPosition: Float, imageWidth: Int, deltaTime: Float, xLongDistance: Float): Float {
    return if (currentPosition <= imageWidth * -1) {
       xLongDistance  + imageWidth - POSITION_OFFSET
    } else {
       currentPosition - ( deltaTime * ANIMATION_SPEED)
    }

}