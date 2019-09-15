package vn.loitp.app.activity.animation.confetti

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.core.base.BaseFontActivity
import com.views.animation.confetti.CommonConfetti
import com.views.animation.confetti.ConfettiManager
import com.views.animation.confetti.ConfettiSource
import com.views.animation.confetti.ConfettoGenerator
import com.views.animation.confetti.confetto.BitmapConfetto
import com.views.animation.confetti.confetto.Confetto
import loitp.basemaster.R
import java.util.*

//https://github.com/jinatonic/confetti
class FallingConfettiWithListenerActivity : BaseFontActivity(), View.OnClickListener, ConfettoGenerator, ConfettiManager.ConfettiAnimationListener {
    private var goldDark: Int = 0
    private var goldMed: Int = 0
    private var gold: Int = 0
    private var goldLight: Int = 0
    private lateinit var colors: IntArray

    private val activeConfettiManagers = ArrayList<ConfettiManager>()
    private var numConfettiTxt: TextView? = null
    private var numConfettiOnScreen: Int = 0

    private var size: Int = 0
    private var velocitySlow: Int = 0
    private var velocityNormal: Int = 0
    private var bitmap: Bitmap? = null

    private val commonConfetti: CommonConfetti
        get() {
            val centerX = rootView?.width ?: 0 / 2
            val centerY = rootView?.height ?: 0 / 5 * 2
            return CommonConfetti.explosion(rootView, centerX, centerY, colors)
        }

    private val confettiManager: ConfettiManager
        get() {
            val source = ConfettiSource(0, -size, rootView?.width ?: 0, -size)
            return ConfettiManager(this, this, source, rootView)
                    .setVelocityX(0f, velocitySlow.toFloat())
                    .setVelocityY(velocityNormal.toFloat(), velocitySlow.toFloat())
                    .setRotationalVelocity(180f, 90f)
                    .setTouchEnabled(true)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<View>(R.id.generate_confetti_once_btn).setOnClickListener(this)
        findViewById<View>(R.id.generate_confetti_stream_btn).setOnClickListener(this)
        findViewById<View>(R.id.generate_confetti_infinite_btn).setOnClickListener(this)
        findViewById<View>(R.id.generate_confetti_stop_btn).setOnClickListener(this)

        val res = resources
        goldDark = ContextCompat.getColor(activity, R.color.gold_dark)
        goldMed = ContextCompat.getColor(activity, R.color.gold_med)
        gold = ContextCompat.getColor(activity, R.color.gold)
        goldLight = ContextCompat.getColor(activity, R.color.gold_light)
        colors = intArrayOf(goldDark, goldMed, gold, goldLight)

        numConfettiTxt = findViewById<View>(R.id.num_confetti_txt) as TextView
        numConfettiTxt!!.visibility = View.VISIBLE

        size = res.getDimensionPixelSize(R.dimen.big_confetti_size)
        velocitySlow = res.getDimensionPixelOffset(R.dimen.default_velocity_slow)
        velocityNormal = res.getDimensionPixelOffset(R.dimen.default_velocity_normal)

        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.l_heart_icon),
                size, size, false)
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_falling_confetti_from_top
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.generate_confetti_once_btn -> activeConfettiManagers.add(generateOnce())
            R.id.generate_confetti_stream_btn -> activeConfettiManagers.add(generateStream())
            R.id.generate_confetti_infinite_btn -> activeConfettiManagers.add(generateInfinite())
            else -> {
                for (confettiManager in activeConfettiManagers) {
                    confettiManager.terminate()
                }
                activeConfettiManagers.clear()
            }
        }
    }

    private fun generateOnce(): ConfettiManager {
        return confettiManager.setNumInitialCount(20)
                .setEmissionDuration(0)
                .setConfettiAnimationListener(this)
                .animate()
    }

    private fun generateStream(): ConfettiManager {
        return confettiManager.setNumInitialCount(0)
                .setEmissionDuration(3000)
                .setEmissionRate(20f)
                .setConfettiAnimationListener(this)
                .animate()
    }

    private fun generateInfinite(): ConfettiManager {
        return confettiManager.setNumInitialCount(0)
                .setEmissionDuration(ConfettiManager.INFINITE_DURATION)
                .setEmissionRate(20f)
                .setConfettiAnimationListener(this)
                .animate()
    }

    override fun generateConfetto(random: Random): Confetto {
        return BitmapConfetto(bitmap!!)
    }

    override fun onAnimationStart(confettiManager: ConfettiManager) {
        Toast.makeText(this, "Starting confetti animation", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationEnd(confettiManager: ConfettiManager) {
        numConfettiOnScreen = 0
        updateNumConfettiTxt()
        Toast.makeText(this, "Ending confetti animation", Toast.LENGTH_SHORT).show()
    }

    override fun onConfettoEnter(confetto: Confetto) {
        numConfettiOnScreen++
        updateNumConfettiTxt()
    }

    override fun onConfettoExit(confetto: Confetto) {
        numConfettiOnScreen--
        updateNumConfettiTxt()
    }

    private fun updateNumConfettiTxt() {
        numConfettiTxt!!.text = getString(R.string.num_confetti_desc, numConfettiOnScreen)
    }
}
