package vn.loitp.app.activity.utillsCore

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.core.view.isVisible
import com.loitpcore.annotation.IsAutoAnimation
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.*
import com.loitpcore.core.utilities.statusbar.StatusBarCompat
import com.loitpcore.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_0.lActionBar
import kotlinx.android.synthetic.main.activity_utils_core.*
import vn.loitp.app.R
import java.math.BigDecimal

@LogTag("UtilsCoreActivity")
@IsFullScreen(false)
@IsAutoAnimation(true)
class UtilsCoreActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_utils_core
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        lActionBar.apply {
            LUIUtil.setSafeOnClickListenerElastic(
                view = this.ivIconLeft,
                runnable = {
                    onBackPressed()
                }
            )
            this.ivIconRight?.isVisible = false
            this.viewShadow?.isVisible = true
            this.tvTitle?.text = UtilsCoreActivity::class.java.simpleName
        }
        btSetStatusBarColorAlpha.setSafeOnClickListener {
            StatusBarCompat.setStatusBarColor(this, Color.RED, 50)
        }
        btSetStatusBarColor.setSafeOnClickListener {
            StatusBarCompat.setStatusBarColor(this, Color.RED)
        }
        btTranslucentStatusBar.setSafeOnClickListener {
            StatusBarCompat.translucentStatusBar(this, true)
        }
        btHideSystemUI.setSafeOnClickListener {
            LActivityUtil.hideSystemUI(layoutRootView)
        }
        btShowSystemUI.setSafeOnClickListener {
            LActivityUtil.showSystemUI(layoutRootView)
        }
        btPlayRotate.setSafeOnClickListener {
            LAnimationUtil.playRotate(it, null)
        }
        btSlideInDown.setSafeOnClickListener {
            LAnimationUtil.slideInDown(it)
        }
        btSlideInUp.setSafeOnClickListener {
            LAnimationUtil.slideInUp(it)
        }
        btPlayAnimRandomDuration.setSafeOnClickListener {
            LAnimationUtil.playAnimRandomDuration(it)
        }
        btConvertNumberToStringFormat.setSafeOnClickListener {
            showShortInformation(LConvertUtil.convertNumberToStringFormat(System.currentTimeMillis()))
        }
        btConvertNumberToString.setSafeOnClickListener {
            showShortInformation(LConvertUtil.convertNumberToString(System.currentTimeMillis()))
        }
        btConvertNumberToPercent.setSafeOnClickListener {
            showShortInformation(LConvertUtil.convertNumberToPercent(System.currentTimeMillis()))
        }
        btRoundBigDecimal.setSafeOnClickListener {
            showShortInformation(
                "roundBigDecimal: ${
                    LConvertUtil.roundBigDecimal(
                        BigDecimal(
                            60.123456
                        ), 3
                    )
                }"
            )
        }
        btLDateUtil.setSafeOnClickListener {
            val msg =
                "currentDate: ${LDateUtil.currentDate}" +
                        "\ncurrentYearMonth: ${LDateUtil.currentYearMonth}" +
                        "\ncurrentMonth: ${LDateUtil.currentMonth}" +
                        "\nconvertFormatDate: ${
                            LDateUtil.convertFormatDate(
                                "12/03/2022 01:02:03",
                                "dd/MM/yyyy Hh:mm:ss",
                                "yyyy/MM/dd"
                            )
                        }" +
                        "\nstringToDate: ${
                            LDateUtil.stringToDate(
                                "12/03/2022 01:02:03",
                                "dd/MM/yyyy Hh:mm:ss"
                            )
                        }" +
                        "\ndateToString: ${
                            LDateUtil.dateToString(
                                LDateUtil.getDate(year = 1993, month = 2, day = 4), "yyyy/MM/dd"
                            )
                        }" +
                        "\nformatDatePicker: ${
                            LDateUtil.formatDatePicker(
                                year = 1993, month = 2, day = 4, format = "yyyy/MM/dd"
                            )
                        }" +
                        "\ngetDateWithoutTime: ${LDateUtil.getDateWithoutTime("04/02/1993")}" +
                        "\nconvertDateToTimestamp: ${LDateUtil.convertDateToTimestamp("14-09-2017")}" +
                        "\nzeroTime: ${
                            LDateUtil.zeroTime(
                                LDateUtil.getDate(
                                    year = 1993,
                                    month = 2,
                                    day = 4
                                )
                            )
                        }"
            showDialogMsg(msg)
        }
    }
}
