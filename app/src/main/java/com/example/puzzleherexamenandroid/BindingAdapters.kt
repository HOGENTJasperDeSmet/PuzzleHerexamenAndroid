package com.example.puzzleherexamenandroid

import `in`.codeshuffle.typewriterview.TypeWriterView
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        Glide.with(imgView.context).load("https://puzzleapiandroid20200809163632.azurewebsites.net/$imgUrl").fitCenter().into(imgView)
    }
}
/**
 * Sets text in the [typeWriter] for animation
 */
@BindingAdapter("animateText")
fun animateText(typeWriter: TypeWriterView, text: String?) {
    text?.let {
        typeWriter.setWithMusic(false)
        typeWriter.animateText(text)
        typeWriter.setDelay(50)
    }
}
/**
 * Formats the number of the [textView] for puzzle number
 */
@BindingAdapter("formatId")
fun formatId(textView: TextView, id: Int?) {
    id?.let {
        textView.text = String.format("%03d", id)
    }
}

/**
 * Decides animation based on result for [imgView]
 */
@BindingAdapter("solveAnimation")
fun resultAnimation(imgView: ImageView,solved: Boolean) {
    val begin : Int
    val end : Int
    if(solved){
        begin = R.drawable.dog_happy
        end =  R.drawable.dog_happy_end
    } else {
        begin = R.drawable.dog_sad
        end =  R.drawable.dog_sad_end
    }
    Glide.with(imgView.context).asGif().load(begin).listener(object:
        RequestListener<GifDrawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<GifDrawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: GifDrawable,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<GifDrawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            resource.setLoopCount(1)
            resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable) {
                    Glide.with(imgView.context).load(end).into(imgView)
                }
            })
            return false
        }

    }).into(imgView)
}