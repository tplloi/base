package vn.loitp.app.activity.customviews.switchToggle.toggleButtonGroup

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LActivityUtil
import com.loitpcore.core.utilities.LUIUtil
import com.loitpcore.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_menu_switch_tbg.*
import vn.loitp.app.R

// https://github.com/nex3z/ToggleButtonGroup
@LogTag("TBGMenuActivity")
@IsFullScreen(false)
class MenuTBGActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_menu_switch_tbg
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
            this.tvTitle?.text = MenuTBGActivity::class.java.simpleName
        }

        btnMultiSelectSample.setSafeOnClickListener {
            val intent = Intent(this, TBGMultiSelectActivity::class.java)
            startActivity(intent)
            LActivityUtil.tranIn(this)
        }
        btnSingleSelectSample.setSafeOnClickListener {
            val intent = Intent(this, TBGSingleSelectActivity::class.java)
            startActivity(intent)
            LActivityUtil.tranIn(this)
        }
        btnLabelSample.setSafeOnClickListener {
            val intent = Intent(this, TBGFlowLabelActivity::class.java)
            startActivity(intent)
            LActivityUtil.tranIn(this)
        }
        btnCustomButtonSample.setSafeOnClickListener {
            val intent = Intent(this, TBGCustomButtonActivity::class.java)
            startActivity(intent)
            LActivityUtil.tranIn(this)
        }
    }
}