package vn.loitp.app.activity.api.truyentranhtuan

import android.annotation.SuppressLint
import android.os.Bundle
import com.core.base.BaseFontActivity
import com.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.activity_api_ttt_add_fav_list.*
import vn.loitp.app.R
import vn.loitp.app.activity.api.truyentranhtuan.helper.favlist.AddComicFavListTask
import vn.loitp.app.activity.api.truyentranhtuan.model.comic.Comic

class TTTAPIAddFavListActivity : BaseFontActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        indicatorView.hide()
        btAddVuongPhongLoi.setOnClickListener {
            val comic = Comic()
            comic.date = "29.07.2014"
            comic.url = "http://truyentranhtuan.com/vuong-phong-loi-i/"
            comic.title = "Vương Phong Lôi I"
            addComic(comic)
        }
        btAddLayers.setOnClickListener {
            val comic = Comic()
            comic.date = "28.06.2015"
            comic.url = "http://truyentranhtuan.com/layers/"
            comic.title = "Layers"
            addComic(comic)
        }
        btAddBlackHaze.setOnClickListener {
            val comic = Comic()
            comic.date = "12.03.2017"
            comic.url = "http://truyentranhtuan.com/black-haze/"
            comic.title = "Black Haze"
            addComic(comic)
        }
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_api_ttt_add_fav_list
    }

    private fun addComic(comic: Comic) {
        indicatorView.smoothToShow()
        AddComicFavListTask(context = applicationContext,
                mComic = comic,
                callback = object : AddComicFavListTask.Callback {
                    override fun onAddComicSuccess(mComic: Comic, comicList: List<Comic>) {
                        LUIUtil.printBeautyJson(comicList, textView)
                        showShort("onAddComicSuccess")
                        indicatorView.smoothToHide()
                    }

                    override fun onComicIsExist(mComic: Comic, comicList: List<Comic>) {
                        LUIUtil.printBeautyJson(comicList, textView)
                        showShort("onComicIsExist")
                        indicatorView.smoothToHide()
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onAddComicError() {
                        showShort("onAddComicError")
                        textView.text = "add error"
                        indicatorView.smoothToHide()
                    }
                }).execute()
    }
}