package com.loitp.anim.flySchool

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.loitp.R
import com.loitp.core.ext.loadGlide

class LFlySchoolView : RelativeLayout, ShapeSetter {
    private var ivGift: ImageView? = null
    private var ivAvatar: ImageView? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
    ) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int,
    ) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.l_v_l_fly_school, this)

        ivGift = findViewById(R.id.ivGift)
        ivAvatar = findViewById(R.id.ivAvatar)
    }

    override fun setShape(drawable: Int) {
        ivGift.loadGlide(
            any = drawable,
            resPlaceHolder = R.color.colorPrimary,
            resError = R.color.red,
            transformation = null,
            drawableRequestListener = null
        )
    }

    override fun setShape(
        imgObject: ImgObject,
        drawableRes: Int,
    ) {
        if (ivGift == null || ivAvatar == null) {
            return
        }
        if (drawableRes == 0) {
            try {
                val urlGift = imgObject.url
                ivGift.loadGlide(
                    any = urlGift,
                    resPlaceHolder = R.color.colorPrimary,
                    resError = R.color.red,
                    transformation = null,
                    drawableRequestListener = object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable?>,
                            isFirstResource: Boolean,
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable?>,
                            dataSource: DataSource,
                            isFirstResource: Boolean,
                        ): Boolean {
                            return false
                        }
                    }
                )
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        } else {
            ivGift.loadGlide(
                any = drawableRes,
                resPlaceHolder = R.color.colorPrimary,
                resError = R.color.red,
                transformation = null,
                drawableRequestListener = null
            )
        }
        val urlAvatar = imgObject.avatar
        ivAvatar.loadGlide(
            any = urlAvatar,
            resPlaceHolder = R.color.colorPrimary,
            resError = R.color.red,
            transformation = null,
            drawableRequestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }
            }
        )
    }
}
