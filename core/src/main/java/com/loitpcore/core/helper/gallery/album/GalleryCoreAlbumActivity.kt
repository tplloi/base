package com.loitpcore.core.helper.gallery.album

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.loitpcore.R
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.IsSwipeActivity
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.common.Constants
import com.loitpcore.core.helper.gallery.photos.GalleryCorePhotosActivity
import com.loitpcore.core.utilities.LActivityUtil
import com.loitpcore.core.utilities.LDialogUtil
import com.loitpcore.restApi.flickr.FlickrConst
import com.loitpcore.restApi.flickr.model.photoSetGetList.Photoset
import com.loitpcore.restApi.flickr.service.FlickrService
import com.loitpcore.restApi.restClient.RestClient
import com.loitpcore.views.layout.swipeBack.SwipeBackLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import kotlinx.android.synthetic.main.l_activity_flickr_gallery_core_album.*

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("GalleryCoreAlbumActivity")
@IsFullScreen(false)
@IsSwipeActivity(true)
class GalleryCoreAlbumActivity : BaseFontActivity() {
    private var albumAdapter: AlbumAdapter? = null
    private val listPhotoSet = ArrayList<Photoset>()
    private var listRemoveAlbum = ArrayList<String>()

    override fun setLayoutResourceId(): Int {
        return R.layout.l_activity_flickr_gallery_core_album
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {

        intent.getStringArrayListExtra(Constants.KEY_REMOVE_ALBUM_FLICKR_LIST)?.let {
            listRemoveAlbum.addAll(it)
        }

        val animator = SlideInRightAnimator(OvershootInterpolator(1f))
        animator.addDuration = 1000

        recyclerView.apply {
            itemAnimator = animator
            layoutManager = LinearLayoutManager(this@GalleryCoreAlbumActivity)
            setHasFixedSize(true)
        }

        albumAdapter = AlbumAdapter(
            listPhotoSet = listPhotoSet,
            callback = object : AlbumAdapter.Callback {
                override fun onClick(pos: Int) {
                    val intent =
                        Intent(this@GalleryCoreAlbumActivity, GalleryCorePhotosActivity::class.java)
                    intent.apply {
                        putExtra(Constants.SK_PHOTOSET_ID, listPhotoSet[pos].id)
                        putExtra(Constants.SK_PHOTOSET_SIZE, listPhotoSet[pos].photos)
                        startActivity(this)
                        LActivityUtil.tranIn(this@GalleryCoreAlbumActivity)
                    }
                }

                override fun onLongClick(pos: Int) {
                }
            }
        )

        albumAdapter?.let {
//            val animAdapter = AlphaInAnimationAdapter(it)
//            val animAdapter = ScaleInAnimationAdapter(it)
            val animAdapter = SlideInBottomAnimationAdapter(it)
//            val animAdapter = SlideInLeftAnimationAdapter(it)
//            val animAdapter = SlideInRightAnimationAdapter(it)

            animAdapter.setDuration(1000)
//          animAdapter.setInterpolator(OvershootInterpolator())
            animAdapter.setFirstOnly(true)
            recyclerView.adapter = animAdapter
        }

//        LUIUtil.setPullLikeIOSVertical(this)

        swipeBackLayout.setSwipeBackListener(object : SwipeBackLayout.OnSwipeBackListener {
            override fun onViewPositionChanged(
                mView: View?,
                swipeBackFraction: Float,
                swipeBackFactor: Float
            ) {
            }

            override fun onViewSwipeFinished(mView: View?, isEnd: Boolean) {
                if (isEnd) {
                    finish()//correct
                    LActivityUtil.transActivityNoAnimation(this@GalleryCoreAlbumActivity)
                }
            }
        })
        getListPhotosets()
    }

    private fun getListPhotosets() {
        LDialogUtil.showProgress(progressBar)
        val service = RestClient.createService(FlickrService::class.java)
        val method = FlickrConst.METHOD_PHOTOSETS_GETLIST
        val apiKey = FlickrConst.API_KEY
        val userID = FlickrConst.USER_KEY
        val page = 1
        val perPage = 500
        val primaryPhotoExtras = FlickrConst.PRIMARY_PHOTO_EXTRAS_0
        val format = FlickrConst.FORMAT
        val noJsonCallBack = FlickrConst.NO_JSON_CALLBACK

        compositeDisposable.add(
            service.getListPhotoset(
                method = method,
                apiKey = apiKey,
                userId = userID,
                page = page,
                perPage = perPage,
                primaryPhotoExtras = primaryPhotoExtras,
                format = format,
                noJsonCallback = noJsonCallBack
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ wrapperPhotoSetGetList ->
                    wrapperPhotoSetGetList?.photosets?.photoset?.let {
                        listPhotoSet.addAll(it)
                    }
                    listRemoveAlbum.let {
                        for (i in it.indices.reversed()) {
                            for (j in listPhotoSet.indices.reversed()) {
                                if (it[i] == listPhotoSet[j].id) {
                                    listPhotoSet.removeAt(j)
                                }
                            }
                        }
                    }

                    // sort date
//                    listPhotoSet.sortWith { o1, o2 ->
//                        java.lang.Long.valueOf(o2.dateUpdate).compareTo(java.lang.Long.valueOf(o1.dateUpdate))
//                    }
                    // random
                    listPhotoSet.shuffle()

                    updateAllViews()
                    LDialogUtil.hideProgress(progressBar)
                }, { throwable ->
                    handleException(throwable)
                    LDialogUtil.hideProgress(progressBar)
                })
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAllViews() {
        albumAdapter?.notifyDataSetChanged()
    }

}
