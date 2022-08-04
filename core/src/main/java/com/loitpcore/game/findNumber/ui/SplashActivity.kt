package com.loitpcore.game.findNumber.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.daimajia.androidanimations.library.Techniques
import com.loitpcore.R
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LAnimationUtil
import com.loitpcore.core.utilities.LScreenUtil
import com.loitpcore.core.utilities.LUIUtil
import com.loitpcore.core.utilities.LValidateUtil
import com.tombayley.activitycircularreveal.CircularReveal
import kotlinx.android.synthetic.main.l_activity_find_number_splash.*

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@SuppressLint("CustomSplashScreen")
@LogTag("SplashActivity")
@IsFullScreen(true)
class SplashActivity : BaseFontActivity() {

    companion object {
        const val REQUEST_CODE = 69
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.l_activity_find_number_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LScreenUtil.toggleFullscreen(activity = this, isFullScreen = true)
        setupViews()
        goToHome()
    }

    private fun setupViews() {
        LValidateUtil.isValidPackageName()
    }

    private fun goToHome() {
        LUIUtil.setDelay(100) {
            ivFindTheNumber?.visibility = View.VISIBLE
            LAnimationUtil.play(
                view = ivFindTheNumber,
                duration = 500,
                techniques = Techniques.ZoomIn,
                onEnd = {
                    if (!this.isFinishing) {
                        val intent = Intent(this, MenuActivity::class.java)
                        val builder = CircularReveal.Builder(
                            this,
                            progressBar,
                            intent,
                            1000
                        ).apply {
                            revealColor = ContextCompat.getColor(
                                this@SplashActivity,
                                R.color.orange
                            )
                            requestCode = REQUEST_CODE
                        }
                        CircularReveal.presentActivity(builder)
                    }
                }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            onBackPressed()
        }
    }
}
