package com.example.gallery.components

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissState
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.gallery.R
import com.example.gallery.data.GalleryEvent
import com.example.gallery.data.ImageData
import kotlin.math.roundToInt

//@Preview
@Composable
fun ImageCard(imageData: ImageData,onSwipe:(GalleryEvent)->Unit) {
    var dragImageAmount by remember {
        mutableStateOf(0.0)
    }
    var offsetX by remember {
        mutableStateOf(0f)
    }
    var padding by remember {
        mutableStateOf(20.dp)
    }
    animateDpAsState(targetValue = padding, animationSpec = tween(easing = LinearOutSlowInEasing))
    Box(modifier = Modifier

        .fillMaxWidth()
        .background(Color.White)
        .height(400.dp)
        .shadow(elevation = 5.dp, shape = RectangleShape, ambientColor = Color.Gray)
        .padding(padding)

    ) {
//      
        animateFloatAsState(targetValue = offsetX)
        Image(modifier = Modifier
            .fillMaxSize()
            .offset(offsetX.dp, 0.dp)
            .pointerInput(Unit) {

                detectHorizontalDragGestures(onDragEnd = {
                    offsetX = 0f
                    if (dragImageAmount > 0) {
                        onSwipe(GalleryEvent.Prev)
                    } else {
                        onSwipe(GalleryEvent.Next)
                    }
                }
                ) { change, dragAmount ->
                    offsetX = dragAmount
                    dragImageAmount = dragAmount.toDouble()
                }

            }
            ,painter = painterResource(imageData.image),
            contentScale = ContentScale.FillBounds,
            contentDescription = "")

    }}


@Composable
fun DescriptionField(imageData: ImageData) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color(172, 167, 167, 255))
        .padding(18.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = imageData.description, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Text(text = imageData.author+" ", fontWeight = FontWeight.Bold)
            Text(text = "("+imageData.year.toString()+")", color = Color(216, 202, 202, 255))
        }
    }
}

@Composable
fun ChangeButton(name: String,onClick:(GalleryEvent)->Unit) {
    Button(modifier = Modifier
        .width(150.dp)
        .clip(RoundedCornerShape(5.dp))
        .padding(8.dp)
        ,onClick = {
            if(name=="Previous"){
                onClick(GalleryEvent.Prev)
            }
            else{
                onClick(GalleryEvent.Next)
            }
        }) {
        Text(text = name, color = Color.White)
    }
}

@Composable
fun ImageChanger(onClick: (GalleryEvent) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        ChangeButton(name = "Previous") {
            onClick(it)
        }
        ChangeButton(name = "Next") {
            onClick(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Dragging() {
    var offset by remember {
        mutableStateOf(0f)
    }
    var text by remember {
       mutableStateOf("Unchanged")
    }
    Box(modifier = Modifier
        .width(200.dp)
        .height(200.dp)
        .background(Color.Cyan)
        .pointerInput(Unit) {
            detectHorizontalDragGestures() { _, dragAmount ->
                if (dragAmount > 0) {
                    text = "right"
                }
                if (dragAmount < 0) {
                    text = "left"
                }
            }
        }
         ,contentAlignment = Alignment.Center

    ){
        Text(text )
    }
}
