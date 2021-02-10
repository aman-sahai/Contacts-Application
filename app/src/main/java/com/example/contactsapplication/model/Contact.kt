package com.example.contactsapplication.model

import android.widget.ImageView;
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class Contact : BaseObservable() {
    @get:Bindable
    var name: String? = null

    @get:Bindable
    var phoneNumber: String? = null

    @get:Bindable
    var photoUri: String? = null

    @BindingAdapter("photoUri")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
    }
}