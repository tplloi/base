package vn.loitp.app.activity.customviews.viewPager.autoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.loitp.app.R

class FrmIv : Fragment() {

    companion object {
        fun newInstance(): FrmIv {
            return FrmIv()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frm_iv, container, false)
    }
}