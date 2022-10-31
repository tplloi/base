package com.loitpcore.core.utilities

import android.app.Application
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.core.content.ContextCompat

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
object LAppResource {

    lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    fun getColor(@ColorRes colorRes: Int): Int = application.let {
        ContextCompat.getColor(it, colorRes)
    }

    fun getColorStateList(@ColorRes colorRes: Int): ColorStateList? = application.let {
        ContextCompat.getColorStateList(it, colorRes)
    }

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable? = application.let {
        ContextCompat.getDrawable(it, drawableRes)
    }

    fun getString(@StringRes stringRes: Int): String = application.getString(stringRes)

    fun getDimenValue(@DimenRes dimenRes: Int): Int {
        return application.resources.getDimensionPixelSize(dimenRes)
    }

    @Suppress("unused")
    fun getArrayString(@ArrayRes arrayRes: Int): Array<String> =
        application.let {
            application.resources.getStringArray(arrayRes)
        }

    @Suppress("unused")
    fun getViewInflater(@LayoutRes layoutRes: Int, container: ViewGroup?): View =
        application.let {
            val inflater =
                application.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(layoutRes, container, false)
        }

    @Suppress("unused")
    fun getViewInflater(@LayoutRes layoutRes: Int, parent: View?, container: ViewGroup?): View =
        application.let {
            val inflater = LayoutInflater.from(parent?.context)
            inflater.inflate(layoutRes, container, false)
        }
}
