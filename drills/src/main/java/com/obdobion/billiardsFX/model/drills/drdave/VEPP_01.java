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
 * A factory class for creating DrDave VEPP disk 1 drills.
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class VEPP_01 extends AbstractDrillPackage
{
    /** {@inheritDoc} */
    @Override
    protected List<DrillFactory> drillFactories()
    {
        final List<DrillFactory> dfs = new ArrayList<>();

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut1dl,
                new DrDaveDemo(PackageName.VEPP_01, 15, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED)
                        .add(SkillLevel.INTERMEDIATE, 400)
                        .add(SkillLevel.ADVANCED, 600),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("LEFT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut1dr,
                new DrDaveDemo(PackageName.VEPP_01, 15, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED)
                        .add(SkillLevel.INTERMEDIATE, 400)
                        .add(SkillLevel.ADVANCED, 600),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RIGHT")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut2dl,
                new DrDaveDemo(PackageName.VEPP_01, 16, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED)
                        .add(SkillLevel.INTERMEDIATE, 400)
                        .add(SkillLevel.ADVANCED, 600),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("LEFT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut2dr,
                new DrDaveDemo(PackageName.VEPP_01, 16, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED)
                        .add(SkillLevel.INTERMEDIATE, 400)
                        .add(SkillLevel.ADVANCED, 600),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RIGHT")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut3dl,
                new DrDaveDemo(PackageName.VEPP_01, 17, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED)
                        .add(SkillLevel.INTERMEDIATE, 400)
                        .add(SkillLevel.ADVANCED, 600),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("LEFT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut3dr,
                new DrDaveDemo(PackageName.VEPP_01, 17, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED)
                        .add(SkillLevel.INTERMEDIATE, 400)
                        .add(SkillLevel.ADVANCED, 600),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RIGHT")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut4dl,
                new DrDaveDemo(PackageName.VEPP_01, 18, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("LEFT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut4dr,
                new DrDaveDemo(PackageName.VEPP_01, 18, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RIGHT")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut5dl,
                new DrDaveDemo(PackageName.VEPP_01, 19, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("LEFT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppcut5dr,
                new DrDaveDemo(PackageName.VEPP_01, 19, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RIGHT")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppstop1a,
                new DrDaveDemo(PackageName.VEPP_01, 30, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STOP")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppstop2a,
                new DrDaveDemo(PackageName.VEPP_01, 31, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STOP")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppstop3a,
                new DrDaveDemo(PackageName.VEPP_01, 32, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STOP")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppfollow1b,
                new DrDaveDemo(PackageName.VEPP_01, 34, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("FOLLOW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppfollow2b,
                new DrDaveDemo(PackageName.VEPP_01, 35, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("FOLLOW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppfollow3b,
                new DrDaveDemo(PackageName.VEPP_01, 36, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("FOLLOW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppfollow4b,
                new DrDaveDemo(PackageName.VEPP_01, 37, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("FOLLOW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppfollow5b,
                new DrDaveDemo(PackageName.VEPP_01, 38, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("FOLLOW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppdraw1c,
                new DrDaveDemo(PackageName.VEPP_01, 42, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppdraw2c,
                new DrDaveDemo(PackageName.VEPP_01, 43, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppdraw3c,
                new DrDaveDemo(PackageName.VEPP_01, 44, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppdraw4c,
                new DrDaveDemo(PackageName.VEPP_01, 45, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ppdraw5c,
                new DrDaveDemo(PackageName.VEPP_01, 46, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.pprailcutl,
                new DrDaveDemo(PackageName.VEPP_01, 21, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RAIL"),
                Tag.findOrCreate("LEFT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.pprailcutr,
                new DrDaveDemo(PackageName.VEPP_01, 21, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("AIMING"),
                Tag.findOrCreate("CUT"),
                Tag.findOrCreate("RAIL"),
                Tag.findOrCreate("RIGHT")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.lag,
                new DrDaveDemo(PackageName.VEPP_01, 23, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("LAG")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.successiveTouchShot,
                new DrDaveDemo(PackageName.VEPP_01, 24, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("SPEED")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.repeatedTouchShot,
                new DrDaveDemo(PackageName.VEPP_01, 25, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("SPEED")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.pocketSpeed,
                new DrDaveDemo(PackageName.VEPP_01, 26, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("SPEED")));
        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.angledPocketSpeed,
                new DrDaveDemo(PackageName.VEPP_01, 27, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("SPEED")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.beginnerDraw,
                new DrDaveDemo(PackageName.VEPP_01, 41, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.ringAroundTheSide,
                new DrDaveDemo(PackageName.VEPP_01, 47, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.circleOfBalls,
                new DrDaveDemo(PackageName.VEPP_01, 48, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW"),
                Tag.findOrCreate("STUN")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.drawToCushion,
                new DrDaveDemo(PackageName.VEPP_01, 49, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.raceToCushion,
                new DrDaveDemo(PackageName.VEPP_01, 50, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.repeatedDraw,
                new DrDaveDemo(PackageName.VEPP_01, 51, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.followAndDrawIntoSide,
                new DrDaveDemo(PackageName.VEPP_01, 52, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("CHALLENGE"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("DRAW"),
                Tag.findOrCreate("FOLLOW")));

        dfs.add(new DrillFactory(PackageName.VEPP_01, DrillName.runThroughStunBack,
                new DrDaveDemo(PackageName.VEPP_01, 53, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_01"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("CONTROL"),
                Tag.findOrCreate("STUN")));

        return dfs;
    }
}
