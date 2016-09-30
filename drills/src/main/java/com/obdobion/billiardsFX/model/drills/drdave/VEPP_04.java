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
public class VEPP_04 extends AbstractDrillPackage
{

    /** {@inheritDoc} */
    @Override
    protected List<DrillFactory> drillFactories()
    {
        final List<DrillFactory> dfs = new ArrayList<>();

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.rollingCBOneRailKickToCorner,
                new DrDaveDemo(PackageName.VEPP_04, 126, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.rollingCBOneRailKickLineOfBalls,
                new DrDaveDemo(PackageName.VEPP_04, 127, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.kickAngleRange,
                new DrDaveDemo(PackageName.VEPP_04, 128, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.rollingCBShallowAngleKick,
                new DrDaveDemo(PackageName.VEPP_04, 129, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.firmStunShallowAngleKick,
                new DrDaveDemo(PackageName.VEPP_04, 130, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.lineOfBallsMirrorKick,
                new DrDaveDemo(PackageName.VEPP_04, 131, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.twoCushionKickToCorner,
                new DrDaveDemo(PackageName.VEPP_04, 133, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.twoCushionKickToRail,
                new DrDaveDemo(PackageName.VEPP_04, 134, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.twoCushionKickToRail,
                new DrDaveDemo(PackageName.VEPP_04, 134, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.tracksToCorner,
                new DrDaveDemo(PackageName.VEPP_04, 136, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.thirdRailTargetsFromCorner,
                new DrDaveDemo(PackageName.VEPP_04, 137, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.thirdRailTargetFromDifferentPositions,
                new DrDaveDemo(PackageName.VEPP_04, 138, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.repeatedKickToCorner,
                new DrDaveDemo(PackageName.VEPP_04, 139, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.perpetualKick,
                new DrDaveDemo(PackageName.VEPP_04, 140, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("KICK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.lineOfBallsBank,
                new DrDaveDemo(PackageName.VEPP_04, 142, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("BANK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.crossSideBankCutAngle,
                new DrDaveDemo(PackageName.VEPP_04, 143, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("BANK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.crossCornerBankCutAngle,
                new DrDaveDemo(PackageName.VEPP_04, 144, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("BANK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.bankLineOfBallsPosition,
                new DrDaveDemo(PackageName.VEPP_04, 145, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("BANK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.bankToAllPockets,
                new DrDaveDemo(PackageName.VEPP_04, 146, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("BANK")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.jackedUpOverObsticle,
                new DrDaveDemo(PackageName.VEPP_04, 147, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("JACKEDUP")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.elevatedDrawAtRail,
                new DrDaveDemo(PackageName.VEPP_04, 148, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("JACKEDUP"),
                Tag.findOrCreate("DRAW"),
                Tag.findOrCreate("RAIL")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.progressiveGapJump,
                new DrDaveDemo(PackageName.VEPP_04, 149, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("JUMP")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.progressiveHeightJump,
                new DrDaveDemo(PackageName.VEPP_04, 150, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("JUMP")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.progressiveDistanceJump,
                new DrDaveDemo(PackageName.VEPP_04, 151, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("JUMP")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.progressiveMasse,
                new DrDaveDemo(PackageName.VEPP_04, 151, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("MASSE")));

        dfs.add(new DrillFactory(PackageName.VEPP_04, DrillName.softMasseSpeedControl,
                new DrDaveDemo(PackageName.VEPP_04, 151, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_04"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("MASSE")));

        return dfs;
    }
}
