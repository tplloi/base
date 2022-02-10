package vn.loitp.app.activity.api.truyentranhtuan

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import com.annotation.IsFullScreen
import com.annotation.LogTag
import com.core.base.BaseFontActivity
import com.core.helper.ttt.helper.ComicUtils
import com.core.helper.ttt.model.comictype.ComicType
import com.core.helper.ttt.viewmodel.TTTViewModel
import com.core.utilities.LDialogUtil
import com.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.activity_api_ttt_comic_list.*
import vn.loitp.app.R

@LogTag("TTTAPIComicListActivity")
@IsFullScreen(false)
class TTTAPIComicListActivity : BaseFontActivity() {
    private var tttViewModel: TTTViewModel? = null
    private var comicTypeList = ArrayList<ComicType>()

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_api_ttt_comic_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
        setupViewModels()
    }

    private fun setupViews() {
        LDialogUtil.hideProgress(progressBar)
        comicTypeList.addAll(ComicUtils.comicTypeList)

        btSelect.setOnClickListener {
            showDialogSelect()
        }
    }

    private fun setupViewModels() {
        tttViewModel = getViewModel(TTTViewModel::class.java)
        tttViewModel?.let { vm ->
            vm.comicTypeLiveEvent.observe(this) { comicType ->
                logD("loitpp>>>comicTypeLiveEvent comicType ${comicType.url}")
                vm.getListComic(link = comicType.url)
            }
            vm.listComicActionLiveData.observe(this) { actionData ->
                val isDoing = actionData.isDoing
                val isSuccess = actionData.isSuccess

                if (isDoing == true) {
                    LDialogUtil.showProgress(progressBar)
                } else {
                    LDialogUtil.hideProgress(progressBar)
                    if (isSuccess == true) {
                        val listComic = actionData.data
                        listComic?.let {
                            LUIUtil.printBeautyJson(o = it, textView = textView)
                        }
                    }
                }
            }
        }
    }

    private fun showDialogSelect() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn thể loại:")
        val items = arrayOfNulls<String>(comicTypeList.size)
        for (i in comicTypeList.indices) {
            items[i] = comicTypeList[i].type
        }
        builder.setItems(items) { _: DialogInterface?, position: Int ->
            tttViewModel?.setComicType(comicTypeList[position])
            tttViewModel?.setComicType(comicTypeList[position + 1])
            tttViewModel?.setComicType(comicTypeList[position + 2])
        }
        val dialog = builder.create()
        dialog.show()
    }
}
