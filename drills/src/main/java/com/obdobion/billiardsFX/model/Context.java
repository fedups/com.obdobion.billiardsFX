package com.obdobion.billiardsFX.model;

import java.io.Closeable;
import java.sql.Connection;

import org.apache.log4j.NDC;

/**
 * <p>
 * Context class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class Context implements Closeable
{
    /**
     * <p>
     * getInstance.
     * </p>
     *
     * @param transactionNumber a int.
     * @return a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    static public Context getInstance(final int transactionNumber)
    {
        final Context context = new Context();
        context.setSessionId(null);
        context.setTransactionNumber(transactionNumber);
        context.setFunction(Thread.currentThread().getStackTrace()[2]);
        context.begin();
        return context;
    }

    /**
     * <p>
     * getInstance.
     * </p>
     *
     * @param sessionId a {@link java.lang.String} object.
     * @param transactionNumber a int.
     * @return a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    static public Context getInstance(final String sessionId, final int transactionNumber)
    {
        final Context context = new Context();
        context.setSessionId(sessionId);
        context.setTransactionNumber(transactionNumber);
        context.setFunction(Thread.currentThread().getStackTrace()[2]);
        context.begin();
        return context;
    }

    private StackTraceElement function;
    private String            sessionId;
    private int               transactionNumber;
    private Connection        connection;

    private Context()
    {}

    private void begin()
    {
        if (getSessionId() != null)
            NDC.push(getSessionId());
        NDC.push(Integer.toString(getTransactionNumber()));
        NDC.push(getFunction().getMethodName());
    }

    /** {@inheritDoc} */
    @Override
    public void close()
    {
        end();
    }

    private void end()
    {
        if (getSessionId() != null)
            NDC.pop();
        NDC.pop();
        NDC.pop();
    }

    /**
     * <p>Getter for the field <code>connection</code>.</p>
     *
     * @return a {@link java.sql.Connection} object.
     */
    public Connection getConnection()
    {
        return connection;
    }

    private StackTraceElement getFunction()
    {
        return function;
    }

    /**
     * <p>
     * Getter for the field <code>sessionId</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSessionId()
    {
        return sessionId;
    }

    /**
     * <p>
     * Getter for the field <code>transactionNumber</code>.
     * </p>
     *
     * @return a int.
     */
    public int getTransactionNumber()
    {
        return transactionNumber;
    }

    /**
     * <p>
     * isInUserSession.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isInUserSession()
    {
        return sessionId != null;
    }

    /**
     * <p>Setter for the field <code>connection</code>.</p>
     *
     * @param connection a {@link java.sql.Connection} object.
     */
    public void setConnection(final Connection connection)
    {
        this.connection = connection;
    }

    private void setFunction(final StackTraceElement function)
    {
        this.function = function;
    }

    private void setSessionId(final String sessionId)
    {
        this.sessionId = sessionId;
    }

    /**
     * <p>
     * Setter for the field <code>transactionNumber</code>.
     * </p>
     *
     * @param transactionNumber a int.
     */
    public void setTransactionNumber(final int transactionNumber)
    {
        this.transactionNumber = transactionNumber;
    }
}
