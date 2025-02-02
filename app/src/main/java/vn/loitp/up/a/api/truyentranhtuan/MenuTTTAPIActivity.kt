package vn.loitp.up.a.api.truyentranhtuan

import android.content.Intent
import android.os.Bundle
import com.loitp.annotation.IsAutoAnimation
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.common.NOT_FOUND
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.ext.setSafeOnClickListenerElastic
import com.loitp.core.ext.tranIn
import vn.loitp.R
import vn.loitp.databinding.AMenuTttApiBinding

@LogTag("TTTAPIMenuActivity")
@IsFullScreen(false)
@IsAutoAnimation(true)
class MenuTTTAPIActivity : BaseActivityFont() {
    private lateinit var binding: AMenuTttApiBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AMenuTttApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.lActionBar.apply {
            this.ivIconLeft?.setSafeOnClickListenerElastic(
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.tvTitle?.text = MenuTTTAPIActivity::class.java.simpleName
        }
        binding.btComicList.setSafeOnClickListener {
            val intent = Intent(this, TTTAPIComicListActivity::class.java)
            startActivity(intent)
            this.tranIn()
        }
        binding.btChapList.setSafeOnClickListener {
            val intent = Intent(this, TTTAPIChapListActivity::class.java)
            startActivity(intent)
            this.tranIn()
        }
        binding.btPageList.setSafeOnClickListener {
            val intent = Intent(this, TTTAPIPageListActivity::class.java)
            startActivity(intent)
            this.tranIn()
        }
        binding.btFavList.setSafeOnClickListener {
            val intent = Intent(this, TTTAPIFavListActivity::class.java)
            startActivity(intent)
            this.tranIn()
        }
    }
}
