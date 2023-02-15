package com.hienthai.baseprojectmvvm.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hienthai.baseprojectmvvm.R

fun ImageView.load(url: GlideUrl, placeholder: Int = R.drawable.bg_placeholder) {
    Glide.with(context)
        .asBitmap().load(url).fitCenter()
        .placeholder(placeholder).override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .transform(CenterCrop(), RoundedCorners(20))
        .error(placeholder)
        .into(this)
}