package vn.loitp.app.activity.customviews.dialog.slideimages

import android.os.Bundle
import com.annotation.LayoutId
import com.core.base.BaseFontActivity
import com.core.common.Constants
import com.core.utilities.LDialogUtil
import com.core.utilities.LImageUtil
import com.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_dialog_slide_images.*
import vn.loitp.app.R

@LayoutId(R.layout.activity_dialog_slide_images)
class DialogSlideImagesActivity : BaseFontActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url0 = Constants.URL_IMG_1
        val url1 = Constants.URL_IMG_2
        val url2 = Constants.URL_IMG_3

        val imageList = ArrayList<String>()
        imageList.add(url0)
        imageList.add(url1)
        imageList.add(url2)

        LImageUtil.load(activity, url0, iv0)
        LImageUtil.load(activity, url1, iv1)
        LImageUtil.load(activity, url2, iv2)

        iv0.setSafeOnClickListener {
            LDialogUtil.showDialogSlide(context = activity, index = 0, imgList = imageList, amount = 0.5f, isShowController = true, isShowIconClose = true)
        }
        iv1.setSafeOnClickListener {
            LDialogUtil.showDialogSlide(context = activity, index = 1, imgList = imageList, amount = 0.5f, isShowController = true, isShowIconClose = true)
        }
        iv2.setSafeOnClickListener {
            LDialogUtil.showDialogSlide(context = activity, index = 2, imgList = imageList, amount = 0.5f, isShowController = true, isShowIconClose = true)
        }
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String {
        return javaClass.simpleName
    }

}
