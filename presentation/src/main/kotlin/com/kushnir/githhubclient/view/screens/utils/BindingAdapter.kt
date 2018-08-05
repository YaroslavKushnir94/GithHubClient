package com.kushnir.githhubclient.view.screens.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.kushnir.githhubclient.R
import com.squareup.picasso.Picasso

class BindingAdapter {
    companion object {
        @BindingAdapter("url")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_github)
                    .into(view)
        }
    }

}