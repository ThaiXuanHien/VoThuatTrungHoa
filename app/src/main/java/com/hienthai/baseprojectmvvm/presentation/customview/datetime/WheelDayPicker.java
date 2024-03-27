package com.hienthai.baseprojectmvvm.presentation.customview.datetime;

import static com.hienthai.baseprojectmvvm.presentation.customview.datetime.SingleDateAndTimeConstants.DAYS_PADDING;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;

import com.hienthai.baseprojectmvvm.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WheelDayPicker extends WheelPicker<DateWithLabel> {

    private static final String DAY_FORMAT_PATTERN = "MMMd日EEE";

    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat customDateFormat;
    private int dayCount = DAYS_PADDING;

    private OnDaySelectedListener onDaySelectedListener;

    public WheelDayPicker(Context context) {
        super(context);
    }

    public WheelDayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init() {
        simpleDateFormat = new SimpleDateFormat(DAY_FORMAT_PATTERN, getCurrentLocale());
        simpleDateFormat.setTimeZone(dateHelper.getTimeZone());
    }

    @Override
    public void setCustomLocale(Locale customLocale) {
        super.setCustomLocale(customLocale);
        simpleDateFormat = new SimpleDateFormat(DAY_FORMAT_PATTERN, getCurrentLocale());
        simpleDateFormat.setTimeZone(dateHelper.getTimeZone());
    }

    @Override
    protected DateWithLabel initDefault() {
        return new DateWithLabel(getTodayText(), new Date());
    }

    @NonNull
    private String getTodayText() {
        return getLocalizedString(R.string.picker_today);
    }

    @Override
    protected void onItemSelected(int position, DateWithLabel item) {
        if (onDaySelectedListener != null) {
            onDaySelectedListener.onDaySelected(this, position, item.label, item.date);
        }
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    @Override
    protected List<DateWithLabel> generateAdapterValues(boolean showOnlyFutureDates) {
        final List<DateWithLabel> days = new ArrayList<>();

        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(dateHelper.getTimeZone());
        int startDayOffset = showOnlyFutureDates ? 0 : -1 * dayCount;
        instance.add(Calendar.DATE, startDayOffset - 1);
        for (int i = startDayOffset; i < 0; ++i) {
            instance.add(Calendar.DAY_OF_MONTH, 1);
            Date date = instance.getTime();
            Locale currentLocale = getContext().getResources().getConfiguration().locale;
            if (currentLocale.getLanguage().equals("ja")) {
                days.add(new DateWithLabel(getFormattedValue(date), date));
            } else {
                days.add(new DateWithLabel(getFormattedValue(date)
                        .replace(
                                getFormattedValue(date).substring(0, 3),
                                convertMonthEnglishToJapanese(getFormattedValue(date).substring(0, 3), getContext())
                        )
                        .replace(
                                getFormattedValue(date)
                                        .substring(getFormattedValue(date).length() - 3),
                                convertDayEnglishToJapanese(getFormattedValue(date)
                                        .substring(getFormattedValue(date).length() - 3), getContext())
                        )
                        , date));
            }
        }

        //today
        days.add(new DateWithLabel(getTodayText(), new Date()));

        instance = Calendar.getInstance();
        instance.setTimeZone(dateHelper.getTimeZone());

        for (int i = 0; i < dayCount; ++i) {
            instance.add(Calendar.DATE, 1);
            Date date = instance.getTime();
            Locale currentLocale = getContext().getResources().getConfiguration().locale;
            if (currentLocale.getLanguage().equals("ja")) {
                days.add(new DateWithLabel(getFormattedValue(date), date));
            } else {
                days.add(new DateWithLabel(getFormattedValue(date)
                        .replace(
                                getFormattedValue(date).substring(0, 3),
                                convertMonthEnglishToJapanese(getFormattedValue(date).substring(0, 3), getContext())
                        )
                        .replace(
                                getFormattedValue(date)
                                        .substring(getFormattedValue(date).length() - 3),
                                convertDayEnglishToJapanese(getFormattedValue(date)
                                        .substring(getFormattedValue(date).length() - 3), getContext())
                        )
                        , date));
            }

        }

        return days;
    }

    public String convertMonthEnglishToJapanese(String month, Context context) {
        Locale currentLocale = context.getResources().getConfiguration().locale;
        if (currentLocale.getLanguage().equals("ja")) {
            return month;
        } else {
            return switch (month.toLowerCase()) {
                case "jan" -> "1月";
                case "feb" -> "2月";
                case "mar" -> "3月";
                case "apr" -> "4月";
                case "may" -> "5月";
                case "jun" -> "6月";
                case "jul" -> "7月";
                case "aug" -> "8月";
                case "sep" -> "9月";
                case "oct" -> "10月";
                case "nov" -> "11月";
                case "dec" -> "12月";
                default -> "";
            };
        }
    }

    public String convertDayEnglishToJapanese(String day, Context context) {
        Locale currentLocale = context.getResources().getConfiguration().locale;
        if (currentLocale.getLanguage().equals("ja")) {
            return day;
        } else {
            return switch (day.toLowerCase()) {
                case "mon" -> "月";
                case "tue" -> "火";
                case "wed" -> "水";
                case "thu" -> "木";
                case "fri" -> "金";
                case "sat" -> "土";
                case "sun" -> "日";
                default -> "";
            };
        }
    }

    protected String getFormattedValue(Object value) {
        return getDateFormat().format(value);
    }

    public WheelDayPicker setDayFormatter(SimpleDateFormat simpleDateFormat) {
        simpleDateFormat.setTimeZone(dateHelper.getTimeZone());
        this.customDateFormat = simpleDateFormat;
        updateAdapter();
        return this;
    }

    public void setOnDaySelectedListener(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    public Date getCurrentDate() {
        return convertItemToDate(super.getCurrentItemPosition());
    }

    private SimpleDateFormat getDateFormat() {
        if (customDateFormat != null) {
            return customDateFormat;
        }
        return simpleDateFormat;
    }

    private Date convertItemToDate(int itemPosition) {
        Date date;
        final String itemText = adapter.getItemText(itemPosition);
        final Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTimeZone(dateHelper.getTimeZone());

        int todayPosition = -1;
        final List<DateWithLabel> data = adapter.getData();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).label.equals(getTodayText())) {
                todayPosition = i;
                break;
            }
        }

        if (getTodayText().equals(itemText)) {
            date = todayCalendar.getTime();
        } else {
            todayCalendar.add(Calendar.DAY_OF_YEAR, (itemPosition - todayPosition));
            date = todayCalendar.getTime();
        }
        return date;
    }

    public void setTodayText(DateWithLabel today) {
        final List<DateWithLabel> data = adapter.getData();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).label.equals(getTodayText())) {
                adapter.getData().set(i, today);
                notifyDatasetChanged();
            }
        }
    }

    public interface OnDaySelectedListener {
        void onDaySelected(WheelDayPicker picker, int position, String name, Date date);
    }
}
