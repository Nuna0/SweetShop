package com.example.sweetshop

import android.content.Context
import android.view.View
import androidx.viewpager.widget.ViewPager

class CustPagerTransformer(context: Context) : ViewPager.PageTransformer {
    private val maxTranslateOffsetX: Int
    private var viewPager: ViewPager? = null
    override fun transformPage(view: View, position: Float) {
        if (viewPager == null) {
            viewPager = view.parent as ViewPager
        }
        val leftInScreen = view.left - viewPager!!.scrollX
        val centerXInViewPager = leftInScreen + view.measuredWidth / 2
        val offsetX = centerXInViewPager - viewPager!!.measuredWidth / 2
        val offsetRate = offsetX.toFloat() * 0.25f / viewPager!!.measuredWidth
        val scaleFactor = 1 - Math.abs(offsetRate)
        if (scaleFactor > 0) {
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.translationX = -maxTranslateOffsetX * offsetRate
        }
    }

    /**
     * dp和像素转换
     */
    private fun dp2px(context: Context, dipValue: Float): Int {
        val m = context.resources.displayMetrics.density
        return (dipValue * m + 0.5f).toInt()
    }

    init {
        maxTranslateOffsetX = dp2px(context, 120f)
    }
}

