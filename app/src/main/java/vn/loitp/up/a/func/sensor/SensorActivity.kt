package vn.loitp.up.a.func.sensor

import android.content.Context
import android.os.Bundle
import android.view.OrientationEventListener
import android.view.View
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.common.NOT_FOUND
import com.loitp.core.common.URL_IMG
import com.loitp.core.ext.getScreenHeightIncludeNavigationBar
import com.loitp.core.ext.loadGlide
import com.loitp.core.ext.screenWidth
import com.loitp.core.ext.toggleFullscreen
import vn.loitp.databinding.AFuncSensorBinding

@LogTag("SensorActivity")
@IsFullScreen(false)
class SensorActivity : BaseActivityFont() {
    private var orientationListener: OrientationListener? = null
    private lateinit var binding: AFuncSensorBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AFuncSensorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.imageView.loadGlide(any = URL_IMG)
        val w = screenWidth
        val h = w * 9 / 16
        setSizeRelativeLayout(binding.rotateLayout, w, h)
        orientationListener = OrientationListener(this)
    }

    private fun setSizeRelativeLayout(view: View, w: Int, h: Int) {
        val params = view.layoutParams
        params.width = w
        params.height = h
        view.layoutParams = params
    }

    public override fun onStart() {
        orientationListener?.enable()
        super.onStart()
    }

    public override fun onStop() {
        orientationListener?.disable()
        super.onStop()
    }

    private inner class OrientationListener(context: Context?) : OrientationEventListener(context) {
        val rotation0 = 1
        val rotation90 = 2
        val rotation180 = 3
        val rotation270 = 4

        private var rotation = 0

        override fun onOrientationChanged(orientation: Int) {
            if ((orientation < 35 || orientation > 325) && rotation != rotation0) { // PORTRAIT
                rotation = rotation0
                binding.rotateLayout.setAngle(0)
                val w = screenWidth
                val h = w * 9 / 16
                setSizeRelativeLayout(binding.rotateLayout, w, h)
                this@SensorActivity.toggleFullscreen(
                    isFullScreen = false
                )
            } else if (orientation in 146..214 && rotation != rotation180) { // REVERSE PORTRAIT
                rotation = rotation180
            } else if (orientation in 56..124 && rotation != rotation270) { // REVERSE LANDSCAPE
                rotation = rotation270
                binding.rotateLayout.setAngle(90)
                val w = screenWidth
                val h = getScreenHeightIncludeNavigationBar()
                setSizeRelativeLayout(view = binding.rotateLayout, w = w, h = h)
                this@SensorActivity.toggleFullscreen(
                    isFullScreen = true
                )
            } else if (orientation in 236..304 && rotation != rotation90) { // LANDSCAPE
                rotation = rotation90
                binding.rotateLayout.setAngle(-90)
                val w = screenWidth
                val h = getScreenHeightIncludeNavigationBar()
                setSizeRelativeLayout(binding.rotateLayout, w, h)
                this@SensorActivity.toggleFullscreen(
                    isFullScreen = true
                )
            }
        }
    }
}
