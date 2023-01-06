package vn.loitp.a.cv.rv.fitGv

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.utilities.LUIUtil
import com.loitp.views.rv.fitGridView.FitGridView
import kotlinx.android.synthetic.main.a_fit_grid_view.*
import vn.loitp.R

@LogTag("FitGridViewActivity")
@IsFullScreen(false)
class FitGridViewActivityFont : BaseActivityFont() {

    override fun setLayoutResourceId(): Int {
        return R.layout.a_fit_grid_view
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
            this.tvTitle?.text = FitGridViewActivityFont::class.java.simpleName
        }
        gridView.setFitGridAdapter(
            Adapter(this) { pos ->
                showShortInformation("Click $pos")
            }
        )
        btShowInDialog.setSafeOnClickListener {
            showAlert()
        }
        btChangeSize.setSafeOnClickListener {
            changeSize()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
        }
        val gridView = FitGridView(this)
        gridView.numColumns = 3
        gridView.numRows = 4
        gridView.setFitGridAdapter(
            Adapter(this) { pos ->
                showShortInformation("Click $pos")
            }
        )
        builder.setView(gridView)
        builder.show()
    }

    private var counter = 0
    private fun changeSize() {
        when (counter) {
            0 -> {
                changeSize(row = 2, column = 2)
            }
            1 -> {
                changeSize(row = 3, column = 3)
            }
            2 -> {
                changeSize(row = 4, column = 3)
            }
        }
        counter = ++counter % 3
    }

    private fun changeSize(
        row: Int,
        column: Int
    ) {
        gridView.numRows = row
        gridView.numColumns = column
        gridView.update()
    }
}