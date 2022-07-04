package vn.loitp.app.activity.customviews.layout.transformationLayout

import android.os.Bundle
import android.view.View
import com.loitpcore.core.base.BaseFragment
import com.loitpcore.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.frm_transformation_library.*
import vn.loitp.app.R
import vn.loitp.app.activity.customviews.layout.transformationLayout.TransformationMockUtil.getMockPosters
import vn.loitp.app.activity.customviews.layout.transformationLayout.recycler.PosterLineAdapter

class TransformationLibraryFragment : BaseFragment() {
    override fun setLayoutResourceId(): Int {
        return R.layout.frm_transformation_library
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        recyclerView.adapter = PosterLineAdapter().apply {
            addPosterList(getMockPosters())
        }

        fab.setSafeOnClickListener {
            transformationLayout.startTransform()
        }

        layoutMenuCard.setSafeOnClickListener {
            transformationLayout.finishTransform()
            showShortInformation("Play")
        }
    }
}