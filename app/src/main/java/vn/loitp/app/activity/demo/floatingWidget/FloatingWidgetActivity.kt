package vn.loitp.app.activity.demo.floatingWidget

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LDialogUtil
import com.loitpcore.core.utilities.LUIUtil
import com.loitpcore.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_demo_floating_widget.*
import vn.loitp.app.R

@LogTag("FloatingWidgetActivity")
@IsFullScreen(false)
class FloatingWidgetActivity : BaseFontActivity() {

    companion object {
        private const val CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_demo_floating_widget
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
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.viewShadow?.isVisible = true
            this.tvTitle?.text = FloatingWidgetActivity::class.java.simpleName
        }
        btNotifyMe.setSafeOnClickListener {
            startService()
        }
    }

    private fun startService() {
        LDialogUtil.showDialog2(
            context = this,
            title = "Permission",
            msg = "Please open overlay permission",
            button1 = "Yes",
            button2 = "No",
            onClickButton1 = {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                //TODO startActivityForResult
                startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION)
            },
            onClickButton2 = {
            }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            // Check if the permission is granted or not.
            startService()
        }
    }
}
