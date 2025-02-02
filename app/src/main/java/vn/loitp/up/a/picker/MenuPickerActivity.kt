package vn.loitp.up.a.picker

import android.os.Bundle
import com.loitp.annotation.IsAutoAnimation
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.common.NOT_FOUND
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.ext.setSafeOnClickListenerElastic
import vn.loitp.R
import vn.loitp.databinding.APickerMenuBinding
import vn.loitp.up.a.picker.attachmentManager.AttachmentManagerActivity
import vn.loitp.up.a.picker.gradientColorPickerBar.GradientColorPickerBarActivity
import vn.loitp.up.a.picker.image.ImagePickerActivity
import vn.loitp.up.a.picker.lassi.LassiActivity
import vn.loitp.up.a.picker.number.NumberPickerActivity
import vn.loitp.up.a.picker.shiftColor.ShiftColorPickerActivity
import vn.loitp.up.a.picker.ssImage.MainActivitySSImagePicker
import vn.loitp.up.a.picker.time.TimePickerActivity
import vn.loitp.up.a.picker.unicornFile.UnicornFilePickerActivity

@LogTag("MenuPickerActivity")
@IsFullScreen(false)
@IsAutoAnimation(true)
class MenuPickerActivity : BaseActivityFont() {

    private lateinit var binding: APickerMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = APickerMenuBinding.inflate(layoutInflater)
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
            this.tvTitle?.text = MenuPickerActivity::class.java.simpleName
        }
        binding.btAttachmentManager.setSafeOnClickListener {
            launchActivity(AttachmentManagerActivity::class.java)
        }
        binding.btGradientColorPickerBar.setSafeOnClickListener {
            launchActivity(GradientColorPickerBarActivity::class.java)
        }
        binding.btTimePicker.setSafeOnClickListener {
            launchActivity(TimePickerActivity::class.java)
        }
        binding.btNumberPicker.setSafeOnClickListener {
            launchActivity(NumberPickerActivity::class.java)
        }
        binding.btUnicornFilePickerActivity.setSafeOnClickListener {
            launchActivity(UnicornFilePickerActivity::class.java)
        }
        binding.btSSImagePicker.setSafeOnClickListener {
            launchActivity(MainActivitySSImagePicker::class.java)
        }
        binding.btShiftColorPickerActivity.setSafeOnClickListener {
            launchActivity(ShiftColorPickerActivity::class.java)
        }
        binding.btImagePicker.setSafeOnClickListener {
            launchActivity(ImagePickerActivity::class.java)
        }
        binding.btLassi.setSafeOnClickListener {
            launchActivity(LassiActivity::class.java)
        }
    }
}
