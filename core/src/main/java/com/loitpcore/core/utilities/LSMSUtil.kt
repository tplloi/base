package com.loitpcore.core.utilities

import android.app.Activity
import android.content.Intent
import android.provider.Telephony

class LSMSUtil {
    companion object {
        // https://gist.github.com/mustafasevgi/8c6b638ffd5fca90d45d
        fun sendSMS(activity: Activity?, text: String) {
            if (activity == null) {
                return
            }
            val defaultSmsPackageName =
                Telephony.Sms.getDefaultSmsPackage(activity) // Need to change the build to API 19

            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, text)

            if (defaultSmsPackageName != null)
            // Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName)
            }
            activity.startActivity(sendIntent)
            LActivityUtil.tranIn(activity)
        }
    }
}