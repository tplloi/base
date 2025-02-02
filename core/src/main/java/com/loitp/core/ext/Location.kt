package com.loitp.core.ext

import android.content.Context
import android.location.Geocoder
import java.io.IOException
import java.util.*

/**
 * Created by Loitp on 06,January,2023
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
fun Context.getCityByLatLon(
    latitude: Double,
    longitude: Double,
    result: ((
        address: String?,
        city: String?,
        state: String?,
        country: String?
    ) -> Unit)? = null
) {
    val gc = Geocoder(this, Locale.getDefault())
    try {
        val addresses = gc.getFromLocation(
            latitude,
            longitude,
            1
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        val firstItem = addresses?.firstOrNull()
        val address =
            firstItem?.getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        val city = firstItem?.locality
        val state = firstItem?.adminArea
        val country = firstItem?.countryName
        result?.invoke(address, city, state, country)
    } catch (e: IOException) {
        e.printStackTrace()
        result?.invoke(null, null, null, null)
    }
}
