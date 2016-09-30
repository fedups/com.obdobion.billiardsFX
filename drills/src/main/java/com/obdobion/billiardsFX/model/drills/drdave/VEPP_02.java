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
public class VEPP_02 extends AbstractDrillPackage
{

    /** {@inheritDoc} */
    @Override
    protected List<DrillFactory> drillFactories()
    {
        final List<DrillFactory> dfs = new ArrayList<>();

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.ppstun4a,
                new DrDaveDemo(PackageName.VEPP_02, 55, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STUN")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.ppstun5a,
                new DrDaveDemo(PackageName.VEPP_02, 56, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STUN")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.ppstunhold,
                new DrDaveDemo(PackageName.VEPP_02, 57, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STUN")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.thinCutSpeedControl,
                new DrDaveDemo(PackageName.VEPP_02, 58, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.repeatedFollow,
                new DrDaveDemo(PackageName.VEPP_02, 59, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("FOLLOW")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.noRailGridOfBalls,
                new DrDaveDemo(PackageName.VEPP_02, 60, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("AIMING")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.wagonWheelBIH,
                new DrDaveDemo(PackageName.VEPP_02, 62, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("AIMING")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.wagonWheelHalfBall,
                new DrDaveDemo(PackageName.VEPP_02, 63, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("AIMING")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.stunFollowDrawTarget,
                new DrDaveDemo(PackageName.VEPP_02, 65, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STUN"),
                Tag.findOrCreate("FOLLOW"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.englishShortRail,
                new DrDaveDemo(PackageName.VEPP_02, 67, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("ENGLISH")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.englishAcrossTable,
                new DrDaveDemo(PackageName.VEPP_02, 68, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("ENGLISH")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.insideFollow2Rail,
                new DrDaveDemo(PackageName.VEPP_02, 69, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("SPEED"),
                Tag.findOrCreate("ENGLISH"),
                Tag.findOrCreate("FOLLOW")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.outsideDraw2Rail,
                new DrDaveDemo(PackageName.VEPP_02, 70, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("SPEED"),
                Tag.findOrCreate("ENGLISH"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.englishWagonWheelShortRailSmallAngle,
                new DrDaveDemo(PackageName.VEPP_02, 72, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("ENGLISH")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.englishWagonWheelShortRailLargeAngle,
                new DrDaveDemo(PackageName.VEPP_02, 73, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("ENGLISH")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.sitCBHold,
                new DrDaveDemo(PackageName.VEPP_02, 74, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("ENGLISH")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.runningEnglishRailCutShot,
                new DrDaveDemo(PackageName.VEPP_02, 75, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("ENGLISH"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RAIL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.gridTarget,
                new DrDaveDemo(PackageName.VEPP_02, 77, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.breakAndRun,
                new DrDaveDemo(PackageName.VEPP_02, 78, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.pocketHanger,
                new DrDaveDemo(PackageName.VEPP_02, 79, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.oneRailTarget,
                new DrDaveDemo(PackageName.VEPP_02, 80, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.twoRailTarget,
                new DrDaveDemo(PackageName.VEPP_02, 81, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.threeRailTarget,
                new DrDaveDemo(PackageName.VEPP_02, 82, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.sidePocketCOT,
                new DrDaveDemo(PackageName.VEPP_02, 83, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.cornerPocketCOT,
                new DrDaveDemo(PackageName.VEPP_02, 84, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.pocketCheating,
                new DrDaveDemo(PackageName.VEPP_02, 85, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.footLineOfBalls,
                new DrDaveDemo(PackageName.VEPP_02, 86, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.progressiveL,
                new DrDaveDemo(PackageName.VEPP_02, 87, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.fourBallMiddleDiamond,
                new DrDaveDemo(PackageName.VEPP_02, 88, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.railCutShot,
                new DrDaveDemo(PackageName.VEPP_02, 89, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL")));

        dfs.add(new DrillFactory(PackageName.VEPP_02, DrillName.headAndFootLineOfBalls,
                new DrDaveDemo(PackageName.VEPP_02, 90, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_02"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL")));

        return dfs;
    }
}
