package vn.loitp.app.activity.animation.flyschool

import android.os.Bundle
import com.core.base.BaseFontActivity
import com.views.animation.flyschool.ImgObject
import com.views.animation.flyschool.PATHS
import kotlinx.android.synthetic.main.activity_animation_fly_school.*
import vn.loitp.app.R

//https://github.com/cipherthinkers/shapeflyer?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=5370
class FlySchoolActivity : BaseFontActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        floatingContainer.addPath(PATHS.S_INVERTED_BOTTOM_RIGHT)
        //mShapeFlyer.addPath(PATHS.S_BOTTOM_LEFT)
        btPlay1.setOnClickListener {
            val imgObject = ImgObject()
            imgObject.avatar = "https://kenh14cdn.com/2016/photo-1-1472659093342.jpg"
            imgObject.url = "https://kenh14cdn.com/2016/photo-1-1472659093342.jpg"
            play1(imgObject)
        }
        btPlay2.setOnClickListener {
            val imgObject = ImgObject()
            imgObject.avatar = "https://kenh14cdn.com/2016/photo-9-1472659093718.jpg"
            imgObject.url = "https://kenh14cdn.com/2016/photo-9-1472659093718.jpg"
            play2(imgObject)
        }
        btPlay3.setOnClickListener {
            play3()
        }
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_animation_fly_school
    }

    override fun onDestroy() {
        floatingContainer?.release()
        super.onDestroy()
    }

    private fun play1(imgObject: ImgObject?) {
        //FlyBluePrint linearBluePrint = new FlyBluePrint(new FPoint(0, 0), FlyPath.getSimpleLinePath(new FPoint(1, 1)));
        //mShapeFlyer.startAnimation(R.drawable.logo, linearBluePrint);
        //mShapeFlyer.startAnimation(R.drawable.l_heart_icon);

        imgObject?.let {
            floatingContainer?.startAnimation(it, 0)
        }
    }

    private fun play2(imgObject: ImgObject?) {
        imgObject?.let {
            floatingContainer.startAnimation(it, R.drawable.logo)
        }
    }

    private fun play3() {
        floatingContainer?.startAnimation(R.drawable.logo)
    }
}