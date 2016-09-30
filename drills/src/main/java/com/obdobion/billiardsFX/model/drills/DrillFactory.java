package com.obdobion.billiardsFX.model.drills;

import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.Context;
import com.obdobion.billiardsFX.model.Tag;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.ReservedNames.PackageName;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall;

/**
 * <p>
 * DrillFactory class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class DrillFactory
{
    static private Class<?> instanceClassForDrillType(final String drillType)
    {
        if ("bowlliards".equalsIgnoreCase(drillType))
            return Bowlliards.class;
        if ("bacaball".equalsIgnoreCase(drillType))
            return BacaBall.class;
        if ("threeball".equalsIgnoreCase(drillType))
            return ThreeBall.class;
        if ("singlescoredrill".equalsIgnoreCase(drillType))
            return SingleScoreDrill.class;
        return null;
    }

    final private IDemonstration demonstration;
    final public String          drillID;
    public final String          drillTitleRef;
    final private Class<IDrill>  instanceClass;
    /**
     *
     */
    final private String         packageID;
    public final List<Tag>       tags;

    final private TargetScores   targetScores;

    /**
     * <p>Constructor for DrillFactory.</p>
     *
     * @param packageName a {@link com.obdobion.billiardsFX.model.ReservedNames.PackageName} object.
     * @param drillName a {@link com.obdobion.billiardsFX.model.ReservedNames.DrillName} object.
     * @param p_demo a {@link com.obdobion.billiardsFX.model.drills.IDemonstration} object.
     * @param p_targetScores a {@link com.obdobion.billiardsFX.model.drills.TargetScores} object.
     * @param p_tags a {@link com.obdobion.billiardsFX.model.Tag} object.
     */
    public DrillFactory(
            final PackageName packageName,
            final DrillName drillName,
            final IDemonstration p_demo,
            final TargetScores p_targetScores,
            final Tag... p_tags)
    {
        packageID = packageName.displayName();
        drillID = drillName.displayName();
        instanceClass = drillName.instanceClass();
        drillTitleRef = "drill_" + packageID + "_" + drillID + "_title";
        demonstration = p_demo;
        targetScores = p_targetScores;

        tags = new ArrayList<>();
        for (final Tag tag : p_tags)
            tags.add(tag);

        // loadUserImprovementsToStandardDefinition(p_packageID, p_drillID);
    }

    /**
     * <p>
     * Constructor for DrillFactory.
     * </p>
     *
     * @param p_packageID a {@link java.lang.String} object.
     * @param p_drillID a {@link java.lang.String} object.
     * @param drillType a {@link java.lang.String} object.
     * @param p_demo a {@link com.obdobion.billiardsFX.model.drills.IDemonstration}
     *            object.
     * @param p_targetScores a
     *            {@link com.obdobion.billiardsFX.model.drills.TargetScores} object.
     * @param p_tags a {@link com.obdobion.billiardsFX.model.Tag} object.
     */
    @SuppressWarnings("unchecked")
    public DrillFactory(
            final String p_packageID,
            final String p_drillID,
            final String drillType,
            final IDemonstration p_demo,
            final TargetScores p_targetScores,
            final Tag... p_tags)
    {
        packageID = p_packageID;
        drillID = p_drillID;
        instanceClass = (Class<IDrill>) instanceClassForDrillType(drillType);
        drillTitleRef = "drill_" + p_packageID + "_" + p_drillID + "_title";
        demonstration = p_demo;
        targetScores = p_targetScores;

        tags = new ArrayList<>();
        for (final Tag tag : p_tags)
            tags.add(tag);

        // loadUserImprovementsToStandardDefinition(p_packageID, p_drillID);
    }

    /*-
    private void loadUserImprovementsToStandardDefinition(String p_packageID, String p_drillID)
    {
        String sTags = DrillMaster.getConfigParm(p_packageID.toLowerCase() + "/" + p_drillID.toLowerCase() + "/tags");
        if (sTags != null)
            for (String sTag : sTags.split(","))
            {
                tags.add(Tag.findOrCreate(sTag));
            }
    }
    */

    /**
     * <p>
     * newInstance.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.IDrill} object.
     * @throws java.lang.InstantiationException if any.
     * @throws java.lang.IllegalAccessException if any.
     * @param context a {@link com.obdobion.billiardsFX.model.Context} object.
     */
    public IDrill newInstance(final Context context) throws InstantiationException, IllegalAccessException
    {
        final IDrill instance = instanceClass.newInstance();
        instance.setContext(context);
        instance.setDrillId(drillID);
        instance.setTitleRef(drillTitleRef);
        instance.setPackageId(packageID);
        for (final Tag tag : tags)
            instance.tagIt(tag);
        instance.setTargetScores(targetScores);
        instance.setDemonstration(demonstration);
        return instance;
    }
}
