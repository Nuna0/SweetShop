package com.example.sweetshop.adapters

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.palette.graphics.Palette
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.GlideApp
import com.example.sweetshop.R

class SlidingImageAdapter (
    var context: Context,
    var arrayImages: ArrayList<String>,
    /*val swipetoclose: () -> Unit*/
) : PagerAdapter() {

    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(
            R.layout.item_baner_images,
            container,
            false
        )

        val image = imageLayout.findViewById<ImageView>(R.id.imageBanner)
        val cardView = imageLayout.findViewById<CardView>(R.id.cardView)

        /*val bitmap = (image.getDrawable() as BitmapDrawable).bitmap
        val pixel = bitmap.getPixel(x, y)
        container.setBackgroundResource(pixel)*/


        GlideApp.with(context)
            .asBitmap()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            //.placeholder(R.drawable.ic_loading_svgrepo_com)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.i(ContentValues.TAG, "Error while loading picture: $e")
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Bitmap>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        val palette: Palette = Palette.from(resource).generate()
                        val color = palette.dominantSwatch?.rgb ?: R.color.default_title_background
                        cardView.setCardBackgroundColor(color)
                    }
                    return false
                }

            })
            .load(arrayImages[position])
            .into(image)



        /* container.setOnTouchListener(object: OnSwipeTouchListener(ctx = context) {
             override fun onSwipeLeft() {
                 swipetoclose
             }
         })*/

        container.addView(imageLayout, 0)

        return imageLayout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj


    override fun getCount(): Int = arrayImages.size

}