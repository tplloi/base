package vn.loitp.a.demo.tts

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.helper.tts.LTextToSpeechUtil
import com.loitp.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.a_demo_text_to_speech.*
import vn.loitp.R

@LogTag("TextToSpeechActivity")
@IsFullScreen(false)
class TextToSpeechActivityFont : BaseActivityFont(), OnClickListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.a_demo_text_to_speech
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LTextToSpeechUtil.instance.setupTTS()
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
            this.tvTitle?.text = TextToSpeechActivityFont::class.java.simpleName
        }
        btILoveYou.setOnClickListener(this)
        btYouLoveMe.setOnClickListener(this)
        btSpeak.setOnClickListener(this)

        etType.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (etType.text.toString().isEmpty()) {
                    btSpeak.visibility = View.GONE
                } else {
                    btSpeak.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable) {
                // do nothing
            }
        })
    }

    override fun onClick(v: View) {
        when (v) {
            btILoveYou -> LTextToSpeechUtil.instance.speakOut("I love you")
            btYouLoveMe -> LTextToSpeechUtil.instance.speakOut("You love me")
            btSpeak -> LTextToSpeechUtil.instance.speakOut(etType.text.toString())
        }
    }

    override fun onDestroy() {
        LTextToSpeechUtil.instance.destroy()
        super.onDestroy()
    }
}
