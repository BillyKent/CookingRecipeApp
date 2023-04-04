package com.billysoft.cookingrecipeapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ImageView.loadImageFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}