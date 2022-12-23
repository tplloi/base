package vn.loitp.app.a.cv.lCv

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.daimajia.androidanimations.library.Techniques
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseFontActivity
import com.loitp.core.common.Constants
import com.loitp.core.utilities.LAnimationUtil
import com.loitp.core.utilities.LScreenUtil
import com.loitp.core.utilities.LUIUtil
import com.loitp.views.card.LCardView
import kotlinx.android.synthetic.main.activity_l_card_view.*
import vn.loitp.R

@LogTag("LCardViewActivity")
@IsFullScreen(false)
class LCardViewActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_l_card_view
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
            this.tvTitle?.text = LCardViewActivity::class.java.simpleName
        }
        lCardView0.apply {
            callback = object : LCardView.Callback {
                override fun onClickRoot(v: View) {
                    // LAnimationUtil.play(v, Techniques.Pulse)
                }

                override fun onLongClickRoot(v: View) {
                    // LAnimationUtil.play(v, Techniques.Pulse)
                }

                override fun onClickText(v: View) {
                    LAnimationUtil.play(view = v, techniques = Techniques.Pulse)
                }

                override fun onLongClickText(v: View) {
                    LAnimationUtil.play(view = v, techniques = Techniques.Pulse)
                }
            }
            setText(System.currentTimeMillis().toString() + "")
            height = 300
            width = LScreenUtil.screenWidth * 3 / 4
            setRadius(30f)
            setCardElevation(10f)
            setImg(Constants.URL_IMG_2)
        }
        lCardView1.apply {
            setText(System.currentTimeMillis().toString() + "")
            height = LScreenUtil.screenHeight * 3 / 2
            setRadius(50f)
            setCardElevation(20f)
            setImg(Constants.URL_IMG_4)
        }
    }
}