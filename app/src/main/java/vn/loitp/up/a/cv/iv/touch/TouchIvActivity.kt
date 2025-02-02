package vn.loitp.up.a.cv.iv.touch

import android.os.Bundle
import androidx.core.view.isVisible
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.common.NOT_FOUND
import com.loitp.core.ext.loadGlide
import com.loitp.core.ext.setSafeOnClickListenerElastic
import vn.loitp.R
import vn.loitp.databinding.AIvTouchBinding
import vn.loitp.up.common.Constants

@LogTag("TouchImageViewActivity")
@IsFullScreen(false)
class TouchIvActivity : BaseActivityFont() {
    private lateinit var binding: AIvTouchBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AIvTouchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.lActionBar.apply {
            this.ivIconLeft.setSafeOnClickListenerElastic(
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.isVisible = false
            this.tvTitle?.text = TouchIvActivity::class.java.simpleName
        }
        // note when use with glide, must have placeholder
        binding.lTouchImageView.loadGlide(
            any = Constants.URL_IMG,
            resPlaceHolder = R.color.colorPrimary
        )
    }
}
