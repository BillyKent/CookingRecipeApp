package com.billysoft.cookingrecipeapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.CancelableCallback
import com.squareup.picasso.Picasso
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ImageView.loadImageFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}

suspend fun GoogleMap.animate(update: CameraUpdate, durationMs: Int) =
    suspendCoroutine<Boolean> { continuation ->
        animateCamera(update, durationMs, object : CancelableCallback {
            override fun onCancel() {
                continuation.resumeWith(Result.success(false))
            }

            override fun onFinish() {
                continuation.resumeWith(Result.success(true))
            }
        })

    }