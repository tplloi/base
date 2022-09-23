package vn.loitp.app.activity.customviews.edittext.textWatcher

import android.os.Bundle
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.activity_text_watcher_edittext.*
import vn.loitp.app.R

@LogTag("EditTextTextWatcherActivity")
@IsFullScreen(false)
class EditTextTextWatcherActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_text_watcher_edittext
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
            this.viewShadow?.isVisible = true
            this.tvTitle?.text = EditTextTextWatcherActivity::class.java.simpleName
        }
        var text = ""
        LUIUtil.addTextChangedListener(
            editText = editText,
            delayInMls = 1000, afterTextChanged = { s ->
                text += s + "\n"
                textView.text = text
            }
        )
    }
}
