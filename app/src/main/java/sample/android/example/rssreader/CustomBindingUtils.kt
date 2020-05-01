package sample.android.example.rssreader

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

object CustomBindingUtils {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView:ImageView, url: String)
    {
        Picasso.get()
            .load(url)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(imageView)
    }


}