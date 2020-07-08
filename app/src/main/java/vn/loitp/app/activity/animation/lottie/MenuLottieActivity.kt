package vn.loitp.app.activity.animation.lottie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.core.base.BaseFontActivity
import com.core.common.Constants
import com.core.utilities.LUIUtil
import kotlinx.android.synthetic.main.activity_animation_lottie.*
import vn.loitp.app.R
import java.io.IOException
import java.util.*

//https://www.lottiefiles.com/?page=1
class MenuLottieActivity : BaseFontActivity() {
    private val lottieItemList: MutableList<LottieItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val slidePagerAdapter = SlidePagerAdapter()
        viewPager.adapter = slidePagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        LUIUtil.changeTabsFont(tabLayout = tabLayout, fontName = Constants.FONT_PATH)

        sb.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                viewPager.currentItem = seekBar.progress
            }
        })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                sb.progress = i
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })

        prepareData()
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_animation_lottie
    }

    private fun prepareData() {
        getListAssetFiles("lottie")
        for (i in stringList.indices) {
            lottieItemList.add(LottieItem(i.toString() + " - " + stringList[i], "lottie/" + stringList[i]))
        }
        sb.max = lottieItemList.size
        viewPager.adapter?.notifyDataSetChanged()
    }

    private val stringList: MutableList<String> = ArrayList()
    private fun getListAssetFiles(path: String): Boolean {
        val list: Array<String>?
        try {
            list = assets.list(path)
            if (list?.isNotEmpty() == true) {
                // This is a folder
                for (file in list) {
                    if (!getListAssetFiles("$path/$file")) return false else {
                        // This is a file
                        stringList.add(file)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
        return true
    }

    private inner class SlidePagerAdapter : PagerAdapter() {
        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            val lottieItem = lottieItemList[position]
            val inflater = LayoutInflater.from(activity)
            val layout = inflater.inflate(R.layout.item_lottie_view, collection, false) as ViewGroup
            val rl = layout.findViewById<RelativeLayout>(R.id.rl)
            val lottieAnimationView: LottieAnimationView = layout.findViewById(R.id.animationView)
            lottieAnimationView.setAnimation(lottieItem.pathAsset)
            //lottieAnimationView.useHardwareAcceleration();
            //lottieAnimationView.setScale(0.3f);

            //lottieAnimationView.playAnimation();
            lottieAnimationView.loop(true)
            rl.setOnClickListener {
                lottieAnimationView.playAnimation()
            }
            collection.addView(layout)
            return layout
        }

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            collection.removeView(view as View)
        }

        override fun getCount(): Int {
            return lottieItemList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return lottieItemList[position].name
        }
    }
}