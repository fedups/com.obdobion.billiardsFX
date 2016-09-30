package com.obdobion.billiardsFX.model.demos;

import java.io.IOException;

import com.obdobion.billiardsFX.model.drills.IDemonstration;

/**
 * <p>
 * HTMLInstructions class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class HTMLInstructions implements IDemonstration
{
    final String fileName;

    /**
     * <p>
     * Constructor for HTMLInstructions.
     * </p>
     *
     * @param p_fileName a {@link java.lang.String} object.
     */
    public HTMLInstructions(final String p_fileName)
    {
        fileName = p_fileName;
    }

    /**
     * <p>
     * getContents.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    public String getContents() throws IOException
    {
        // final StringBuilder sb = new StringBuilder();
        // try (BufferedReader reader = DrillMaster.fileReader(fileName))
        // {
        // String line;
        // while ((line = reader.readLine()) != null)
        // {
        // sb.append(line);
        // sb.append("\n");
        // }
        // }
        // return sb.toString();
        return null;
    }
}
