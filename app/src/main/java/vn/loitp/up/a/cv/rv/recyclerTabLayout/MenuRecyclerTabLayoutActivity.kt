package vn.loitp.up.a.cv.rv.recyclerTabLayout

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.common.NOT_FOUND
import com.loitp.core.ext.setSafeOnClickListenerElastic
import vn.loitp.R
import vn.loitp.databinding.AMenuRvTabLayoutBinding
import vn.loitp.up.a.cv.rv.recyclerTabLayout.autoSelect.RvTabAutoSelectActivityRvTab
import vn.loitp.up.a.cv.rv.recyclerTabLayout.basic.RvTabDemoBasicActivity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.customView01.RvTabCustomView01Activity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.customView02.RvTabCustomView02Activity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.imitationLoop.RvTabImitationLoopActivity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.rtl.RvTabDemoRtlActivity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.tabOnScreenLimit.RvTabOnScreenLimitActivity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.tabScrollDisabled.RvTabScrollDisabledActivity
import vn.loitp.up.a.cv.rv.recyclerTabLayout.years.RvTabYearsActivity

@LogTag("MenuRecyclerTabLayoutActivity")
@IsFullScreen(false)
class MenuRecyclerTabLayoutActivity : BaseActivityFont(), AdapterView.OnItemClickListener {
    private lateinit var binding: AMenuRvTabLayoutBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AMenuRvTabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.lActionBar.apply {
            this.ivIconLeft.setSafeOnClickListenerElastic(runnable = {
                onBaseBackPressed()
            })
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.tvTitle?.text = MenuRecyclerTabLayoutActivity::class.java.simpleName
        }
        binding.listView.onItemClickListener = this
        val adapter = ArrayAdapter<String>(this, R.layout.i_test_retrofit)

        for (demo in Demo.values()) {
            adapter.add(getString(demo.titleResId))
        }

        binding.listView.adapter = adapter
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
