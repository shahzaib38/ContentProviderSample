package image.crystalapps.contentprovidersample

import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("customImage")
fun setGlideImage(image : AppCompatImageView, uriImage : String?){

    if(uriImage!=null) {
        val parse = Uri.parse(uriImage)
        Glide.with(image.context).load(parse).into(image)
    }

}
