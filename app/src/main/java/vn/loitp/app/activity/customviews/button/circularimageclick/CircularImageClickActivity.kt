package vn.loitp.app.activity.customviews.button.circularimageclick

import android.os.Bundle
import com.annotation.LayoutId
import com.core.base.BaseFontActivity
import kotlinx.android.synthetic.main.activity_button_circular_image_click.*
import vn.loitp.app.R

//guide https://github.com/hoang8f/android-flat-button

@LayoutId(R.layout.activity_button_circular_image_click)
class CircularImageClickActivity : BaseFontActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        circleButton.setOnCircleClickListener { showShort("onClick") }
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }
}
