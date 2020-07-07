package vn.loitp.app.activity.donation

import android.os.Bundle
import com.core.base.BaseFontActivity
import com.core.helper.donate.FrmDonate
import com.core.utilities.LScreenUtil
import vn.loitp.app.R

class DonationActivity : BaseFontActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LScreenUtil.addFragment(activity = activity, containerFrameLayoutIdRes = R.id.flContainer, fragment = FrmDonate(), isAddToBackStack = false)
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_more
    }
}
