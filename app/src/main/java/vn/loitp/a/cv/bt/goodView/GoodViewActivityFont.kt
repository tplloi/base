package vn.loitp.a.cv.bt.goodView

import android.graphics.Color
import android.os.Bundle
import androidx.core.view.isVisible
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.openUrlInBrowser
import com.loitp.core.utilities.LUIUtil
import com.loitp.views.bt.goodView.LGoodView
import kotlinx.android.synthetic.main.a_good_view.*
import vn.loitp.R

@LogTag("GoodViewActivity")
@IsFullScreen(false)
class GoodViewActivityFont : BaseActivityFont() {

    private var lGoodView: LGoodView? = null

    override fun setLayoutResourceId(): Int {
        return R.layout.a_good_view
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
            this.ivIconRight?.let {
                LUIUtil.setSafeOnClickListenerElastic(
                    view = it,
                    runnable = {
                        context.openUrlInBrowser(
                            url = "https://github.com/venshine/GoodView"
                        )
                    }
                )
                it.isVisible = true
                it.setImageResource(R.drawable.ic_baseline_code_48)
            }
            this.tvTitle?.text = GoodViewActivityFont::class.java.simpleName
        }

        lGoodView = LGoodView(this)
        bt.setOnClickListener {
            lGoodView?.let {
                it.setText("+1")
                it.show(v = bt)
            }
        }
        imageView.setOnClickListener {
            imageView.setColorFilter(Color.TRANSPARENT)
            lGoodView?.apply {
                this.setImage(R.drawable.ic_account_circle_black_48dp)
                // this.setDistance(1000)
                // this.setTranslateY(0, 10000)
                // this.setAlpha(0, 0.5f)
                // this.setDuration(3000)
                this.show(it)
            }
        }
    }
}
