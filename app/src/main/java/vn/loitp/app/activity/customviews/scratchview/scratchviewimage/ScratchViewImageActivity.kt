package vn.loitp.app.activity.customviews.scratchview.scratchviewimage

import android.annotation.SuppressLint
import android.os.Bundle
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.views.scratchview.LScratchImageView
import kotlinx.android.synthetic.main.activity_scratchview_image.*
import vn.loitp.app.R

@LogTag("ScratchViewImageActivity")
@IsFullScreen(false)
class ScratchViewImageActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_scratchview_image
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scratchImageView.setRevealListener(object : LScratchImageView.IRevealListener {
            override fun onRevealed(iv: LScratchImageView) {
                textView.text = "onRevealed"
            }

            override fun onRevealPercentChangedListener(siv: LScratchImageView, percent: Float) {
                textView.text = "onRevealPercentChangedListener percent: $percent"
            }
        })
    }
}
