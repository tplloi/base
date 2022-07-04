package vn.loitp.app.activity.customviews.wwlVideo.utils

import com.loitpcore.utils.util.AppUtils.Companion.appPackageName
import vn.loitp.app.R

object WWLVideoDataset {
    @JvmField
    var datasetItems = ArrayList<DatasetItem>()

    class DatasetItem(id: Int) {
        var id: Int

        @JvmField
        var title: String

        @JvmField
        var subtitle: String

        @JvmField
        var url: String

        init {
            val url = "android.resource://" + appPackageName + "/" + R.raw.vid_bigbuckbunny
            this.id = id
            title = String.format("This is element #%d", id)
            subtitle = "Loitp"
            this.url = url
        }
    }

    init {
        val n = 50
        for (i in 0 until n) {
            datasetItems.add(DatasetItem(i + 1))
        }
    }
}