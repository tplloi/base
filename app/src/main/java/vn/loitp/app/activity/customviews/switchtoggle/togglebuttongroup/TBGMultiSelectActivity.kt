package vn.loitp.app.activity.customviews.switchtoggle.togglebuttongroup

import android.os.Bundle
import com.annotation.LayoutId
import com.core.base.BaseFontActivity
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup
import kotlinx.android.synthetic.main.activity_switch_tbg_multi_select.*
import vn.loitp.app.R

@LayoutId(R.layout.activity_switch_tbg_multi_select)
class TBGMultiSelectActivity : BaseFontActivity() {
    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        groupWeekdays.setOnCheckedChangeListener { group: MultiSelectToggleGroup, _: Int, _: Boolean ->
            logD("onCheckedStateChanged(): group.getCheckedIds() = " + group.checkedIds)
        }
    }

}
