package vn.loitp.app.activity.customviews.menu.residemenu

import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFragment
import vn.loitp.app.R

@LogTag("SettingsFragment")
class SettingsFragment : BaseFragment() {

    override fun setLayoutResourceId(): Int {
        return R.layout.reside_menu_setting
    }
}
