package com.obdobion.billiardsFX.model.drills.drdave;

import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.Tag;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.ReservedNames.PackageName;
import com.obdobion.billiardsFX.model.demos.DrDaveDemo;
import com.obdobion.billiardsFX.model.drills.AbstractDrillPackage;
import com.obdobion.billiardsFX.model.drills.DrillFactory;
import com.obdobion.billiardsFX.model.drills.SkillLevel;
import com.obdobion.billiardsFX.model.drills.TargetScores;

/**
 * A factory class for creating DrDave VEPP disk 2 drills.
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class VEPP_05 extends AbstractDrillPackage
{

    /** {@inheritDoc} */
    @Override
    protected List<DrillFactory> drillFactories()
    {
        final List<DrillFactory> dfs = new ArrayList<>();

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.playingTheGhost,
                new DrDaveDemo(PackageName.VEPP_05, 154, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("RATING")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.nineBallPointsRating,
                new DrDaveDemo(PackageName.VEPP_05, 155, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("9BALL"),
                Tag.findOrCreate("RATING")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.nineBallBreak,
                new DrDaveDemo(PackageName.VEPP_05, 156, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("BREAK"),
                Tag.findOrCreate("9BALL"),
                Tag.findOrCreate("RATING")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.hopkinsQSkill,
                new DrDaveDemo(PackageName.VEPP_05, 157, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("RATING")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.fargo,
                new DrDaveDemo(PackageName.VEPP_05, 158, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("RATING")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.equalOffense,
                new DrDaveDemo(PackageName.VEPP_05, 159, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("RATING")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.safety,
                new DrDaveDemo(PackageName.VEPP_05, 160, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("SAFETY")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.loopCarom,
                new DrDaveDemo(PackageName.VEPP_05, 161, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CAROM")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.combinationCarom,
                new DrDaveDemo(PackageName.VEPP_05, 162, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("COMBINATION"),
                Tag.findOrCreate("CAROM")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.thinHitRailCutEnglish,
                new DrDaveDemo(PackageName.VEPP_05, 163, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("RAIL"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("ENGLISH")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.smallAngleRailCutStun,
                new DrDaveDemo(PackageName.VEPP_05, 164, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("RAIL"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("STUN")));

        dfs.add(new DrillFactory(PackageName.VEPP_05, DrillName.runOutPracticeRedos,
                new DrDaveDemo(PackageName.VEPP_05, 165, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_05"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY")));

        return dfs;
    }
}
