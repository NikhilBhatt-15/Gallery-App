package com.example.gallery

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.gallery.components.DescriptionField
import com.example.gallery.components.ImageCard
import com.example.gallery.components.ImageChanger
import com.example.gallery.data.GalleryViewModel


@Composable
fun GalleryScreen(galleryViewModel: GalleryViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)
        .padding(top = 20.dp)) {
        ImageCard(galleryViewModel.currentImage.value)
        Spacer(modifier = Modifier.height(50.dp))
        DescriptionField(galleryViewModel.currentImage.value)
        Spacer(modifier = Modifier.height(80.dp))
        ImageChanger(){
            galleryViewModel.change(
                it
            )
            Log.d("change",galleryViewModel.currentImage.value.toString())
        }
    }
}