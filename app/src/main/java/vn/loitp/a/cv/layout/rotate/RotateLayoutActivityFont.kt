package vn.loitp.a.cv.layout.rotate

import android.os.Bundle
import androidx.core.view.isVisible
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.utilities.LDeviceUtil
import com.loitp.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.a_layout_rotate.*
import vn.loitp.R

@LogTag("RotateLayoutActivity")
@IsFullScreen(false)
class RotateLayoutActivityFont : BaseActivityFont() {

    override fun setLayoutResourceId(): Int {
        return R.layout.a_layout_rotate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        lActionBar.apply {
            LUIUtil.setSafeOnClickListenerElastic(
                view = this.ivIconLeft,
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.isVisible = false
            this.tvTitle?.text = RotateLayoutActivityFont::class.java.simpleName
        }
        btRandomRotate.setSafeOnClickListener {
            val angle = LDeviceUtil.getRandomNumber(360)
            rotateLayout.setAngle(angle)
        }
    }
}