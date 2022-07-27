package vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.activity_menu_recycler_tablayout.*
import vn.loitp.app.R
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.autoSelect.RvTabAutoSelectActivityRvTab
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.basic.RvTabDemoBasicActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.customView01.RvTabCustomView01Activity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.customView02.RvTabCustomView02Activity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.imitationLoop.RvTabImitationLoopActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.rtl.RvTabDemoRtlActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.tabOnScreenLimit.RvTabOnScreenLimitActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.tabScrollDisabled.RvTabScrollDisabledActivity
import vn.loitp.app.activity.customviews.recyclerview.recyclerTabLayout.years.RvTabYearsActivity

@LogTag("MenuRecyclerTabLayoutActivity")
@IsFullScreen(false)
class MenuRecyclerTabLayoutActivity : BaseFontActivity(), AdapterView.OnItemClickListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_menu_recycler_tablayout
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
            this.tvTitle?.text = MenuRecyclerTabLayoutActivity::class.java.simpleName
        }
        listView.onItemClickListener = this
        val adapter = ArrayAdapter<String>(this, R.layout.view_item_test_retrofit)

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
}