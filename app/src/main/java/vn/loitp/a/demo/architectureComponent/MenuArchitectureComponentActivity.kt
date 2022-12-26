package vn.loitp.a.demo.architectureComponent

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.loitp.annotation.IsAutoAnimation
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseFontActivity
import com.loitp.core.utilities.LActivityUtil
import com.loitp.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.a_menu_demo_architecture_component.*
import vn.loitp.R
import vn.loitp.a.demo.architectureComponent.coroutine.CoroutineActivity
import vn.loitp.a.demo.architectureComponent.room.WordActivity
import vn.loitp.a.demo.architectureComponent.vm.ViewModelActivity

@LogTag("MenuArchitectureComponentActivity")
@IsFullScreen(false)
@IsAutoAnimation(true)
class MenuArchitectureComponentActivity : BaseFontActivity(), View.OnClickListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.a_menu_demo_architecture_component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
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
            this.tvTitle?.text = MenuArchitectureComponentActivity::class.java.simpleName
        }
        btCoroutine.setOnClickListener(this)
        btRoom.setOnClickListener(this)
        btViewModel.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v) {
            btCoroutine -> intent = Intent(this, CoroutineActivity::class.java)
            btRoom -> intent = Intent(this, WordActivity::class.java)
            btViewModel -> intent = Intent(this, ViewModelActivity::class.java)
        }
        intent?.let { i ->
            startActivity(i)
            LActivityUtil.tranIn(this)
        }
    }
}