package vn.loitp.up.a.demo.fragmentNavigation

import android.os.Bundle
import android.view.View
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseFragment
import com.loitp.core.base.OnBackPressedListener
import kotlinx.android.synthetic.main.f_fn_2.*
import vn.loitp.R

@LogTag("fragmentNavigationActivity")
class FN2 : BaseFragment(), OnBackPressedListener {

    private var fragmentNavigationActivity: FragmentNavigationActivity? = null

    override fun setLayoutResourceId(): Int {
        return R.layout.f_fn_2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentNavigationActivity = activity as FragmentNavigationActivity?
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun setupViews() {
        button2.setOnClickListener {
            val navController = fragmentNavigationActivity?.navController
            // new NavOptions.Builder().setExitAnim(R.anim.fade_out);
            navController?.navigate(R.id.action_fn2_to_fn3)
        }
    }

    override fun onResume() {
        super.onResume()
        logD("onResume FN2")
    }

    override fun onBackPressed(): Boolean {
        logD("onBackPressed FN2")
        fragmentNavigationActivity?.popThisFragment()
        return true
    }
}
