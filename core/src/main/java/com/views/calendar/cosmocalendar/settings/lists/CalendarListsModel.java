package com.views.calendar.cosmocalendar.settings.lists;

import com.views.calendar.cosmocalendar.settings.lists.connectedDays.ConnectedDays;
import com.views.calendar.cosmocalendar.settings.lists.connectedDays.ConnectedDaysManager;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

//21.12.2020 try to convert kotlin but failed
public class CalendarListsModel implements CalendarListsInterface {

    private Calendar minDate;

    private Calendar maxDate;

    //Disabled days cannot be selected
    private Set<Long> disabledDays = new TreeSet<>();

    private DisabledDaysCriteria disabledDaysCriteria;

    //Custom connected days for displaying in calendar
    private final ConnectedDaysManager connectedDaysManager = ConnectedDaysManager.getInstance();

    private Set<Long> weekendDays = new HashSet() {{
        add(Calendar.SUNDAY);
    }};

    @Override
    public Calendar getMinDate() {
        return minDate;
    }

    @Override
    public Calendar getMaxDate() {
        return maxDate;
    }

    @Override
    public Set<Long> getDisabledDays() {
        return disabledDays;
    }

    @Override
    public ConnectedDaysManager getConnectedDaysManager() {
        return connectedDaysManager;
    }

    @Override
    public Set<Long> getWeekendDays() {
        return weekendDays;
    }

    @Override
    public DisabledDaysCriteria getDisabledDaysCriteria() {
        return disabledDaysCriteria;
    }

    @Override
    public void setMinDate(Calendar minDate) {
        this.minDate = minDate;
    }

    @Override
    public void setMaxDate(Calendar maxDate) {
        this.maxDate = maxDate;
    }

    @Override
    public void setDisabledDays(Set<Long> disabledDays) {
        this.disabledDays = disabledDays;
    }

    @Override
    public void setWeekendDays(Set<Long> weekendDays) {
        this.weekendDays = weekendDays;
    }

    @Override
    public void setDisabledDaysCriteria(DisabledDaysCriteria criteria) {
        this.disabledDaysCriteria = criteria;
    }

    @Override
    public void addConnectedDays(ConnectedDays connectedDays) {
        connectedDaysManager.addConnectedDays(connectedDays);
    }
}
