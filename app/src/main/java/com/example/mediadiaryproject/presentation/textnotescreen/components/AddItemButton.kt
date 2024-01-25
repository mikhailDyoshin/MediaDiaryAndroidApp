package com.example.mediadiaryproject.presentation.textnotescreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mediadiaryproject.ui.theme.BlueText
import kotlin.math.exp

@Composable
fun AddItemButton(backgroundColor: Color = Color.White, modifier: Modifier, onClick: () -> Unit) {
    Column(modifier = modifier) {
        ConstraintLayout(modifier = Modifier.height(115.dp)) {

            val (addButton, line) = createRefs()



            SigmoidLine(backgroundColor = backgroundColor, modifier = Modifier.constrainAs(line) {
                this.bottom.linkTo(parent.bottom)
            })
            RoundButton(onClick = { onClick() }, modifier = Modifier.constrainAs(addButton) {
                this.centerHorizontallyTo(parent)
                this.top.linkTo(parent.top)
            })
        }
    }

}


@Composable
private fun RoundButton(modifier: Modifier, onClick: () -> Unit) {

    val buttonSize = 82.dp

    Column(modifier = modifier) {
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(size = buttonSize)
                )
                .size(buttonSize)
        ) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add-button icon")
        }
    }

}


@Composable
private fun SigmoidLine(backgroundColor: Color, modifier: Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color = Color.Transparent)
            ) {
                val startX = size.width / 2
                val endX = size.width
                val step = 1f
                val scale = 30f

                val magnitude = -160f

                val lineWidth = 10f

                // Drawing the left sigmoid
                for (x in startX.toInt()..endX.toInt() step step.toInt()) {
                    val xOffsetLeft = x.toFloat() - startX
                    val yOffsetLeft =
                        sigmoid(-(xOffsetLeft - startX) - 120f, scale, magnitude) - magnitude


                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            xOffsetLeft,
                            yOffsetLeft
                        ),
                        size = androidx.compose.ui.geometry.Size(lineWidth, lineWidth)
                    )

                    val rectTopYLeft = yOffsetLeft + lineWidth
                    val rectHeightLeft = size.height - rectTopYLeft
                    
                    drawRect(
                        color = backgroundColor,
                        topLeft = Offset(
                            xOffsetLeft,
                            rectTopYLeft,
                        ),
                        size = androidx.compose.ui.geometry.Size(lineWidth, rectHeightLeft)

                    )

                    val xOffsetRight = x.toFloat()
                    val yOffsetRight = sigmoid(x.toFloat() - startX - 120f, scale, magnitude) - magnitude
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            xOffsetRight,
                            yOffsetRight,
                        ),
                        size = androidx.compose.ui.geometry.Size(lineWidth, lineWidth)
                    )

                    val rectTopYRight = yOffsetRight + lineWidth
                    val rectHeightRight = size.height - rectTopYRight


                    drawRect(
                        color = backgroundColor,
                        topLeft = Offset(
                            xOffsetRight + lineWidth,
                            rectTopYRight,
                        ),
                        size = androidx.compose.ui.geometry.Size(lineWidth, rectHeightRight)

                    )

                }
            }
        }
    }

}

private fun sigmoid(x: Float, scale: Float, magnitude: Float): Float {
    return (1f / (1f + exp(-x / scale))) * magnitude
}

@Preview
@Composable
fun SigmoidGraphPreview() {
    SigmoidLine(modifier = Modifier, backgroundColor = Color.White)
}

@Preview
@Composable
fun RoundButtonPreview() {
    RoundButton(modifier = Modifier, onClick = {})
}


@Preview
@Composable
fun AddItemButtonPreview() {
    AddItemButton(
        backgroundColor = Color.White,
        modifier = Modifier.background(color = BlueText),
        onClick = {})
}