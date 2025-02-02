package com.loitp.views.cal.cosmoCalendar.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.loitp.R;
import com.loitp.views.cal.cosmoCalendar.model.Day;
import com.loitp.views.cal.cosmoCalendar.settings.appearance.AppearanceInterface;
import com.loitp.views.cal.cosmoCalendar.settings.date.DateInterface;
import com.loitp.views.cal.cosmoCalendar.settings.lists.CalendarListsInterface;
import com.loitp.views.cal.cosmoCalendar.settings.lists.DisabledDaysCriteria;
import com.loitp.views.cal.cosmoCalendar.settings.lists.connectedDays.ConnectedDays;
import com.loitp.views.cal.cosmoCalendar.settings.lists.connectedDays.ConnectedDaysManager;
import com.loitp.views.cal.cosmoCalendar.settings.selection.SelectionInterface;
import com.loitp.views.cal.cosmoCalendar.utils.SelectionType;
import com.loitp.views.cal.cosmoCalendar.view.CalendarView;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import kotlin.Suppress;

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@Suppress(names = "unused")
public class CalendarDialog extends Dialog implements View.OnClickListener,
        AppearanceInterface, DateInterface, CalendarListsInterface, SelectionInterface {

    private CalendarView calendarView;

    private OnDaysSelectionListener onDaysSelectionListener;

    @Suppress(names = "unused")
    public CalendarDialog(@NonNull Context context) {
        super(context);
    }

    @Suppress(names = "unused")
    public CalendarDialog(@NonNull Context context, OnDaysSelectionListener onDaysSelectionListener) {
        super(context);
        this.onDaysSelectionListener = onDaysSelectionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.l_v_cosmo_calendar_dlg_calendar);

        Window window = getWindow();

        if (window != null) {
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().getAttributes().gravity = Gravity.TOP;
        }

        initViews();
    }

    private void initViews() {
        //Views
        FrameLayout flNavigationButtonsBar = findViewById(R.id.fl_navigation_buttons_bar);
        ImageView ivCancel = findViewById(R.id.iv_cancel);
        ImageView ivDone = findViewById(R.id.iv_done);
        calendarView = findViewById(R.id.calendar_view);

        Drawable background = calendarView.getBackground();

        if (background instanceof ColorDrawable) {
            flNavigationButtonsBar.setBackgroundColor(((ColorDrawable) background).getColor());
        }

        ivCancel.setOnClickListener(this);
        ivDone.setOnClickListener(this);

    }

    @Suppress(names = "unused")
    public void setOnDaysSelectionListener(OnDaysSelectionListener onDaysSelectionListener) {
        this.onDaysSelectionListener = onDaysSelectionListener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_cancel) {
            cancel();
        } else if (id == R.id.iv_done) {
            doneClick();
        }
    }

    private void doneClick() {
        List<Day> selectedDays = calendarView.getSelectedDays();
        if (onDaysSelectionListener != null) {
            onDaysSelectionListener.onDaysSelected(selectedDays);
        }
        dismiss();
    }


    @Override
    @SelectionType
    public int getSelectionType() {
        return calendarView.getSelectionType();
    }

    @Override
    public void setSelectionType(@SelectionType int selectionType) {
        calendarView.setSelectionType(selectionType);
    }

    @Override
    public int getCalendarBackgroundColor() {
        return calendarView.getCalendarBackgroundColor();
    }

    @Override
    public int getMonthTextColor() {
        return calendarView.getMonthTextColor();
    }

    @Override
    public int getOtherDayTextColor() {
        return calendarView.getOtherDayTextColor();
    }

    @Override
    public int getDayTextColor() {
        return calendarView.getDayTextColor();
    }

    @Override
    public int getWeekendDayTextColor() {
        return calendarView.getWeekendDayTextColor();
    }

    @Override
    public int getWeekDayTitleTextColor() {
        return calendarView.getWeekDayTitleTextColor();
    }

    @Override
    public int getSelectedDayTextColor() {
        return calendarView.getSelectedDayTextColor();
    }

    @Override
    public int getSelectedDayBackgroundColor() {
        return calendarView.getSelectedDayBackgroundColor();
    }

    @Override
    public int getSelectedDayBackgroundStartColor() {
        return calendarView.getSelectedDayBackgroundStartColor();
    }

    @Override
    public int getSelectedDayBackgroundEndColor() {
        return calendarView.getSelectedDayBackgroundEndColor();
    }

    @Override
    public int getCurrentDayTextColor() {
        return calendarView.getCurrentDayTextColor();
    }

    @Override
    public int getCurrentDayIconRes() {
        return calendarView.getCurrentDayIconRes();
    }

    @Override
    public int getCurrentDaySelectedIconRes() {
        return calendarView.getCurrentDaySelectedIconRes();
    }

    @Override
    public int getCalendarOrientation() {
        return calendarView.getCalendarOrientation();
    }

    @Override
    public int getConnectedDayIconRes() {
        return calendarView.getConnectedDayIconRes();
    }

    @Override
    public int getConnectedDaySelectedIconRes() {
        return calendarView.getConnectedDaySelectedIconRes();
    }

    @Override
    public int getConnectedDayIconPosition() {
        return calendarView.getConnectedDayIconPosition();
    }

    @Override
    public int getDisabledDayTextColor() {
        return calendarView.getDisabledDayTextColor();
    }

    @Override
    public int getSelectionBarMonthTextColor() {
        return calendarView.getSelectionBarMonthTextColor();
    }

    @Override
    public int getPreviousMonthIconRes() {
        return calendarView.getPreviousMonthIconRes();
    }

    @Override
    public int getNextMonthIconRes() {
        return calendarView.getNextMonthIconRes();
    }

    @Override
    public boolean isShowDaysOfWeek() {
        return calendarView.isShowDaysOfWeek();
    }

    @Override
    public boolean isShowDaysOfWeekTitle() {
        return calendarView.isShowDaysOfWeekTitle();
    }

    @Override
    public boolean isShowFlBottomSelectionBar() {
        return calendarView.isShowFlBottomSelectionBar();
    }

    @Override
    public void setCalendarBackgroundColor(int calendarBackgroundColor) {
        calendarView.setCalendarBackgroundColor(calendarBackgroundColor);
    }

    @Override
    public void setMonthTextColor(int monthTextColor) {
        calendarView.setMonthTextColor(monthTextColor);
    }

    @Override
    public void setOtherDayTextColor(int otherDayTextColor) {
        calendarView.setOtherDayTextColor(otherDayTextColor);
    }

    @Override
    public void setDayTextColor(int dayTextColor) {
        calendarView.setDayTextColor(dayTextColor);
    }

    @Override
    public void setWeekendDayTextColor(int weekendDayTextColor) {
        calendarView.setWeekendDayTextColor(weekendDayTextColor);
    }

    @Override
    public void setWeekDayTitleTextColor(int weekDayTitleTextColor) {
        calendarView.setWeekDayTitleTextColor(weekDayTitleTextColor);
    }

    @Override
    public void setSelectedDayTextColor(int selectedDayTextColor) {
        calendarView.setSelectedDayTextColor(selectedDayTextColor);
    }

    @Override
    public void setSelectedDayBackgroundColor(int selectedDayBackgroundColor) {
        calendarView.setSelectedDayBackgroundColor(selectedDayBackgroundColor);
    }

    @Override
    public void setSelectedDayBackgroundStartColor(int selectedDayBackgroundStartColor) {
        calendarView.setSelectedDayBackgroundStartColor(selectedDayBackgroundStartColor);
    }

    @Override
    public void setSelectedDayBackgroundEndColor(int selectedDayBackgroundEndColor) {
        calendarView.setSelectedDayBackgroundEndColor(selectedDayBackgroundEndColor);
    }

    @Override
    public void setCurrentDayTextColor(int currentDayTextColor) {
        calendarView.setCurrentDayTextColor(currentDayTextColor);
    }

    @Override
    public void setCurrentDayIconRes(int currentDayIconRes) {
        calendarView.setCurrentDayIconRes(currentDayIconRes);
    }

    @Override
    public void setCurrentDaySelectedIconRes(int currentDaySelectedIconRes) {
        calendarView.setCurrentDaySelectedIconRes(currentDaySelectedIconRes);
    }

    @Override
    public void setCalendarOrientation(int calendarOrientation) {
        calendarView.setCalendarOrientation(calendarOrientation);
    }

    @Override
    public void setConnectedDayIconRes(int connectedDayIconRes) {
        calendarView.setConnectedDayIconRes(connectedDayIconRes);
    }

    @Override
    public void setConnectedDaySelectedIconRes(int connectedDaySelectedIconRes) {
        calendarView.setConnectedDaySelectedIconRes(connectedDaySelectedIconRes);
    }

    @Override
    public void setConnectedDayIconPosition(int connectedDayIconPosition) {
        calendarView.setConnectedDayIconPosition(connectedDayIconPosition);
    }

    @Override
    public void setDisabledDayTextColor(int disabledDayTextColor) {
        calendarView.setDisabledDayTextColor(disabledDayTextColor);
    }

    @Override
    public void setSelectionBarMonthTextColor(int selectionBarMonthTextColor) {
        calendarView.setSelectionBarMonthTextColor(selectionBarMonthTextColor);
    }

    @Override
    public void setPreviousMonthIconRes(int previousMonthIconRes) {
        calendarView.setPreviousMonthIconRes(previousMonthIconRes);
    }

    @Override
    public void setNextMonthIconRes(int nextMonthIconRes) {
        calendarView.setNextMonthIconRes(nextMonthIconRes);
    }

    @Override
    public void setShowDaysOfWeek(boolean showDaysOfWeek) {
        calendarView.setShowDaysOfWeek(showDaysOfWeek);
    }

    @Override
    public void setShowDaysOfWeekTitle(boolean showDaysOfWeekTitle) {
        calendarView.setShowDaysOfWeekTitle(showDaysOfWeekTitle);
    }

    @Override
    public Calendar getMinDate() {
        return calendarView.getMinDate();
    }

    @Override
    public Calendar getMaxDate() {
        return calendarView.getMaxDate();
    }

    @Override
    public void setShowFlBottomSelectionBar(boolean showFlBottomSelectionBar) {
        calendarView.setShowFlBottomSelectionBar(showFlBottomSelectionBar);
    }

    @Override
    public Set<Long> getDisabledDays() {
        return calendarView.getDisabledDays();
    }

    @Override
    public void setMinDate(Calendar minDate) {
        calendarView.setMinDate(minDate);
    }

    @Override
    public void setMaxDate(Calendar maxDate) {
        calendarView.setMaxDate(maxDate);
    }

    @Override
    public ConnectedDaysManager getConnectedDaysManager() {
        return calendarView.getConnectedDaysManager();
    }

    @Override
    public Set<Long> getWeekendDays() {
        return calendarView.getWeekendDays();
    }

    @Override
    public DisabledDaysCriteria getDisabledDaysCriteria() {
        return calendarView.getDisabledDaysCriteria();
    }

    @Override
    public void setDisabledDays(Set<Long> disabledDays) {
        calendarView.setDisabledDays(disabledDays);
    }

    @Override
    public void setWeekendDays(Set<Long> weekendDays) {
        calendarView.setWeekendDays(weekendDays);
    }

    @Override
    public void setDisabledDaysCriteria(DisabledDaysCriteria criteria) {
        calendarView.setDisabledDaysCriteria(criteria);
    }

    @Override
    public void addConnectedDays(ConnectedDays connectedDays) {
        calendarView.addConnectedDays(connectedDays);
    }

    @Override
    public int getFirstDayOfWeek() {
        return calendarView.getFirstDayOfWeek();
    }

    @Override
    public void setFirstDayOfWeek(int firstDayOfWeek) {
        calendarView.setFirstDayOfWeek(firstDayOfWeek);
    }
}
