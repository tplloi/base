package vn.loitp.up.a.cv.dragView

import android.os.Bundle
import android.view.View
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.ext.setSafeOnClickListenerElastic
import com.tuanhav95.drag.DragView
import com.tuanhav95.drag.utils.toPx
import vn.loitp.R
import vn.loitp.databinding.ADragViewCustomBinding
import vn.loitp.up.a.cv.dragView.frm.BottomFragment
import vn.loitp.up.a.cv.dragView.frm.NormalTopFragment
import kotlin.math.max
import kotlin.math.min

@LogTag("DragViewCustomActivity")
@IsFullScreen(false)
class DragViewCustomActivity : BaseActivityFont() {
    private lateinit var binding: ADragViewCustomBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ADragViewCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.lActionBar.apply {
            this.ivIconLeft.setSafeOnClickListenerElastic(
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.tvTitle?.text = DragViewCustomActivity::class.java.simpleName
        }

        binding.dragView.setDragListener(object : DragView.DragListener {
            override fun onChangeState(state: DragView.State) {
            }

            override fun onChangePercent(percent: Float) {
                binding.alpha.alpha = 1 - percent

                val shadow = findViewById<View>(R.id.shadow)
                shadow.alpha = percent
            }
        })

        supportFragmentManager.beginTransaction().add(R.id.frameTop, NormalTopFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.frameBottom, BottomFragment()).commit()

        binding.btnMax.setSafeOnClickListener { binding.dragView.maximize() }
        binding.btnMin.setSafeOnClickListener { binding.dragView.minimize() }
        binding.btnClose.setSafeOnClickListener { binding.dragView.close() }

        binding.btnSetHeightMax.setSafeOnClickListener {
            var heightMax = 0
            if (binding.etHeightMax.text?.isNotEmpty() == true) {
                heightMax = binding.etHeightMax.text.toString().toInt()
            }
            heightMax = max(heightMax, 200)
            heightMax = min(heightMax, 400)

            binding.dragView.setHeightMax(heightMax.toPx(), true)
        }
    }
}
