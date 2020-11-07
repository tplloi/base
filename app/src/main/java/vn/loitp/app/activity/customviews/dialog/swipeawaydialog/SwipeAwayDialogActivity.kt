package vn.loitp.app.activity.customviews.dialog.swipeawaydialog

import android.os.Bundle
import android.view.View
import com.annotation.IsFullScreen
import com.annotation.LogTag
import com.core.base.BaseFontActivity
import kotlinx.android.synthetic.main.activity_dialog_swipe_away.*
import vn.loitp.app.R

//https://github.com/kakajika/SwipeAwayDialog

@LogTag("SwipeAwayDialogActivity")
@IsFullScreen(false)
class SwipeAwayDialogActivity : BaseFontActivity(), View.OnClickListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_dialog_swipe_away
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btShow1.setOnClickListener(this)
        btShow2.setOnClickListener(this)
        btShow3.setOnClickListener(this)
        btShowList.setOnClickListener(this)
        btProgressDialog.setOnClickListener(this)
        btCustomDialog.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            btShow1 -> show(SADialog.KEY_1)
            btShow2 -> show(SADialog.KEY_2)
            btShow3 -> show(SADialog.KEY_3)
            btShowList -> show(SADialog.KEY_4)
            btProgressDialog -> show(SADialog.KEY_5)
            btCustomDialog -> show(SADialog.KEY_6)
        }
    }

    private fun show(key: Int) {
        val saDialog = SADialog()
        val bundle = Bundle()
        bundle.putInt(SADialog.KEY, key)
        saDialog.arguments = bundle
        saDialog.show(supportFragmentManager, SADialog::class.java.simpleName)
    }
}
