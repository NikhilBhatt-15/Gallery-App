package com.example.gallery.components

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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

//@Preview
@Composable
fun ImageCard(imageData: ImageData) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .height(400.dp)
        .shadow(elevation = 5.dp, shape = RectangleShape, ambientColor = Color.Gray)
        .padding(20.dp)) {
//
        Image(modifier = Modifier.fillMaxSize(),painter = painterResource(imageData.image),
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
