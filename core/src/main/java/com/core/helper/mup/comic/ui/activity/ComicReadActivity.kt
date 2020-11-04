package com.core.helper.mup.comic.ui.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.R
import com.annotation.IsFullScreen
import com.annotation.IsShowAdWhenExit
import com.annotation.IsSwipeActivity
import com.annotation.LogTag
import com.core.base.BaseApplication
import com.core.base.BaseFontActivity
import com.core.helper.mup.comic.adapter.ChapterDetailAdapter
import com.core.helper.mup.comic.model.Chap
import com.core.helper.mup.comic.viewmodel.ComicViewModel
import com.core.utilities.LActivityUtil
import com.core.utilities.LAnimationUtil
import com.core.utilities.LUIUtil
import com.daimajia.androidanimations.library.Techniques
import com.google.ads.interactivemedia.v3.internal.fa
import com.interfaces.CallbackRecyclerView
import com.views.layout.swipeback.SwipeBackLayout
import com.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.l_activity_comic_read.*
import kotlinx.android.synthetic.main.l_activity_comic_read.tvNoData
import kotlinx.android.synthetic.main.l_frm_comic_home.*

@LogTag("loitppComicActivity")
@IsFullScreen(false)
@IsShowAdWhenExit(true)
@IsSwipeActivity(true)
class ComicReadActivity : BaseFontActivity() {

    companion object {
        const val KEY_CHAP = "KEY_CHAP"
    }

    private var chap: Chap? = null
    private var comicViewModel: ComicViewModel? = null

    private var concatAdapter = ConcatAdapter()
    private var chapterDetailAdapter = ChapterDetailAdapter()

    override fun setLayoutResourceId(): Int {
        return R.layout.l_activity_comic_read
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupData()
        setupViews()
        setupViewModels()

        comicViewModel?.getChapterDetail(chapId = chap?.id)
    }

    private fun setupData() {
        intent?.getSerializableExtra(KEY_CHAP)?.let {
            if (it is Chap) {
                chap = it
            }
        }
    }

    private fun setupViews() {
        swipeBackLayout.setSwipeBackListener(object : SwipeBackLayout.OnSwipeBackListener {
            override fun onViewPositionChanged(mView: View, swipeBackFraction: Float, SWIPE_BACK_FACTOR: Float) {
            }

            override fun onViewSwipeFinished(mView: View, isEnd: Boolean) {
                if (isEnd) {
                    finish()
                    LActivityUtil.transActivityNoAnimation(this@ComicReadActivity)
                }
            }
        })
        chapterDetailAdapter = ChapterDetailAdapter()
        chapterDetailAdapter.onClickRoot = { chapterComicsDetail ->
            logD("onClickRoot chapterComicsDetail " + BaseApplication.gson.toJson(chapterComicsDetail))
            //TODO zoom
        }
        concatAdapter.addAdapter(chapterDetailAdapter)

        rvComicRead.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvComicRead.adapter = concatAdapter

        LUIUtil.setScrollChange(
                recyclerView = rvComicRead,
                callbackRecyclerView = object : CallbackRecyclerView {
                    override fun onTop() {
                        fabPrevious.visibility = View.VISIBLE
                        LAnimationUtil.play(view = fabPrevious, techniques = Techniques.SlideInUp)
                    }

                    override fun onBottom() {
                        fabNext.visibility = View.VISIBLE
                        LAnimationUtil.play(view = fabNext, techniques = Techniques.SlideInUp)
                    }

                    override fun onScrolled(isScrollDown: Boolean) {
                        if (isScrollDown) {
                            LAnimationUtil.play(view = layoutControl, techniques = Techniques.SlideOutUp)
                            LAnimationUtil.play(view = fabPrevious, techniques = Techniques.SlideOutDown)
                        } else {
                            LAnimationUtil.play(view = layoutControl, techniques = Techniques.SlideInDown)
                            LAnimationUtil.play(view = fabNext, techniques = Techniques.SlideOutDown)
                        }
                    }
                })
        ivBack.setSafeOnClickListener {
            onBackPressed()
        }
        ivMenu.setSafeOnClickListener {
            //TODO loitpp iplm
        }
        fabNext.setSafeOnClickListener {
            //TODO loitpp iplm
        }
        fabPrevious.setSafeOnClickListener {
            goToPreviousChap()
        }
    }

    private fun setupViewModels() {
        comicViewModel = getViewModel(ComicViewModel::class.java)
        comicViewModel?.let { vm ->
            vm.chapterDetailActionLiveData.observe(this, { actionData ->
                val isDoing = actionData.isDoing
                val isSuccess = actionData.isSuccess

                logD("<<<chapterDetailActionLiveData observe isDoing $isDoing, isSuccess $isSuccess")

                if (isDoing == true) {
                    indicatorView.smoothToShow()
                } else {
                    indicatorView.smoothToHide()
                }

                if (isDoing == false && isSuccess == true) {
                    val listChapterComicDetails = actionData.data?.chapterComicsDetails
                    if (listChapterComicDetails.isNullOrEmpty()) {
                        tvNoData.visibility = View.VISIBLE
                        rvComicRead.visibility = View.GONE
                    } else {
                        tvNoData.visibility = View.GONE
                        rvComicRead.visibility = View.VISIBLE
                        tvTitle.text = actionData.data?.title

                        chapterDetailAdapter.setListData(listChap = listChapterComicDetails)
                    }
                }
            })
        }
    }

    private fun goToPreviousChap() {
        val prevChap = comicViewModel?.chapterDetailActionLiveData?.value?.data?.prevChap
        if (prevChap == null) {
            showLongInformation(getString(R.string.no_data))
        } else {

        }
    }
}
