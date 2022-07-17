package com.loitpcore.views.viewPager.viewPagerTransformers

import android.view.View

class CubeOutTransformer : BaseTransformer() {

    public override val isPagingEnabled: Boolean
        get() = true

    override fun onTransform(view: View, position: Float) {
        view.pivotX = if (position < 0f) view.width.toFloat() else 0f
        view.pivotY = view.height * 0.5f
        view.rotationY = 90f * position
    }
}
