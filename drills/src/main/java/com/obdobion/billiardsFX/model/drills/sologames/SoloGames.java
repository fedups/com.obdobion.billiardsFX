package com.obdobion.billiardsFX.model.drills.sologames;

import java.util.ArrayList;
import java.util.List;

import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.Tag;
import com.obdobion.billiardsFX.model.ReservedNames.DrillName;
import com.obdobion.billiardsFX.model.ReservedNames.PackageName;
import com.obdobion.billiardsFX.model.demos.HTMLInstructions;
import com.obdobion.billiardsFX.model.demos.NoDemonstrationAvailable;
import com.obdobion.billiardsFX.model.demos.YoutubeVideo;
import com.obdobion.billiardsFX.model.drills.AbstractDrillPackage;
import com.obdobion.billiardsFX.model.drills.DrillFactory;
import com.obdobion.billiardsFX.model.drills.ScoreType;
import com.obdobion.billiardsFX.model.drills.SkillLevel;
import com.obdobion.billiardsFX.model.drills.TargetScores;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall;

/**
 * A factory class for creating solo game drills.
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class SoloGames extends AbstractDrillPackage
{
    /** {@inheritDoc} */
    @Override
    protected List<DrillFactory> drillFactories()
    {
        final List<DrillFactory> dfs = new ArrayList<>();
        dfs.add(new DrillFactory(PackageName.SOLOGAMES, DrillName.bowlliards,
                new YoutubeVideo(
                        DrillMaster.resolveResourceReference("BowlliardsYoutubeVideoID"),
                        new HTMLInstructions(DrillMaster.resolveResourceReference("BowlliardsInstructionFileName"))),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.PRO)
                        .add(SkillLevel.INTERMEDIATE, 80)
                        .add(SkillLevel.ADVANCED, 120)
                        .add(SkillLevel.EXPERT, 190)
                        .add(SkillLevel.PRO, 220),
                Tag.findOrCreate("SOLO_GAMES"),
                Tag.findOrCreate("GAME"),
                Tag.findOrCreate("STRATEGY")));
        dfs.add(new DrillFactory(PackageName.SOLOGAMES, DrillName.threeball,
                new NoDemonstrationAvailable(),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.PRO, ScoreType.DESC)
                        .add(SkillLevel.BEGINNER, 6 * ThreeBall.InningsPerGame)
                        .add(SkillLevel.INTERMEDIATE, 5 * ThreeBall.InningsPerGame)
                        .add(SkillLevel.ADVANCED, 4 * ThreeBall.InningsPerGame)
                        .add(SkillLevel.EXPERT, 3 * ThreeBall.InningsPerGame)
                        .add(SkillLevel.PRO, 2 * ThreeBall.InningsPerGame),
                Tag.findOrCreate("SOLO_GAMES"),
                Tag.findOrCreate("GAME"),
                Tag.findOrCreate("STRATEGY")));
        dfs.add(new DrillFactory(PackageName.SOLOGAMES, DrillName.bacaball,
                new HTMLInstructions(DrillMaster.resolveResourceReference("BacaBallInstructionFileName")),
                new TargetScores(SkillLevel.BEGINNER, SkillLevel.PRO)
                        .add(SkillLevel.INTERMEDIATE, 3)
                        .add(SkillLevel.ADVANCED, 5)
                        .add(SkillLevel.EXPERT, 7)
                        .add(SkillLevel.PRO, 9),
                Tag.findOrCreate("SOLO_GAMES"),
                Tag.findOrCreate("GAME"),
                Tag.findOrCreate("STRATEGY"),
                Tag.findOrCreate("ROTATION")));

        return dfs;
    }
}
