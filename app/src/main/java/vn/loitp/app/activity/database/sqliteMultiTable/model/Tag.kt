package vn.loitp.app.activity.database.sqliteMultiTable.model

import androidx.annotation.Keep
import com.loitpcore.core.base.BaseModel

/**
 * Created by Loitp on 15.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */

@Keep
class Tag : BaseModel {
    var id: Int = 0
    var tagName: String? = null

    constructor()

    constructor(tagName: String) {
        this.tagName = tagName
    }

    constructor(id: Int, tagName: String) {
        this.id = id
        this.tagName = tagName
    }
}
