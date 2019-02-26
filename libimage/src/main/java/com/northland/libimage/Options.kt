package com.northland.libimage

import com.bumptech.glide.request.RequestOptions


fun RequestOptions.default(error: Int, place: Int): RequestOptions {
    return RequestOptions.centerCropTransform().placeholder(place).error(error)
}

fun RequestOptions.resize(w: Int, h: Int, e: Int, p: Int): RequestOptions {
    return default(e, p).override(w, h)
}

fun RequestOptions.circle(e: Int, p: Int): RequestOptions {
    return default(e, p).circleCrop()
}


