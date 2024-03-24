package com.example.gallery.data

sealed class GalleryEvent {
    data object Next:GalleryEvent()
    data object Prev:GalleryEvent()
}