package com.northland.libimage

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File


fun ImageView.display(string: String, options: RequestOptions?) {
    if (options == null) {
        Glide.with(this).load(string).into(this)
    } else {
        Glide.with(this).load(string).apply(options).into(this)
    }
}

fun ImageView.display(uri: Uri, options: RequestOptions?) {
    if (options == null) {
        Glide.with(this).load(uri).into(this)
    } else {
        Glide.with(this).load(uri).apply(options).into(this)
    }
}

fun ImageView.display(res: Int, options: RequestOptions?) {
    if (options == null) {
        Glide.with(this).load(res).into(this)
    } else {
        Glide.with(this).load(res).apply(options).into(this)
    }
}

fun ImageView.display(file: File, options: RequestOptions?) {
    if (options == null) {
        Glide.with(this).load(file).into(this)
    } else {
        Glide.with(this).load(file).apply(options).into(this)
    }
}
