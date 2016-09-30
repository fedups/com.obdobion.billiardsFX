package com.obdobion.billiardsFX.model;

import java.time.LocalDateTime;

/**
 * <p>ConfigParm class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class ConfigParm
{
    /*-
    +---------------+--------------+------+-----+-------------------+----------------+
    | Field         | Type         | Null | Key | Default           | Extra          |
    +---------------+--------------+------+-----+-------------------+----------------+
    | uid           | int(11)      | NO   | PRI | NULL              | auto_increment |
    | version       | int(11)      | NO   |     | 0                 |                |
    | createdAt     | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
    | updatedAt     | datetime     | YES  |     | NULL              |                |
    | userUid       | int(11)      | NO   | MUL | NULL              |                |
    | propertyName  | varchar(255) | NO   |     | NULL              |                |
    | propertyValue | varchar(255) | YES  |     | NULL              |                |
    +---------------+--------------+------+-----+-------------------+----------------+
    */
    private String        propertyName;
    private String        propertyValue;
    /*
     * database fields
     */
    private int           uid;
    private int           version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int           userUid;

    /**
     * <p>Constructor for ConfigParm.</p>
     */
    public ConfigParm()
    {}

    /**
     * <p>Constructor for ConfigParm.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     */
    public ConfigParm(final String key, final String value)
    {
        setPropertyName(key);
        setPropertyValue(value);
    }

    /**
     * <p>Getter for the field <code>createdAt</code>.</p>
     *
     * @return a {@link java.time.LocalDateTime} object.
     */
    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    /**
     * <p>Getter for the field <code>propertyName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPropertyName()
    {
        return propertyName;
    }

    /**
     * <p>Getter for the field <code>propertyValue</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPropertyValue()
    {
        return propertyValue;
    }

    /**
     * <p>Getter for the field <code>uid</code>.</p>
     *
     * @return a int.
     */
    public int getUid()
    {
        return uid;
    }

    /**
     * <p>Getter for the field <code>updatedAt</code>.</p>
     *
     * @return a {@link java.time.LocalDateTime} object.
     */
    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * <p>Getter for the field <code>userUid</code>.</p>
     *
     * @return a int.
     */
    public int getUserUid()
    {
        return userUid;
    }

    /**
     * <p>Getter for the field <code>version</code>.</p>
     *
     * @return a int.
     */
    public int getVersion()
    {
        return version;
    }

    /**
     * <p>Setter for the field <code>createdAt</code>.</p>
     *
     * @param createdAt a {@link java.time.LocalDateTime} object.
     */
    public void setCreatedAt(final LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    /**
     * <p>Setter for the field <code>propertyName</code>.</p>
     *
     * @param propertyName a {@link java.lang.String} object.
     */
    public void setPropertyName(final String propertyName)
    {
        this.propertyName = propertyName;
    }

    /**
     * <p>Setter for the field <code>propertyValue</code>.</p>
     *
     * @param propertyValue a {@link java.lang.String} object.
     */
    public void setPropertyValue(final String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    /**
     * <p>Setter for the field <code>uid</code>.</p>
     *
     * @param uid a int.
     */
    public void setUid(final int uid)
    {
        this.uid = uid;
    }

    /**
     * <p>Setter for the field <code>updatedAt</code>.</p>
     *
     * @param updatedAt a {@link java.time.LocalDateTime} object.
     */
    public void setUpdatedAt(final LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    /**
     * <p>Setter for the field <code>userUid</code>.</p>
     *
     * @param userUid a int.
     */
    public void setUserUid(final int userUid)
    {
        this.userUid = userUid;
    }

    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version a int.
     */
    public void setVersion(final int version)
    {
        this.version = version;
    }
}
