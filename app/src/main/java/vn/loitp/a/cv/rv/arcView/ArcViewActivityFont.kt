package vn.loitp.a.cv.rv.arcView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.amir.arcview.ArcLinearLayout
import com.loitp.annotation.IsAutoAnimation
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.openUrlInBrowser
import com.loitp.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.a_rv_arc_view.*
import kotlinx.android.synthetic.main.layout_include_arc_button.*
import vn.loitp.R

@LogTag("ArcViewActivity")
@IsFullScreen(false)
@IsAutoAnimation(true)
class ArcViewActivityFont : BaseActivityFont(), View.OnClickListener {
    private lateinit var strokeArc: ArcLinearLayout
    private lateinit var shadowArc: ArcLinearLayout

    override fun setLayoutResourceId(): Int {
        return R.layout.a_rv_arc_view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        lActionBar.apply {
            LUIUtil.setSafeOnClickListenerElastic(
                view = this.ivIconLeft,
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.let {
                LUIUtil.setSafeOnClickListenerElastic(
                    view = it,
                    runnable = {
                        context.openUrlInBrowser(
                            url = "https://github.com/amir5121/arcView"
                        )
                    }
                )
                it.isVisible = true
                it.setImageResource(R.drawable.ic_baseline_code_48)
            }
            this.tvTitle?.text = ArcViewActivityFont::class.java.simpleName
        }

        btKickMe.setOnClickListener(this)
        btKickSwapped.setOnClickListener(this)
        includeButtonsStroke.setOnClickListener(this)
        includeButtonsShadow.setOnClickListener(this)
        strokeArc = layoutInflater.inflate(
            R.layout.stroke_arc_linear_layout,
            includeArcButtonsTempArc,
            false
        ) as ArcLinearLayout
        shadowArc = layoutInflater.inflate(
            R.layout.shadow_arc_linear_layout,
            includeArcButtonsTempArc,
            false
        ) as ArcLinearLayout
    }

    override fun onClick(v: View?) {
        when (v) {
            btKickMe -> {
                if (includeButtonsScrollView.isKnockedIn) {
                    includeButtonsScrollView.knockout()
                } else {
                    includeButtonsScrollView.knockIn()
                }
            }
            btKickSwapped -> {
                includeArcButtonsTempArc.swapView(null)
            }
            includeButtonsShadow -> {
                includeArcButtonsTempArc.swapView(shadowArc)
            }
            includeButtonsStroke -> {
                includeArcButtonsTempArc.swapView(strokeArc)
            }
        }
    }
}
