package com.loitpcore.views.calendar.cosmoCalendar.adapter.viewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.loitpcore.R
import com.loitpcore.views.calendar.cosmoCalendar.adapter.DaysAdapter
import com.loitpcore.views.calendar.cosmoCalendar.model.Month
import com.loitpcore.views.calendar.cosmoCalendar.settings.SettingsManager
import com.loitpcore.views.calendar.cosmoCalendar.view.MonthView

class MonthHolder(
    itemView: View,
    private val appearanceModel: SettingsManager
) : RecyclerView.ViewHolder(itemView) {

    private val llMonthHeader: LinearLayout = itemView.findViewById(R.id.ll_month_header)
    private val tvMonthName: TextView = itemView.findViewById(R.id.tv_month_name)
    private val viewLeftLine: View = itemView.findViewById(R.id.view_left_line)
    private val viewRightLine: View = itemView.findViewById(R.id.view_right_line)
    private val monthView: MonthView = itemView.findViewById(R.id.month_view)

    fun setDayAdapter(adapter: DaysAdapter?) {
        monthView.adapter = adapter
    }

    fun bind(month: Month) {
        tvMonthName.text = month.monthName
        tvMonthName.setTextColor(appearanceModel.monthTextColor)
        viewLeftLine.visibility =
            if (appearanceModel.calendarOrientation == OrientationHelper.HORIZONTAL) View.INVISIBLE else View.VISIBLE
        viewRightLine.visibility =
            if (appearanceModel.calendarOrientation == OrientationHelper.HORIZONTAL) View.INVISIBLE else View.VISIBLE
        llMonthHeader.setBackgroundResource(if (appearanceModel.calendarOrientation == OrientationHelper.HORIZONTAL) R.drawable.border_top_bottom else 0)
        monthView.initAdapter(month)
    }
}