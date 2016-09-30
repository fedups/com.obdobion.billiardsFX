package com.obdobion.billiardsFX.model;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.obdobion.calendar.CalendarFactory;

/**
 * This class is a filter used to select drills from the save store. See
 * {@link com.obdobion.billiardsFX.model.IDrillMaster} for methods that use this
 * class.
 *
 * @author degreefc
 */
public class SaveStoreFilter
{
    /**
     * This enumerator is used to qualify the least significant part of the date
     * in the filter. If day is used for example then the hour, minutes, and
     * seconds part of the date are ignored.
     *
     * @author degreefc
     *
     */
    public static enum DatePart
    {
        /**
         * Limit the date selection to a specific day
         */
        DAY,
        /**
         * Limit the date selection to a specific hour
         */
        HOUR,
        /**
         * Limit the date selection to a specific minute
         */
        MINUTE,
        /**
         * Limit the date selection to a specific month
         */
        MONTH,
        /**
         * Limit the date selection to a specific second
         */
        SECOND,
        /**
         * Limit the date selection to a specific year
         */
        YEAR
    }

    private boolean       byDay;
    private boolean       byHour;
    private boolean       byMinute;
    private boolean       byMonth;
    private boolean       bySecond;
    private boolean       byYear;
    private LocalDateTime date;
    private Matcher       drillIDMatcher;
    private String        drillIDPattern;
    private Matcher       packageIDMatcher;
    private String        packageIDPattern;

    /**
     * Getter for the drill ID pattern
     *
     * @return the pattern
     */
    public String getDrillIDPattern()
    {
        return drillIDPattern;
    }

    /**
     * Getter for the package ID pattern
     *
     * @return the pattern
     */
    public String getPackageIDPattern()
    {
        return packageIDPattern;
    }

    /**
     * Use this method (from core) to see of the provided header (read from a
     * save store drill) matches the local patterns.
     *
     * @param header is the formatted information from the save store drill
     * @return true if the saved store drill is selected by the local patterns
     */
    public boolean selects(final SaveStoreFilter header)
    {
        if (packageIDMatcher != null)
        {
            packageIDMatcher.reset(header.getPackageIDPattern());
            if (!packageIDMatcher.matches())
                return false;
        }

        if (drillIDMatcher != null)
        {
            drillIDMatcher.reset(header.getDrillIDPattern());
            if (!drillIDMatcher.matches())
                return false;
        }

        if (header.date != null)
        {

            if (byYear || byMonth || byDay || byHour || byMinute || bySecond)
                if (date.getYear() != header.date.getYear())
                    return false;
            if (byMonth || byDay || byHour || byMinute || bySecond)
                if (date.getMonthValue() != header.date.getMonthValue())
                    return false;
            if (byDay || byHour || byMinute || bySecond)
                if (date.getDayOfMonth() != header.date.getDayOfMonth())
                    return false;
            if (byHour || byMinute || bySecond)
                if (date.getHour() != header.date.getHour())
                    return false;
            if (byMinute || bySecond)
                if (date.getMinute() != header.date.getMinute())
                    return false;
            if (bySecond)
                if (date.getSecond() != header.date.getSecond())
                    return false;
        }
        return true;
    }

    /**
     * Use this method to set the date selection parameter. You can limit the
     * select to a specific level with the date part parameter.
     *
     * @param p_date is the formatted date
     * @param p_datePart specifies the least significant part of the date to be
     *            considered.
     * @param p_datePart specifies the least significant part of the date to be
     *            considered.
     * @param p_datePart specifies the least significant part of the date to be
     *            considered.
     * @param p_datePart specifies the least significant part of the date to be
     *            considered.
     * @param p_datePart specifies the least significant part of the date to be
     *            considered.
     * @param p_datePart specifies the least significant part of the date to be
     *            considered.
     * @throws java.text.ParseException when the date parameter is not properly
     *             formatted
     */
    public void setDateSelection(final String p_date, final DatePart p_datePart) throws ParseException
    {
        date = CalendarFactory.modify(p_date);
        byYear = false;
        byMonth = false;
        byDay = false;
        byHour = false;
        byMinute = false;
        bySecond = false;

        switch (p_datePart)
        {
            case YEAR:
                byYear = true;
                break;
            case MONTH:
                byMonth = true;
                break;
            case DAY:
                byDay = true;
                break;
            case HOUR:
                byHour = true;
                break;
            case MINUTE:
                byMinute = true;
                break;
            case SECOND:
                bySecond = true;
                break;
            default:
                break;
        }
    }

    /**
     * Setter for the drill id pattern
     *
     * @param p_drillIDPattern is the {@link java.util.regex.Pattern} to be used
     *            for selection
     */
    public void setDrillSelection(final String p_drillIDPattern)
    {
        drillIDPattern = p_drillIDPattern;
        drillIDMatcher = Pattern.compile(p_drillIDPattern, Pattern.CASE_INSENSITIVE).matcher("");
    }

    /**
     * Setter for the package id pattern
     *
     * @param p_packageIDPattern is the {@link java.util.regex.Pattern} to be
     *            used for selection
     */
    public void setPackageSelection(final String p_packageIDPattern)
    {
        packageIDPattern = p_packageIDPattern;
        packageIDMatcher = Pattern.compile(p_packageIDPattern, Pattern.CASE_INSENSITIVE).matcher("");
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(packageIDPattern).append("/").append(drillIDPattern);
        if (byYear)
            sb.append(" by year");
        else if (byMonth)
            sb.append(" by month in ").append(date.format(DateTimeFormatter.ofPattern("yyyy")));
        else if (byDay)
            sb.append(" by day in ").append(date.format(DateTimeFormatter.ofPattern("yyyy/MM")));
        else if (byHour)
            sb.append(" by hour in ").append(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        else if (byMinute)
            sb.append(" by minute in ").append(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd_HH")));
        else if (bySecond)
            sb.append(" by second in ").append(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm")));
        return sb.toString();
    }
}
