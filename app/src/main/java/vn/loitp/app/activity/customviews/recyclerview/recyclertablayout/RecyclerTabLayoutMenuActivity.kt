package vn.loitp.app.activity.customviews.recyclerview.recyclertablayout

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.annotation.LayoutId
import com.core.base.BaseFontActivity
import kotlinx.android.synthetic.main.activity_recycler_tablayout_menu.*
import vn.loitp.app.R
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.autoselect.RvTabAutoSelectActivityRvTab
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.basic.RvTabDemoBasicActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.customview01.RvTabCustomView01Activity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.customview02.RvTabCustomView02Activity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.imitationloop.RvTabImitationLoopActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.rtl.RvTabDemoRtlActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.tabonscreenlimit.RvTabOnScreenLimitActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.tabscrolldisabled.RvTabScrollDisabledActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclertablayout.years.RvTabYearsActivity

@LayoutId(R.layout.activity_recycler_tablayout_menu)
class RecyclerTabLayoutMenuActivity : BaseFontActivity(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView.onItemClickListener = this
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        for (demo in Demo.values()) {
            adapter.add(getString(demo.titleResId))
        }

        listView.adapter = adapter
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (val demo = Demo.values()[position]) {
            Demo.BASIC -> RvTabDemoBasicActivity.startActivity(this, demo)
            Demo.AUTO_SELECT -> RvTabAutoSelectActivityRvTab.startActivity(this, demo)
            Demo.CUSTOM_VIEW01 -> RvTabCustomView01Activity.startActivity(this, demo)
            Demo.CUSTOM_VIEW02 -> RvTabCustomView02Activity.startActivity(this, demo)
            Demo.YEARS -> RvTabYearsActivity.startActivity(this, demo)
            Demo.IMITATION_LOOP -> RvTabImitationLoopActivity.startActivity(this, demo)
            Demo.RTL -> RvTabDemoRtlActivity.startActivity(this, demo)
            Demo.TAB_SCROLL_DISABLED -> RvTabScrollDisabledActivity.startActivity(this, demo)
            Demo.TAB_ON_SCREEN_LIMIT -> RvTabOnScreenLimitActivity.startActivity(this, demo)
        }
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

}
