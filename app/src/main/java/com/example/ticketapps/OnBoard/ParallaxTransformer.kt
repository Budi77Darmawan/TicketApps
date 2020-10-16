package com.example.ticketapps.OnBoard

import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.example.ticketapps.R

class ParallaxTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val absPosition = Math.abs(position)
        if (position < -1) {
            // This page is way off-screen to the left.
            view.alpha = 1f
        } else if (position <= 1) {
            val image = view.findViewById<ImageView>(R.id.intro_image)
            image?.apply {
                setScaleX(1.0f - absPosition * 2)
                setScaleY(1.0f - absPosition * 2)
                setAlpha(1.0f - absPosition * 2)
            }

        } else {
            // This page is way off-screen to the right.
            view.alpha = 1f
        }


    }
}

