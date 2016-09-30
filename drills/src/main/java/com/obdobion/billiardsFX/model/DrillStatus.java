package com.obdobion.billiardsFX.model;

import java.time.LocalDateTime;

import com.obdobion.billiardsFX.model.drills.SkillLevel;

/**
 * <p>
 * DrillStatus class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrillStatus
{
    private SkillLevel    currentSkillLevel;
    private int           playCount;
    /*
     * database fields
     */
    private int           uid;
    private int           version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int           userUid;

    private String        packageId;
    private String        drillId;

    /**
     * <p>Getter for the field <code>createdAt</code>.</p>
     *
     * @return the createdAt
     */
    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    /**
     * <p>
     * Getter for the field <code>currentSkillLevel</code>.
     * </p>
     *
     * @return the currentSkillLevel
     */
    public SkillLevel getCurrentSkillLevel()
    {
        return currentSkillLevel;
    }

    /**
     * <p>Getter for the field <code>drillId</code>.</p>
     *
     * @return the drillId
     */
    public String getDrillId()
    {
        return drillId;
    }

    /**
     * <p>Getter for the field <code>packageId</code>.</p>
     *
     * @return the packageId
     */
    public String getPackageId()
    {
        return packageId;
    }

    /**
     * <p>
     * Getter for the field <code>playCount</code>.
     * </p>
     *
     * @return the playCount
     */
    public int getPlayCount()
    {
        return playCount;
    }

    /**
     * <p>Getter for the field <code>uid</code>.</p>
     *
     * @return the uid
     */
    public int getUid()
    {
        return uid;
    }

    /**
     * <p>Getter for the field <code>updatedAt</code>.</p>
     *
     * @return the updatedAt
     */
    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * <p>Getter for the field <code>userUid</code>.</p>
     *
     * @return the userUid
     */
    public int getUserUid()
    {
        return userUid;
    }

    /**
     * <p>Getter for the field <code>version</code>.</p>
     *
     * @return the version
     */
    public int getVersion()
    {
        return version;
    }

    /**
     * <p>Setter for the field <code>createdAt</code>.</p>
     *
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(final LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    /**
     * <p>
     * Setter for the field <code>currentSkillLevel</code>.
     * </p>
     *
     * @param currentSkillLevel the currentSkillLevel to set
     */
    public void setCurrentSkillLevel(final SkillLevel currentSkillLevel)
    {
        this.currentSkillLevel = currentSkillLevel;
    }

    /**
     * <p>Setter for the field <code>drillId</code>.</p>
     *
     * @param drillId the drillId to set
     */
    public void setDrillId(final String drillId)
    {
        this.drillId = drillId;
    }

    /**
     * <p>Setter for the field <code>packageId</code>.</p>
     *
     * @param packageId the packageId to set
     */
    public void setPackageId(final String packageId)
    {
        this.packageId = packageId;
    }

    /**
     * <p>
     * Setter for the field <code>playCount</code>.
     * </p>
     *
     * @param playCount the playCount to set
     */
    public void setPlayCount(final int playCount)
    {
        this.playCount = playCount;
    }

    /**
     * <p>Setter for the field <code>uid</code>.</p>
     *
     * @param uid the uid to set
     */
    public void setUid(final int uid)
    {
        this.uid = uid;
    }

    /**
     * <p>Setter for the field <code>updatedAt</code>.</p>
     *
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(final LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    /**
     * <p>Setter for the field <code>userUid</code>.</p>
     *
     * @param userUid the userUid to set
     */
    public void setUserUid(final int userUid)
    {
        this.userUid = userUid;
    }

    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version the version to set
     */
    public void setVersion(final int version)
    {
        this.version = version;
    }
}
