package vn.loitp.a.func.recolor

import android.graphics.Color
import android.os.Bundle
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.utilities.LUIUtil
import com.simmorsal.recolor_project.ReColor
import kotlinx.android.synthetic.main.a_func_recolor.*
import vn.loitp.R

// https://github.com/SIMMORSAL/ReColor

@LogTag("RecolorActivity")
@IsFullScreen(false)
class RecolorActivityFont : BaseActivityFont() {
    private var imageViewColorSetNumber = 0
    private var textViewColorSetNumber = 0
    private var isStatusBarColorChanged = false
    private var isNavigationBarColorChanged = false

    override fun setLayoutResourceId(): Int {
        return R.layout.a_func_recolor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
        logAListOfColors()
    }

    private fun logAListOfColors() {
        val colorList = ReColor(this).getColorIntArray("E91E63", "1E88E5", 20)
        for (i in colorList.indices) {
            logD(colorList[i].toString())
        }
    }

    private fun setupViews() {
        lActionBar.apply {
            LUIUtil.setSafeOnClickListenerElastic(
                view = this.ivIconLeft,
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.tvTitle?.text = RecolorActivityFont::class.java.simpleName
        }

        // changing space_invaders' color
        imageView.setOnClickListener {
            when (imageViewColorSetNumber) {
                0 -> {
                    LUIUtil.recolor(
                        view = imageView,
                        startColor = Color.RED,
                        endColor = Color.YELLOW,
                        duration = 300
                    )
                    imageViewColorSetNumber = 1
                }
                1 -> {
                    LUIUtil.recolor(
                        view = imageView,
                        startColor = Color.YELLOW,
                        endColor = Color.GREEN,
                        duration = 300
                    )
                    imageViewColorSetNumber = 2
                }
                2 -> {
                    LUIUtil.recolor(
                        view = imageView,
                        startColor = Color.GREEN,
                        endColor = Color.BLUE,
                        duration = 300
                    )
                    imageViewColorSetNumber = 3
                }
                3 -> {
                    LUIUtil.recolor(
                        view = imageView,
                        startColor = Color.BLUE,
                        endColor = Color.RED,
                        duration = 300
                    )
                    imageViewColorSetNumber = 0
                }
            }
        }

        // changing the color on the text view
        textView.setOnClickListener {
            when (textViewColorSetNumber) {
                0 -> {
                    LUIUtil.recolor(
                        view = textView,
                        startColor = Color.RED,
                        endColor = Color.YELLOW,
                        duration = 300
                    )
                    textViewColorSetNumber = 1
                }
                1 -> {
                    LUIUtil.recolor(
                        view = textView,
                        startColor = Color.YELLOW,
                        endColor = Color.BLACK,
                        duration = 300
                    )
                    textViewColorSetNumber = 2
                }
                2 -> {
                    LUIUtil.recolor(
                        view = textView,
                        startColor = Color.BLACK,
                        endColor = Color.WHITE,
                        duration = 300
                    )
                    textViewColorSetNumber = 3
                }
                3 -> {
                    LUIUtil.recolor(
                        view = textView,
                        startColor = Color.WHITE,
                        endColor = Color.RED,
                        duration = 300
                    )
                    textViewColorSetNumber = 0
                }
            }
        }
        linReColorBackground.setOnClickListener {
            LUIUtil.recolor(
                view = it,
                startColor = Color.RED,
                endColor = Color.GREEN,
                duration = 300,
                onReColorFinish = {
                    showSnackBarInfor("onReColorFinish")
                }
            )
        }

        theCardView.setOnClickListener {
            LUIUtil.recolor(
                view = it,
                startColor = Color.RED,
                endColor = Color.GREEN,
                duration = 300,
                onReColorFinish = {
                    showSnackBarInfor("onReColorFinish")
                }
            )
        }

        // changing statusBar color
        linRecolorStatusBar.setOnClickListener {
            isStatusBarColorChanged = if (!isStatusBarColorChanged) {
                // if starting color is null, color will be automatically retrieved from status bar
                // same is true for navigation bar
                LUIUtil.recolorStatusBar(
                    context = this,
                    startColor = null,
                    endColor = Color.RED,
                    duration = 300
                )
                !isStatusBarColorChanged
            } else {
                LUIUtil.recolorStatusBar(
                    context = this,
                    startColor = null,
                    endColor = Color.YELLOW,
                    duration = 300
                )
                !isStatusBarColorChanged
            }
        }

        // changing navigationBar color
        linRecolorNavigationBar.setOnClickListener {
            isNavigationBarColorChanged = if (!isNavigationBarColorChanged) {
                LUIUtil.recolorNavigationBar(
                    context = this,
                    startColor = null,
                    endColor = Color.YELLOW,
                    duration = 300
                )
                !isNavigationBarColorChanged
            } else {
                LUIUtil.recolorNavigationBar(
                    context = this,
                    startColor = null,
                    endColor = Color.GREEN,
                    duration = 300
                )
                !isNavigationBarColorChanged
            }
        }

        // changing both statusBar and navigationBar colors
        linRecolorBoth.setOnClickListener {
            isStatusBarColorChanged = if (!isStatusBarColorChanged) {
                LUIUtil.recolorStatusBar(
                    context = this,
                    startColor = null,
                    endColor = Color.YELLOW,
                    duration = 300
                )
                !isStatusBarColorChanged
            } else {
                LUIUtil.recolorStatusBar(
                    context = this,
                    startColor = null,
                    endColor = Color.RED,
                    duration = 300
                )
                !isStatusBarColorChanged
            }
            isNavigationBarColorChanged = if (!isNavigationBarColorChanged) {
                LUIUtil.recolorNavigationBar(
                    context = this,
                    startColor = null,
                    endColor = Color.YELLOW,
                    duration = 300
                )
                !isNavigationBarColorChanged
            } else {
                LUIUtil.recolorNavigationBar(
                    context = this,
                    startColor = null,
                    endColor = Color.RED,
                    duration = 300
                )
                !isNavigationBarColorChanged
            }
        }

        // pulsing statusBar to an orange color for 2 times
        linPulseStatusBar.setOnClickListener {
            LUIUtil.recolorStatusBarPulse(
                context = this,
                pulseColor = Color.RED,
                pulseSpeed = 200,
                pulseCount = 2
            )
        }

        // pulsing navigationBar to an orange color for 2 times
        linPulseNavigationBar.setOnClickListener {
            LUIUtil.recolorNavigationBarPulse(
                context = this,
                pulseColor = Color.RED,
                pulseSpeed = 200,
                pulseCount = 2
            )
        }

        // pulsing both colors' to an orange color for 5 times really fast
        linPulseBoth.setOnClickListener {
            LUIUtil.recolorStatusBarPulse(
                context = this,
                pulseColor = Color.RED,
                pulseSpeed = 200,
                pulseCount = 2
            )
            LUIUtil.recolorNavigationBarPulse(
                context = this,
                pulseColor = Color.RED,
                pulseSpeed = 200,
                pulseCount = 2
            )
        }
        ib.setOnClickListener {
            LUIUtil.recolor(
                view = it,
                startColor = Color.WHITE,
                endColor = Color.RED,
                duration = 300
            )
        }
    }
}