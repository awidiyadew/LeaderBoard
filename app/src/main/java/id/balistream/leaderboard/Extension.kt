package id.balistream.leaderboard

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImg(url : String) {
    Glide.with(context)
            .load(url)
            .into(this)
}
