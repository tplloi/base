package vn.loitp.app.activity.customviews.layout.elasticdragdismisslayout

import android.os.Bundle
import android.widget.TextView

import com.core.base.BaseFontActivity
import com.views.layout.elasticdragdismisslayout.ElasticDragDismissCallback
import com.views.layout.elasticdragdismisslayout.ElasticDragDismissLinearLayout

import vn.loitp.app.R

//https://github.com/Commit451/ElasticDragDismissLayout?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=3098
class ElasticDragDismissLayoutActivity : BaseFontActivity() {
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv = findViewById(R.id.tv)
        val elasticDragDismissLinearLayout = findViewById<ElasticDragDismissLinearLayout>(R.id.draggable_frame)
        elasticDragDismissLinearLayout.addListener(object : ElasticDragDismissCallback() {
            override fun onDrag(elasticOffset: Float, elasticOffsetPixels: Float, rawOffset: Float, rawOffsetPixels: Float) {
                tv.text = "onDrag $elasticOffset - $elasticOffsetPixels - $rawOffset - $rawOffsetPixels"
            }

            override fun onDragDismissed() {
                tv.text = "onDragDismissed"

                //if you are targeting 21+ you might want to finish after transition
                finish()
            }
        })
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_elasticdragdismisslayout
    }
}
