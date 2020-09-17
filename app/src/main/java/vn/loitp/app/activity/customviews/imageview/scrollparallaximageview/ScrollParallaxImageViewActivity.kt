package vn.loitp.app.activity.customviews.imageview.scrollparallaximageview

import android.os.Bundle
import com.annotation.LayoutId
import com.core.base.BaseFontActivity
import com.views.imageview.scrollparallax.LScrollParallaxImageView
import com.views.imageview.scrollparallax.parallaxstyle.HorizontalScaleStyle
import com.views.imageview.scrollparallax.parallaxstyle.VerticalMovingStyle
import kotlinx.android.synthetic.main.activity_imageview_scrollparallax.*
import vn.loitp.app.R

//https://github.com/gjiazhe/ScrollParallaxImageView

@LayoutId(R.layout.activity_imageview_scrollparallax)
class ScrollParallaxImageViewActivity : BaseFontActivity() {

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spiv.setParallaxStyles(VerticalMovingStyle()) // or other parallax styles
        for (i in 0..9) {
            val lScrollParallaxImageView = LScrollParallaxImageView(activity)
            lScrollParallaxImageView.setImageResource(if (i % 2 == 0) R.drawable.iv else R.drawable.logo)
            lScrollParallaxImageView.setParallaxStyles(HorizontalScaleStyle()) // or other parallax styles
            llHorizontal.addView(lScrollParallaxImageView)
        }
        for (i in 0..19) {
            val lScrollParallaxImageView = LScrollParallaxImageView(activity)
            lScrollParallaxImageView.setImageResource(if (i % 2 == 0) R.drawable.iv else R.drawable.logo)
            lScrollParallaxImageView.setParallaxStyles(VerticalMovingStyle()) // or other parallax styles
            ll.addView(lScrollParallaxImageView)
        }
    }
}
