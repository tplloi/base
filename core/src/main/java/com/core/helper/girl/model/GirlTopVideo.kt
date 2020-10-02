package com.core.helper.girl.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class GirlTopVideo(
        @SerializedName("cover")
        @Expose
        val cover: String?,

        @SerializedName("link")
        @Expose
        val link: String?,

        @SerializedName("title")
        @Expose
        val title: String?
)