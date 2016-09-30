package com.obdobion.billiardsFX.model.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.obdobion.billiardsFX.model.ConfigParm;
import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.DrillLookupFilter;
import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.DrillStatus;
import com.obdobion.billiardsFX.model.SaveStoreFilter;
import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.SkillLevel;
import com.obdobion.calendar.CalendarFactory;

/**
 * <p>
 * DrillMasterDbImpl class.
 * </p>
 *
 * Database tables are used in this class: ConfigParm, Drill. DrillStatus, User.
 *
 * ConfigParm: userId, key, value
 *
 * DrillStatus: userId, packageId, drillId, currentLevel, playedCount
 *
 * Drill: userId, packageId, drillId, playedOnDate, note, status (temp or final)
 * score, version, gameData
 *
 * User: userId, email, firstName, lastName
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrillMasterDbImpl extends AbstractDrillMasterImpl
{
    static public class User
    {
        private String userId;
        private int    uid;

        public int getUid()
        {
            return 1;
        }
    }

    /** Constant <code>DrillStatusTemp=0</code> */
    static final public int         DrillStatusTemp = 0;

    /*-
     create database drillmaster;
     CREATE USER 'webserver'@'localhost' IDENTIFIED BY 'webserver';
     connect drillmaster;
     grant all on drillmaster.* to 'webserver'@'localhost';
    
     drop table if exists dmuser;
     create table dmuser
         (
         uid int auto_increment,
         version int not null default 0,
         createdAt datetime not null default current_timestamp,
         updatedAt datetime,
    
         userId varChar(255) not null,
         email varChar(255) not null,
         status tinyInt not null default 0,
         firstName varChar(255),
         lastName varChar(255),
    
         primary key (uid),
         unique index dmuser_by_userId (userId),
         unique index dmuser_by_email (email)
         )
         engine InnoDb
         default character set = utf8
         COLLATE = utf8_bin;
    
     DROP TRIGGER IF EXISTS dmuser_beforeUpdate;
     delimiter //
     CREATE trigger dmuser_beforeUpdate
        before update on dmuser for each row
        BEGIN
          if (NEW.version != OLD.version) then
             signal sqlstate '45000' set message_text = 'optimistic lock failed on dmuser';
          end if;
          set NEW.version = OLD.version + 1;
          set NEW.updatedAt = now();
        END;//
      delimiter ;
    
     drop table if exists dmconfig;
     create table dmconfig
         (
         uid int auto_increment,
         version int not null default 0,
         createdAt datetime not null default current_timestamp,
         updatedAt datetime,
    
         userUid int not null
           references dmuser (uid) on delete cascade,
         propertyName varchar(255) not null,
         propertyValue varchar(255),
    
         primary key (uid),
         unique index dmconfig_by_propertyName (userUid, propertyName)
         )
         engine InnoDb
         default character set = utf8
         COLLATE = utf8_bin;
    
     DROP TRIGGER IF EXISTS dmconfig_beforeUpdate;
     delimiter //
     CREATE trigger dmconfig_beforeUpdate
        before update on dmconfig for each row
        BEGIN
          if (NEW.version != OLD.version) then
             signal sqlstate '45000' set message_text = 'optimistic lock failed on dmconfig';
          end if;
          set NEW.version = OLD.version + 1;
          set NEW.updatedAt = now();
        END;//
      delimiter ;
    
     drop table if exists dmdrill;
     create table dmdrill
         (
         uid int auto_increment,
         version int not null default 0,
         createdAt datetime not null default current_timestamp,
         updatedAt datetime,
    
         userUid int not null
           references dmuser (uid) on delete cascade,
         packageId varChar(255) not null,
         drillId varChar(255) not null,
         playedOnDate datetime not null default current_timestamp,
         note varChar(255),
         status tinyInt not null default 0,
         score int,
         gameDataVersion tinyInt not null default 0,
         gameData varChar(255),
    
         primary key (uid),
         index dmdrill_by_Id (userUid, drillId)
         )
         engine InnoDb
         default character set = utf8
         COLLATE = utf8_bin;
    
     DROP TRIGGER IF EXISTS dmdrill_beforeUpdate;
     delimiter //
     CREATE trigger dmdrill_beforeUpdate
        before update on dmdrill for each row
        BEGIN
          if (NEW.version != OLD.version) then
             signal sqlstate '45000' set message_text = 'optimistic lock failed on dmdrill';
          end if;
          set NEW.version = OLD.version + 1;
          set NEW.updatedAt = now();
        END;//
      delimiter ;
    
     drop table if exists dmdrillstatus;
     create table dmdrillstatus
         (
         uid int auto_increment,
         version int not null default 0,
         createdAt datetime not null default current_timestamp,
         updatedAt datetime,
    
         userUid int not null
           references dmuser (uid) on delete cascade,
         packageId varChar(255) not null,
         drillId varChar(255) not null,
         currentSkillLevel varChar(16),
         playCount tinyInt not null default 0,
    
         primary key (uid),
         unique index dmdrill_by_Id (userUid, drillId)
         )
         engine InnoDb
         default character set = utf8
         COLLATE = utf8_bin;
    
     DROP TRIGGER IF EXISTS dmdrillstatus_beforeUpdate;
     delimiter //
     CREATE trigger dmdrillstatus_beforeUpdate
        before update on dmdrillstatus for each row
        BEGIN
          if (NEW.version != OLD.version) then
             signal sqlstate '45000' set message_text = 'optimistic lock failed on dmdrillstatus';
          end if;
          set NEW.version = OLD.version + 1;
          set NEW.updatedAt = now();
        END;//
      delimiter ;
    
     */

    /** Constant <code>DrillStatusPerm=1</code> */
    static final public int         DrillStatusPerm = 1;
    static String                   dbUrl           = "jdbc:mysql://localhost:3306/drillmaster?autoCommit=false";
    static String                   dbUser          = "webserver";
    static String                   dbPassword      = "webserver";

    final private Stack<Connection> connections     = new Stack<>();

    /** {@inheritDoc} */
    @Override
    public List<IDrill> createDrillFromSaveStore(final Context context, final SaveStoreFilter filter,
            final int mostRecentLimit)
                    throws InstantiationException, IOException, ParseException
    {
        context.setConnection(getConnection());
        try
        {
            final List<IDrill> drills = findDrills(context.getConnection(), context, filter, mostRecentLimit);

            context.getConnection().commit();

            return drills;

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public IDrill createDrillFromTempStore(final Context context)
            throws InstantiationException, IOException, ParseException
    {
        /*
         * There is only one temp drill per dbUser. They must close this out
         * before going on to another.
         */
        context.setConnection(getConnection());
        try
        {
            final IDrill drill = findTempDrill(context.getConnection(), context);

            context.getConnection().commit();

            return drill;

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
    }

    private ConfigParm findConfigParm(final Connection connection, final Context context, final String key)
            throws SQLException
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

        final User drillUser = userForSessionId(context);

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("select * from dmconfig where ");
        pw.printf(" userUid = ?");
        pw.printf(" and propertyName = ?");

        final PreparedStatement ps = connection.prepareStatement(sw.toString());

        int c = 1;
        ps.setInt(c++, drillUser.getUid());
        ps.setString(c++, key);

        final ResultSet resultSet = ps.executeQuery();

        resultSet.first();

        final ConfigParm parm = new ConfigParm();
        parm.setPropertyName(resultSet.getString("propertyName"));
        parm.setPropertyValue(resultSet.getString("propertyValue"));
        parm.setUid(resultSet.getInt("uid"));
        parm.setVersion(resultSet.getInt("version"));
        parm.setUserUid(resultSet.getInt("userUid"));
        return parm;

    }

    private List<IDrill> findDrills(
            final Connection connection,
            final Context context,
            final SaveStoreFilter filter,
            final int limit)
                    throws SQLException
    {
        /*-
        +-----------------+--------------+------+-----+-------------------+----------------+
        | Field           | Type         | Null | Key | Default           | Extra          |
        +-----------------+--------------+------+-----+-------------------+----------------+
        | uid             | int(11)      | NO   | PRI | NULL              | auto_increment |
        | version         | int(11)      | NO   |     | 0                 |                |
        | createdAt       | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | updatedAt       | datetime     | YES  |     | NULL              |                |
        | userUid         | int(11)      | NO   | MUL | NULL              |                |
        | packageId       | varchar(255) | NO   |     | NULL              |                |
        | drillId         | varchar(255) | NO   |     | NULL              |                |
        | playedOnDate    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | note            | varchar(255) | YES  |     | NULL              |                |
        | status          | tinyint(4)   | NO   |     | 0                 |                |
        | score           | int(11)      | YES  |     | NULL              |                |
        | gameDataVersion | tinyint(4)   | NO   |     | 0                 |                |
        | gameData        | varchar(255) | YES  |     | NULL              |                |
        +-----------------+--------------+------+-----+-------------------+----------------+
        */

        final User drillUser = userForSessionId(context);

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("select * from dmdrill where ");
        pw.printf(" userUid = ?");
        pw.printf(" and status = ?");

        final PreparedStatement ps = connection.prepareStatement(sw.toString());

        int c = 1;
        ps.setInt(c++, drillUser.getUid());
        ps.setInt(c++, DrillStatusPerm);

        final List<IDrill> drills = new ArrayList<>();
        try (final ResultSet resultSet = ps.executeQuery())
        {
            if (resultSet.first())
                while (!resultSet.isAfterLast())
                {
                    final String drillId = resultSet.getString("drillId");

                    final IDrill drill = DrillMaster.createDrill(context.getSessionId(),
                            DrillMaster.availableDrills(new DrillLookupFilter(drillId)).get(0));

                    drill.setUid(resultSet.getInt("uid"));
                    drill.setVersion(resultSet.getInt("version"));
                    drill.setUserUid(resultSet.getInt("userUid"));
                    drill.setPackageId(resultSet.getString("packageId"));
                    drill.setDrillId(resultSet.getString("drillId"));
                    drill.setDate(CalendarFactory.at(resultSet.getDate("playedOnDate").getTime()));

                    final SaveStoreFilter header = new SaveStoreFilter();
                    header.setDateSelection(CalendarFactory.asJSON(drill.getDate()), SaveStoreFilter.DatePart.SECOND);
                    header.setPackageSelection(drill.getPackageId());
                    header.setDrillSelection(drill.getDrillId());
                    if (filter == null || filter.selects(header))
                    {
                        drill.setNote(resultSet.getString("note"));
                        drill.gameData(resultSet.getString("gameData").split(","), 0);
                        drills.add(drill);

                        if (drills.size() >= limit)
                            break;
                    }
                    resultSet.next();
                }
        } catch (ParseException | InstantiationException | IllegalAccessException e)
        {
            logger.error(e.getMessage(), e);
            throw new SQLException(e);
        }
        return drills;
    }

    private DrillStatus findDrillStatus(final Connection connection, final Context context, final String drillId)
            throws SQLException
    {
        /*-
        +-------------------+--------------+------+-----+-------------------+----------------+
        | Field             | Type         | Null | Key | Default           | Extra          |
        +-------------------+--------------+------+-----+-------------------+----------------+
        | uid               | int(11)      | NO   | PRI | NULL              | auto_increment |
        | version           | int(11)      | NO   |     | 0                 |                |
        | createdAt         | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | updatedAt         | datetime     | YES  |     | NULL              |                |
        | userUid           | int(11)      | NO   | MUL | NULL              |                |
        | packageId         | varchar(255) | NO   |     | NULL              |                |
        | drillId           | varchar(255) | NO   |     | NULL              |                |
        | currentSkillLevel | varchar(16)  | YES  |     | NULL              |                |
        | playCount         | tinyint(4)   | NO   |     | 0                 |                |
        +-------------------+--------------+------+-----+-------------------+----------------+
        */

        final User drillUser = userForSessionId(context);

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("select * from dmdrill where ");
        pw.printf(" userUid = ?");
        pw.printf(" and drillId = ?");

        final PreparedStatement ps = connection.prepareStatement(sw.toString());

        int c = 1;
        ps.setInt(c++, drillUser.getUid());
        ps.setString(c++, drillId);

        final ResultSet resultSet = ps.executeQuery();

        final DrillStatus drStatus = new DrillStatus();

        resultSet.first();

        drStatus.setUid(resultSet.getInt("uid"));
        drStatus.setVersion(resultSet.getInt("version"));
        drStatus.setUserUid(resultSet.getInt("userUid"));
        drStatus.setPackageId(resultSet.getString("packageId"));
        drStatus.setDrillId(resultSet.getString("drillId"));
        drStatus.setCurrentSkillLevel(SkillLevel.valueOf(resultSet.getString("currentSkillLevel")));
        drStatus.setPlayCount(resultSet.getInt("playCount"));

        return drStatus;
    }

    private IDrill findTempDrill(final Connection connection, final Context context)
            throws SQLException
    {
        /*-
        +-----------------+--------------+------+-----+-------------------+----------------+
        | Field           | Type         | Null | Key | Default           | Extra          |
        +-----------------+--------------+------+-----+-------------------+----------------+
        | uid             | int(11)      | NO   | PRI | NULL              | auto_increment |
        | version         | int(11)      | NO   |     | 0                 |                |
        | createdAt       | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | updatedAt       | datetime     | YES  |     | NULL              |                |
        | userUid         | int(11)      | NO   | MUL | NULL              |                |
        | packageId       | varchar(255) | NO   |     | NULL              |                |
        | drillId         | varchar(255) | NO   |     | NULL              |                |
        | playedOnDate    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | note            | varchar(255) | YES  |     | NULL              |                |
        | status          | tinyint(4)   | NO   |     | 0                 |                |
        | score           | int(11)      | YES  |     | NULL              |                |
        | gameDataVersion | tinyint(4)   | NO   |     | 0                 |                |
        | gameData        | varchar(255) | YES  |     | NULL              |                |
        +-----------------+--------------+------+-----+-------------------+----------------+
        */

        final User drillUser = userForSessionId(context);

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("select * from dmdrill where ");
        pw.printf(" userUid = ?");
        pw.printf(" and status = ?");

        final PreparedStatement ps = connection.prepareStatement(sw.toString());

        int c = 1;
        ps.setInt(c++, drillUser.getUid());
        ps.setInt(c++, DrillStatusTemp);

        final ResultSet resultSet = ps.executeQuery();

        if (!resultSet.first())
            throw new SQLException("temp drill not found for userUid=" + drillUser.getUid());

        final String drillId = resultSet.getString("drillId");

        try
        {
            final IDrill drill = DrillMaster.createDrill(context.getSessionId(),
                    DrillMaster.availableDrills(new DrillLookupFilter(drillId)).get(0));

            drill.setUid(resultSet.getInt("uid"));
            drill.setVersion(resultSet.getInt("version"));
            drill.setUserUid(resultSet.getInt("userUid"));
            drill.setPackageId(resultSet.getString("packageId"));
            drill.setDrillId(resultSet.getString("drillId"));
            drill.setNote(resultSet.getString("note"));
            drill.gameData(resultSet.getString("gameData").split(","), 0);
            drill.setDate(CalendarFactory.at(resultSet.getDate("playedOnDate").getTime()));

            return drill;
        } catch (ParseException | InstantiationException | IllegalAccessException e)
        {
            logger.error(e.getMessage(), e);
            throw new SQLException(e);
        }

    }

    /** {@inheritDoc} */
    @Override
    public ConfigParm getConfigParm(final Context context, final String parmKey) throws IOException
    {
        context.setConnection(getConnection());
        try
        {
            final ConfigParm value = findConfigParm(context.getConnection(), context, parmKey);
            context.getConnection().commit();
            return value;

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
    }

    private Connection getConnection() throws IOException
    {
        Connection connection = null;
        try
        {
            connection = connections.firstElement();
            connections.remove(connection);
            return connection;

        } catch (final NoSuchElementException e)
        {
            try
            {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                connection.setAutoCommit(false);
                return connection;

            } catch (final SQLException sqlEx)
            {
                throw new IOException(sqlEx);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Context getContext(final String sessionId, final int transactionNumber)
    {
        return Context.getInstance(sessionId, transactionNumber);
    }

    /** {@inheritDoc} */
    @Override
    public DrillStatus getDrillStatus(final Context context, final String drillId)
            throws InstantiationException, IOException, ParseException
    {
        context.setConnection(getConnection());
        try
        {
            final DrillStatus status = findDrillStatus(context.getConnection(), context, drillId);
            context.getConnection().commit();
            return status;

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
    }

    private void insertDrill(final Connection connection, final IDrill drill, final int status)
            throws SQLException
    {
        /*-
        +-----------------+--------------+------+-----+-------------------+----------------+
        | Field           | Type         | Null | Key | Default           | Extra          |
        +-----------------+--------------+------+-----+-------------------+----------------+
        | uid             | int(11)      | NO   | PRI | NULL              | auto_increment |
        | version         | int(11)      | NO   |     | 0                 |                |
        | createdAt       | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | updatedAt       | datetime     | YES  |     | NULL              |                |
        | userUid         | int(11)      | NO   | MUL | NULL              |                |
        | packageId       | varchar(255) | NO   |     | NULL              |                |
        | drillId         | varchar(255) | NO   |     | NULL              |                |
        | playedOnDate    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | note            | varchar(255) | YES  |     | NULL              |                |
        | status          | tinyint(4)   | NO   |     | 0                 |                |
        | score           | int(11)      | YES  |     | NULL              |                |
        | gameDataVersion | tinyint(4)   | NO   |     | 0                 |                |
        | gameData        | varchar(255) | YES  |     | NULL              |                |
        +-----------------+--------------+------+-----+-------------------+----------------+
        */
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("insert into dmdrill (");
        pw.printf(" userUid,");
        pw.printf(" packageId,");
        pw.printf(" drillId,");
        pw.printf(" note,");
        pw.printf(" status,");
        pw.printf(" score,");
        pw.printf(" gameDataVersion,");
        pw.printf(" gameData,");
        pw.printf(" playedOnDate)");
        pw.printf(" values (?,?,?,?,?,?,?,?,?)");

        final PreparedStatement ps = connection.prepareStatement(sw.toString(),
                Statement.RETURN_GENERATED_KEYS);

        int c = 1;
        ps.setInt(c++, drill.getUserUid());
        ps.setString(c++, drill.getPackageId());
        ps.setString(c++, drill.getDrillId());
        ps.setString(c++, drill.getNote());
        ps.setInt(c++, status);
        ps.setInt(c++, drill.score());
        ps.setInt(c++, drill.gameDataVersion());
        ps.setString(c++, drill.gameData());
        ps.setDate(c++, new java.sql.Date(CalendarFactory.asDateLong(drill.getDate())));

        ps.executeUpdate();

        final ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        drill.setUid(tableKeys.getInt(1));
    }

    private void returnConnection(final Context context)
    {
        try
        {
            if (context.getConnection().getWarnings() != null)
            {
                context.getConnection().getWarnings().forEach(w -> logger.warn("{}", w.getMessage()));
                context.getConnection().clearWarnings();
            }

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
        connections.push(context.getConnection());
        context.setConnection(null);
    }

    private void rollbackNoExceptions(final Context context)
    {
        try
        {
            context.getConnection().rollback();
        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public SaveStoreFilter saveDrillToSaveStore(final Context context, final IDrill drill)
            throws IOException, InstantiationException, ParseException
    {
        context.setConnection(getConnection());
        try
        {
            if (drill.getUid() == 0)
            {
                drill.setUserUid(userForSessionId(context).getUid());
                insertDrill(context.getConnection(), drill, DrillStatusPerm);
            } else
                updateDrill(context.getConnection(), drill, DrillStatusPerm);
            context.getConnection().commit();

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void saveDrillToTempStore(final Context context, final IDrill drill) throws IOException
    {
        context.setConnection(getConnection());
        try
        {
            if (drill.getUid() == 0)
            {
                drill.setUserUid(userForSessionId(context).getUid());
                insertDrill(context.getConnection(), drill, DrillStatusTemp);
            } else
                updateDrill(context.getConnection(), drill, DrillStatusTemp);
            context.getConnection().commit();

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setConfigParm(final Context context, final ConfigParm parm) throws IOException
    {
        context.setConnection(getConnection());
        try
        {
            updateConfigParm(context.getConnection(), parm);

            context.getConnection().commit();

        } catch (final SQLException e)
        {
            logger.error(e.getMessage(), e);
            rollbackNoExceptions(context);
            throw new IOException(e);
        } finally
        {
            returnConnection(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void start()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }
        super.start();
    }

    /** {@inheritDoc} */
    @Override
    public void stop()
    {
        super.stop();
    }

    private void updateConfigParm(final Connection connection, final ConfigParm parm)
            throws SQLException
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
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("update dmconfig set ");

        pw.printf(" version = ?,");
        pw.printf(" userUid = ?,");
        pw.printf(" propertyName = ?,");
        pw.printf(" propertyValue = ?");

        pw.printf(" where uid = ?");

        final PreparedStatement ps = connection.prepareStatement(sw.toString());

        int c = 1;
        ps.setInt(c++, parm.getVersion());
        ps.setInt(c++, parm.getUserUid());
        ps.setString(c++, parm.getPropertyName());
        ps.setString(c++, parm.getPropertyValue());

        ps.setInt(c++, parm.getUid());
        ps.executeUpdate();
    }

    private void updateDrill(final Connection connection, final IDrill drill, final int status)
            throws SQLException
    {
        /*-
        +-----------------+--------------+------+-----+-------------------+----------------+
        | Field           | Type         | Null | Key | Default           | Extra          |
        +-----------------+--------------+------+-----+-------------------+----------------+
        | uid             | int(11)      | NO   | PRI | NULL              | auto_increment |
        | version         | int(11)      | NO   |     | 0                 |                |
        | createdAt       | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | updatedAt       | datetime     | YES  |     | NULL              |                |
        | userUid         | int(11)      | NO   | MUL | NULL              |                |
        | packageId       | varchar(255) | NO   |     | NULL              |                |
        | drillId         | varchar(255) | NO   |     | NULL              |                |
        | playedOnDate    | datetime     | NO   |     | CURRENT_TIMESTAMP |                |
        | note            | varchar(255) | YES  |     | NULL              |                |
        | status          | tinyint(4)   | NO   |     | 0                 |                |
        | score           | int(11)      | YES  |     | NULL              |                |
        | gameDataVersion | tinyint(4)   | NO   |     | 0                 |                |
        | gameData        | varchar(255) | YES  |     | NULL              |                |
        +-----------------+--------------+------+-----+-------------------+----------------+
        */
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.printf("update dmdrill set ");

        pw.printf(" version = ?,");
        pw.printf(" userUid = ?,");
        pw.printf(" packageId = ?,");
        pw.printf(" drillId = ?,");
        pw.printf(" note = ?,");
        pw.printf(" status = ?,");
        pw.printf(" score = ?,");
        pw.printf(" gameDataVersion = ?,");
        pw.printf(" gameData = ?,");
        pw.printf(" playedOnDate = ?");

        pw.printf(" where uid = ?");

        final PreparedStatement ps = connection.prepareStatement(sw.toString());

        int c = 1;
        ps.setInt(c++, drill.getVersion());
        ps.setInt(c++, drill.getUserUid());
        ps.setString(c++, drill.getPackageId());
        ps.setString(c++, drill.getDrillId());
        ps.setString(c++, drill.getNote());
        ps.setInt(c++, status);
        ps.setInt(c++, drill.score());
        ps.setInt(c++, drill.gameDataVersion());
        ps.setString(c++, drill.gameData());
        ps.setDate(c++, new java.sql.Date(CalendarFactory.asDateLong(drill.getDate())));

        ps.setInt(c++, drill.getUid());

        ps.executeUpdate();
    }

    private User userForSessionId(final Context context)
    {
        final User drillUser = new User();
        drillUser.userId = "TBD";
        drillUser.uid = 1;
        return drillUser;
    }
}
