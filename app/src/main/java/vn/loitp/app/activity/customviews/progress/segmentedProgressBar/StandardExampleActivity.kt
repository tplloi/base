package vn.loitp.app.activity.customviews.progress.segmentedProgressBar

import android.os.Bundle
import com.loitpcore.annotation.IsAutoAnimation
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import kotlinx.android.synthetic.main.activity_standard_example.*
import kotlinx.android.synthetic.main.layout_pager.*
import pt.tornelas.segmentedprogressbar.SegmentedProgressBarListener
import vn.loitp.app.R
import vn.loitp.app.activity.customviews.progress.segmentedProgressBar.pager.PagerAdapter

@LogTag("StandardExampleActivity")
@IsFullScreen(false)
@IsAutoAnimation(false)
class StandardExampleActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_standard_example
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = dataSource()

        viewPager.adapter = PagerAdapter(supportFragmentManager, items)
        spb.viewPager = viewPager

        spb.segmentCount = items.size
        spb.listener = object : SegmentedProgressBarListener {
            override fun onPage(oldPageIndex: Int, newPageIndex: Int) {
                // New page started animating
            }

            override fun onFinished() {
                finish()
            }
        }

        spb.start()

        btnNext.setOnClickListener { spb.next() }
        btnPrevious.setOnClickListener { spb.previous() }
    }
}
