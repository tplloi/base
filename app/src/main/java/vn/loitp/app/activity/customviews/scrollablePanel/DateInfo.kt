package vn.loitp.app.activity.customviews.scrollablePanel

import androidx.annotation.Keep
import com.loitpcore.core.base.BaseModel

@Keep
class DateInfo : BaseModel() {
    var date: String? = null
    var week: String? = null
}