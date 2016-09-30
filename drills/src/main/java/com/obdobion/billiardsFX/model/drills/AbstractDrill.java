package com.obdobion.billiardsFX.model.drills;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.Tag;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * Abstract AbstractDrill class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
abstract public class AbstractDrill implements IDrill
{
    private static final String DISK_COMMA = "!#!";
    final static Logger         logger     = LoggerFactory.getLogger(IDrill.class);

    /**
     * <p>
     * externalize.
     * </p>
     *
     * @param b a boolean.
     * @return a {@link java.lang.String} object.
     */
    protected static String externalize(final boolean b)
    {
        return b
                ? ""
                : "F";
    }

    /**
     * <p>
     * internalizeBoolean.
     * </p>
     *
     * @param bStr a {@link java.lang.String} object.
     * @return a boolean.
     */
    protected static boolean internalizeBoolean(final String bStr)
    {
        return bStr.length() == 0;
    }

    /**
     * <p>
     * readHeader.
     * </p>
     *
     * @param externalRepresentation a {@link java.lang.String} object.
     * @return a {@link com.obdobion.billiardsFX.model.SaveStoreFilter} object.
     * @throws java.text.ParseException if any.
     */
    static public SaveStoreFilter readHeader(final String externalRepresentation) throws ParseException
    {
        final SaveStoreFilter header = new SaveStoreFilter();
        final String[] fields = externalRepresentation.split(",");
        try
        {
            header.setDateSelection(fields[0], SaveStoreFilter.DatePart.SECOND);
        } catch (final ParseException e)
        {
            throw new ParseException(e.getMessage(), 0);
        }
        header.setPackageSelection(fields[1]);
        header.setDrillSelection(fields[2]);
        return header;
    }

    private Context                     context;
    LocalDateTime                       date;
    IDemonstration                      demonstration;
    private String                      drillId;
    String                              note;
    List<WeakReference<IDrillObserver>> observers = new ArrayList<>();
    private String                      packageId;
    private final List<Tag>             tags      = new ArrayList<>();
    TargetScores                        targetScores;
    String                              titleRef;
    /*
     * Fields that are used in the database.
     */
    private int                         userUid;
    private int                         version;
    private int                         uid;

    /**
     * <p>
     * Constructor for AbstractDrill.
     * </p>
     */
    public AbstractDrill()
    {
        date = DrillMaster.currentDateTime();
    }

    /** {@inheritDoc} */
    @Override
    public void addObserver(final IDrillObserver observer)
    {
        for (final WeakReference<IDrillObserver> ref : observers)
        {
            final IDrillObserver obs = ref.get();
            if (obs == observer)
                return;
        }
        observers.add(new WeakReference<>(observer));
    }

    /** {@inheritDoc} */
    @Override
    public Context getContext()
    {
        return context;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>date</code>.
     * </p>
     */
    @Override
    public LocalDateTime getDate()
    {
        return date;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>demonstration</code>.
     * </p>
     */
    @Override
    public IDemonstration getDemonstration()
    {
        return demonstration;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>drillId</code>.
     * </p>
     */
    @Override
    public String getDrillId()
    {
        return drillId;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>note</code>.
     * </p>
     */
    @Override
    public String getNote()
    {
        return note;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>packageId</code>.
     * </p>
     */
    @Override
    public String getPackageId()
    {
        return packageId;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>tags</code>.
     * </p>
     */
    @Override
    public List<Tag> getTags()
    {
        return tags;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Getter for the field <code>titleRef</code>.
     * </p>
     */
    @Override
    public String getTitleRef()
    {
        return titleRef;
    }

    /** {@inheritDoc} */
    @Override
    public int getUid()
    {
        return uid;
    }

    /** {@inheritDoc} */
    @Override
    public int getUserUid()
    {
        return userUid;
    }

    /** {@inheritDoc} */
    @Override
    public int getVersion()
    {
        return version;
    }

    /**
     * <p>
     * noteFromDiskFormat.
     * </p>
     *
     * @param diskNote a {@link java.lang.String} object.
     */
    protected void noteFromDiskFormat(final String diskNote)
    {
        if (diskNote == null || diskNote.trim().length() == 0 || diskNote.equals(DISK_COMMA))
        {
            setNote(null);
            return;
        }
        setNote(diskNote.replaceAll(DISK_COMMA, ","));
    }

    /**
     * <p>
     * noteToDiskFormat.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    protected String noteToDiskFormat()
    {
        if (getNote() == null)
            return DISK_COMMA;
        return getNote().replaceAll(",", DISK_COMMA);
    }

    /**
     * <p>
     * notifyDrillOverObservers.
     * </p>
     */
    public void notifyDrillOverObservers()
    {
        logger.debug("notifying drill over observers");
        final List<WeakReference<IDrillObserver>> removeThese = new ArrayList<>();

        for (final WeakReference<IDrillObserver> ref : observers)
        {
            final IDrillObserver obs = ref.get();
            if (obs != null)
            {
                obs.drillOverEvent(this);
            } else
                removeThese.add(ref);
        }
        observers.removeAll(removeThese);
    }

    /**
     * <p>
     * notifyDrillStartObservers.
     * </p>
     */
    public void notifyDrillStartObservers()
    {
        logger.debug("notifying drill start observers");
        final List<WeakReference<IDrillObserver>> removeThese = new ArrayList<>();

        for (final WeakReference<IDrillObserver> ref : observers)
        {
            final IDrillObserver obs = ref.get();
            if (obs != null)
            {
                obs.drillStartEvent(this);
            } else
                removeThese.add(ref);
        }
        observers.removeAll(removeThese);
    }

    /**
     * <p>
     * notifyScoreChangedObservers.
     * </p>
     */
    public void notifyScoreChangedObservers()
    {
        logger.debug("notifying score change observers");
        final List<WeakReference<IDrillObserver>> removeThese = new ArrayList<>();

        for (final WeakReference<IDrillObserver> ref : observers)
        {
            final IDrillObserver obs = ref.get();
            if (obs != null)
            {
                obs.scoreChangedEvent(this);
            } else
                removeThese.add(ref);
        }
        observers.removeAll(removeThese);
    }

    /** {@inheritDoc} */
    @Override
    public String[] readFrom(final String externalRepresentation) throws ParseException
    {
        int f = 0;

        final String[] fields = externalRepresentation.split(",");
        try
        {
            date = CalendarFactory.modify(fields[f++]);
        } catch (final ParseException e)
        {
            throw new ParseException(e.getMessage(), f);
        }
        packageId = fields[f++];
        return Arrays.copyOfRange(fields, f, fields.length);
    }

    /**
     * floating point scores are stored as integers. This is used to convert the
     * human readable score to an int.
     *
     * @return a int.
     */
    protected int scoreDivisor()
    {
        return 1;
    }

    /**
     * <p>
     * scoreType.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.ScoreType} object.
     */
    public ScoreType scoreType()
    {
        return targetScores.getScoreType();
    }

    /** {@inheritDoc} */
    @Override
    public void setContext(final Context p_context)
    {
        context = p_context;
    }

    /** {@inheritDoc} */
    @Override
    public void setDate(final LocalDateTime p_date)
    {
        this.date = p_date;
    }

    /** {@inheritDoc} */
    @Override
    public void setDemonstration(final IDemonstration demo)
    {
        demonstration = demo;
    }

    /** {@inheritDoc} */
    @Override
    public void setDrillId(final String p_drillId)
    {
        this.drillId = p_drillId;
    }

    /** {@inheritDoc} */
    @Override
    public void setNote(final String p_note)
    {
        this.note = p_note;
    }

    /** {@inheritDoc} */
    @Override
    public void setPackageId(final String pkgID)
    {
        packageId = pkgID;
    }

    /** {@inheritDoc} */
    @Override
    public void setTargetScores(final TargetScores p_targetScores)
    {
        targetScores = p_targetScores;
    }

    /** {@inheritDoc} */
    @Override
    public void setTitleRef(final String p_titleRef)
    {
        this.titleRef = p_titleRef;
    }

    /** {@inheritDoc} */
    @Override
    public void setUid(final int uid)
    {
        this.uid = uid;
    }

    /** {@inheritDoc} */
    @Override
    public void setUserUid(final int userUid)
    {
        this.userUid = userUid;
    }

    /** {@inheritDoc} */
    @Override
    public void setVersion(final int version)
    {
        this.version = version;
    }

    /** {@inheritDoc} */
    @Override
    public SkillLevel skillLevelForScore(final int averageScore)
    {
        return targetScores.skillLevelForScore(averageScore);
    }

    /**
     * <p>
     * start.
     * </p>
     */
    public void start()
    {
        notifyDrillStartObservers();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * tagIt.
     * </p>
     */
    @Override
    public void tagIt(final Tag... newTags)
    {
        for (final Tag tag : newTags)
            tags.add(tag);
    }

    /** {@inheritDoc} */
    @Override
    public int targetScore(final SkillLevel level)
    {
        if (!targetScores.containsKey(level))
            return 0;
        return targetScores.get(level);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(CalendarFactory.asJSON(getDate()));
        sb.append(" ").append(packageId);
        sb.append("/").append(drillId);
        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void writeTo(final StringBuilder sb)
    {
        sb.append(CalendarFactory.asJSON(getDate()));
        sb.append(",");
        sb.append(packageId.toLowerCase());
        sb.append(",");
        sb.append(getDrillId());
        sb.append(",");
        sb.append(gameDataVersion()); // version
        sb.append(",");
        sb.append(gameData());
        sb.append(",");
        sb.append(noteToDiskFormat());
    }

}
