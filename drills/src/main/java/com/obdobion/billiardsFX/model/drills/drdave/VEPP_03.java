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
public class VEPP_03 extends AbstractDrillPackage
{

    /** {@inheritDoc} */
    @Override
    protected List<DrillFactory> drillFactories()
    {
        final List<DrillFactory> dfs = new ArrayList<>();

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.progressiveNineBall1,
                new DrDaveDemo(PackageName.VEPP_03, 91, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.progressiveNineBall2,
                new DrDaveDemo(PackageName.VEPP_03, 92, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.progressiveEightBall1,
                new DrDaveDemo(PackageName.VEPP_03, 93, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.progressiveEightBall2,
                new DrDaveDemo(PackageName.VEPP_03, 94, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern101A,
                new DrDaveDemo(PackageName.VEPP_03, 96, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern101B,
                new DrDaveDemo(PackageName.VEPP_03, 97, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern101C,
                new DrDaveDemo(PackageName.VEPP_03, 98, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern101D,
                new DrDaveDemo(PackageName.VEPP_03, 99, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern102A,
                new DrDaveDemo(PackageName.VEPP_03, 100, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern102B,
                new DrDaveDemo(PackageName.VEPP_03, 101, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern102C,
                new DrDaveDemo(PackageName.VEPP_03, 102, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern102D,
                new DrDaveDemo(PackageName.VEPP_03, 103, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern103A,
                new DrDaveDemo(PackageName.VEPP_03, 104, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern103B,
                new DrDaveDemo(PackageName.VEPP_03, 105, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern103C,
                new DrDaveDemo(PackageName.VEPP_03, 106, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.nineBallPattern103D,
                new DrDaveDemo(PackageName.VEPP_03, 107, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("9BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern81A,
                new DrDaveDemo(PackageName.VEPP_03, 108, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern81B,
                new DrDaveDemo(PackageName.VEPP_03, 109, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern81C,
                new DrDaveDemo(PackageName.VEPP_03, 110, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern81D,
                new DrDaveDemo(PackageName.VEPP_03, 111, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern82A,
                new DrDaveDemo(PackageName.VEPP_03, 112, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern82B,
                new DrDaveDemo(PackageName.VEPP_03, 113, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern82C,
                new DrDaveDemo(PackageName.VEPP_03, 114, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern82D,
                new DrDaveDemo(PackageName.VEPP_03, 115, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern83A,
                new DrDaveDemo(PackageName.VEPP_03, 116, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern83B,
                new DrDaveDemo(PackageName.VEPP_03, 117, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern83C,
                new DrDaveDemo(PackageName.VEPP_03, 118, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.eightBallPattern83D,
                new DrDaveDemo(PackageName.VEPP_03, 119, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("PATTERN"),
                Tag.findOrCreate("8BALL")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.lateralHideSafety,
                new DrDaveDemo(PackageName.VEPP_03, 121, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("SAFETY")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.angledHideSafety,
                new DrDaveDemo(PackageName.VEPP_03, 122, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("SAFETY")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.natAngleEqSeparationSafety,
                new DrDaveDemo(PackageName.VEPP_03, 122, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("SAFETY")));

        dfs.add(new DrillFactory(PackageName.VEPP_03, DrillName.hideBehindTargetSafety,
                new DrDaveDemo(PackageName.VEPP_03, 121, ""),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.ADVANCED),
                Tag.findOrCreate("VEPP_03"),
                Tag.findOrCreate("SKILL"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("SAFETY")));

        return dfs;
    }
}
