package com.loitpcore.restApi.flickr.model.photoSetGetPhotos

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.loitpcore.core.base.BaseModel

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@Keep
class WrapperPhotosetGetPhotos : BaseModel() {
    @SerializedName("photoset")
    @Expose
    var photoset: Photoset? = null

    @SerializedName("stat")
    @Expose
    var stat: String? = null
}
