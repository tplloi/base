package vn.loitp.app.activity.animation.activityTransition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.common.Constants
import com.loitpcore.core.utilities.LActivityUtil
import com.loitpcore.core.utilities.LUIUtil
import com.loitpcore.data.ActivityData
import kotlinx.android.synthetic.main.activity_animation_1.*
import vn.loitp.app.R

@LogTag("Animation1Activity")
@IsFullScreen(false)
class Animation1Activity : BaseFontActivity(), OnClickListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_animation_1
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
                    onBackPressed()
                }
            )
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.viewShadow?.isVisible = true
            this.tvTitle?.text = Animation1Activity::class.java.simpleName
        }
        btNoAnim.setOnClickListener(this)
        btSystemDefault.setOnClickListener(this)
        btSlideLeft.setOnClickListener(this)
        btSlideRight.setOnClickListener(this)
        btSlideDown.setOnClickListener(this)
        btSlideUp.setOnClickListener(this)
        btFade.setOnClickListener(this)
        btZoom.setOnClickListener(this)
        btWindMill.setOnClickListener(this)
        btDiagonal.setOnClickListener(this)
        btSpin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent(this, Animation2Activity::class.java)
        startActivity(intent)
        when (v) {
            btNoAnim -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SYSTEM_DEFAULT
                LActivityUtil.transActivityNoAnimation(this)
            }
            btSystemDefault ->
                ActivityData.instance.type =
                    Constants.TYPE_ACTIVITY_TRANSITION_SYSTEM_DEFAULT
            btSlideLeft -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SLIDELEFT
                LActivityUtil.slideLeft(this)
            }
            btSlideRight -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SLIDERIGHT
                LActivityUtil.slideRight(this)
            }
            btSlideDown -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SLIDEDOWN
                LActivityUtil.slideDown(this)
            }
            btSlideUp -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SLIDEUP
                LActivityUtil.slideUp(this)
            }
            btFade -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_FADE
                LActivityUtil.fade(this)
            }
            btZoom -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_ZOOM
                LActivityUtil.zoom(this)
            }
            btWindMill -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_WINDMILL
                LActivityUtil.windmill(this)
            }
            btDiagonal -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_DIAGONAL
                LActivityUtil.diagonal(this)
            }
            btSpin -> {
                ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SPIN
                LActivityUtil.spin(this)
            }
        }
    }
}