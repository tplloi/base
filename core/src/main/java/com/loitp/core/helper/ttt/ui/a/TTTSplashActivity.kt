package com.loitp.core.helper.ttt.ui.a

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.loitp.R
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.common.NOT_FOUND
import com.loitp.core.ext.isDarkTheme
import com.loitp.core.ext.isValidPackageName
import com.loitp.core.ext.setDelay
import com.loitp.core.ext.tranIn
import com.loitp.databinding.LATttComicSplashBinding
import com.permissionx.guolindev.PermissionX

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@SuppressLint("CustomSplashScreen")
@LogTag("TTTSplashActivity")
@IsFullScreen(false)
class TTTSplashActivity : BaseActivityFont() {
    private lateinit var binding: LATttComicSplashBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LATttComicSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isValidPackageName()

        setupViews()
        checkPermission()
    }

    private fun setupViews() {
        //do sth
    }

    private fun goToHome() {
        setDelay(mls = 1500, runnable = {
            val intent = Intent(this, TTTComicLoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            this.tranIn()
            finish()//correct
        })
    }

    private fun checkPermission() {
        val color = if (isDarkTheme()) {
            Color.WHITE
        } else {
            Color.BLACK
        }
        PermissionX.init(this)
            .permissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
            .setDialogTintColor(color, color)
            .onExplainRequestReason { scope, deniedList, _ ->
                val message = getString(R.string.app_name) + getString(R.string.needs_per)
                scope.showRequestReasonDialog(
                    permissions = deniedList,
                    message = message,
                    positiveText = getString(R.string.allow),
                    negativeText = getString(R.string.deny)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    permissions = deniedList,
                    message = getString(R.string.per_manually_msg),
                    positiveText = getString(R.string.ok),
                    negativeText = getString(R.string.cancel)
                )
            }
            .request { allGranted, _, _ ->
                if (allGranted) {
                    goToHome()
                } else {
                    onBaseBackPressed()
                }
            }
    }
}
