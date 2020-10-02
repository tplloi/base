package com.core.helper.girl.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class GirlTopUser(
        @SerializedName("avatar")
        @Expose
        val avatar: String?,

        @SerializedName("name")
        @Expose
        val name: String?
)
