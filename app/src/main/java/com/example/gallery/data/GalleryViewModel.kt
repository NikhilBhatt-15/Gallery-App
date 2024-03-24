package com.example.gallery.data

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gallery.R

public class GalleryViewModel:ViewModel() {
    var index = mutableIntStateOf(0)
    var ImageList= mutableListOf<ImageData>(ImageData(R.drawable.cup,"Sunset over the mountains", "John Doe", 2020),
        ImageData(R.drawable.can,"City skyline at night", "Jane Smith", 2019),
        ImageData(R.drawable.build,"Abstract art with vibrant colors", "Alice Johnson", 2021),
        ImageData(R.drawable.grass,"Portrait of a woman", "Michael Brown", 2018))
    var currentImage= mutableStateOf(ImageList[index.value])

    fun change(event: GalleryEvent){
        when(event){
            is GalleryEvent.Next->{
                if(index.value==ImageList.size-1){
                    index.value=0
                    currentImage.value=ImageList[index.value]
                }
                else{
                    index.value++
                    currentImage.value=ImageList[index.value]
                }
                Log.d("Change",index.toString())
            }
            is GalleryEvent.Prev->{
                if(index.value==0){
                    index.value=ImageList.size-1;
                    currentImage.value=ImageList[index.value]
                }
                else{
                    index.value--
                    currentImage.value=ImageList[index.value]
                }
                Log.d("Change",index.toString())
            }
        }

    }

}