package com.obdobion.billiardsFX.model;

import com.obdobion.billiardsFX.model.drills.IDrill;
import com.obdobion.billiardsFX.model.drills.drdave.SingleScoreDrill;
import com.obdobion.billiardsFX.model.drills.sologames.bacaball.BacaBall;
import com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Bowlliards;
import com.obdobion.billiardsFX.model.drills.sologames.threeball.ThreeBall;

/**
 * <p>ReservedNames class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class ReservedNames
{
    static public enum DrillName
    {
        ppcut1dl("ppcut1dl", SingleScoreDrill.class),
        ppcut1dr("ppcut1dr", SingleScoreDrill.class),
        ppcut2dl("ppcut2dl", SingleScoreDrill.class),
        ppcut2dr("ppcut2dr", SingleScoreDrill.class),
        ppcut3dl("ppcut3dl", SingleScoreDrill.class),
        ppcut3dr("ppcut3dr", SingleScoreDrill.class),
        ppcut4dl("ppcut4dl", SingleScoreDrill.class),
        ppcut4dr("ppcut4dr", SingleScoreDrill.class),
        ppcut5dl("ppcut5dl", SingleScoreDrill.class),
        ppcut5dr("ppcut5dr", SingleScoreDrill.class),
        ppstop1a("ppstop1a", SingleScoreDrill.class),
        ppstop2a("ppstop2a", SingleScoreDrill.class),
        ppstop3a("ppstop3a", SingleScoreDrill.class),
        ppfollow1b("ppfollow1b", SingleScoreDrill.class),
        ppfollow2b("ppfollow2b", SingleScoreDrill.class),
        ppfollow3b("ppfollow3b", SingleScoreDrill.class),
        ppfollow4b("ppfollow4b", SingleScoreDrill.class),
        ppfollow5b("ppfollow5b", SingleScoreDrill.class),
        ppdraw1c("ppdraw1c", SingleScoreDrill.class),
        ppdraw2c("ppdraw2c", SingleScoreDrill.class),
        ppdraw3c("ppdraw3c", SingleScoreDrill.class),
        ppdraw4c("ppdraw4c", SingleScoreDrill.class),
        ppdraw5c("ppdraw5c", SingleScoreDrill.class),
        pprailcutl("pprailcutl", SingleScoreDrill.class),
        pprailcutr("pprailcutr", SingleScoreDrill.class),
        lag("lag", SingleScoreDrill.class),
        successiveTouchShot("successiveTouchShot", SingleScoreDrill.class),
        repeatedTouchShot("repeatedTouchShot", SingleScoreDrill.class),
        pocketSpeed("pocketSpeed", SingleScoreDrill.class),
        angledPocketSpeed("angledPocketSpeed", SingleScoreDrill.class),
        beginnerDraw("beginnerDraw", SingleScoreDrill.class),
        ringAroundTheSide("ringAroundTheSide", SingleScoreDrill.class),
        circleOfBalls("circleOfBalls", SingleScoreDrill.class),
        drawToCushion("drawToCushion", SingleScoreDrill.class),
        raceToCushion("raceToCushion", SingleScoreDrill.class),
        repeatedDraw("repeatedDraw", SingleScoreDrill.class),
        followAndDrawIntoSide("followAndDrawIntoSide", SingleScoreDrill.class),
        runThroughStunBack("runThroughStunBack", SingleScoreDrill.class),
        ppstun4a("ppstun4a", SingleScoreDrill.class),
        ppstun5a("ppstun5a", SingleScoreDrill.class),
        ppstunhold("ppstunhold", SingleScoreDrill.class),
        thinCutSpeedControl("thinCutSpeedControl", SingleScoreDrill.class),
        repeatedFollow("repeatedFollow", SingleScoreDrill.class),
        noRailGridOfBalls("noRailGridOfBalls", SingleScoreDrill.class),
        wagonWheelBIH("wagonWheelBIH", SingleScoreDrill.class),
        wagonWheelHalfBall("wagonWheelHalfBall", SingleScoreDrill.class),
        stunFollowDrawTarget("stunFollowDrawTarget", SingleScoreDrill.class),
        englishShortRail("englishShortRail", SingleScoreDrill.class),
        englishAcrossTable("englishAcrossTable", SingleScoreDrill.class),
        insideFollow2Rail("insideFollow2Rail", SingleScoreDrill.class),
        outsideDraw2Rail("outsideDraw2Rail", SingleScoreDrill.class),
        englishWagonWheelShortRailSmallAngle("englishWagonWheelShortRailSmallAngle", SingleScoreDrill.class),
        englishWagonWheelShortRailLargeAngle("englishWagonWheelShortRailLargeAngle", SingleScoreDrill.class),
        sitCBHold("sitCBHold", SingleScoreDrill.class),
        runningEnglishRailCutShot("runningEnglishRailCutShot", SingleScoreDrill.class),
        gridTarget("gridTarget", SingleScoreDrill.class),
        breakAndRun("breakAndRun", SingleScoreDrill.class),
        pocketHanger("pocketHanger", SingleScoreDrill.class),
        oneRailTarget("oneRailTarget", SingleScoreDrill.class),
        twoRailTarget("twoRailTarget", SingleScoreDrill.class),
        threeRailTarget("threeRailTarget", SingleScoreDrill.class),
        sidePocketCOT("sidePocketCOT", SingleScoreDrill.class),
        cornerPocketCOT("cornerPocketCOT", SingleScoreDrill.class),
        pocketCheating("pocketCheating", SingleScoreDrill.class),
        footLineOfBalls("footLineOfBalls", SingleScoreDrill.class),
        progressiveL("progressiveL", SingleScoreDrill.class),
        fourBallMiddleDiamond("fourBallMiddleDiamond", SingleScoreDrill.class),
        railCutShot("railCutShot", SingleScoreDrill.class),
        headAndFootLineOfBalls("headAndFootLineOfBalls", SingleScoreDrill.class),
        progressiveNineBall1("progressiveNineBall1", SingleScoreDrill.class),
        progressiveNineBall2("progressiveNineBall2", SingleScoreDrill.class),
        progressiveEightBall1("progressiveEightBall1", SingleScoreDrill.class),
        progressiveEightBall2("progressiveEightBall2", SingleScoreDrill.class),
        nineBallPattern101A("nineBallPattern101A", SingleScoreDrill.class),
        nineBallPattern101B("nineBallPattern101B", SingleScoreDrill.class),
        nineBallPattern101C("nineBallPattern101C", SingleScoreDrill.class),
        nineBallPattern101D("nineBallPattern101D", SingleScoreDrill.class),
        nineBallPattern102A("nineBallPattern102A", SingleScoreDrill.class),
        nineBallPattern102B("nineBallPattern102B", SingleScoreDrill.class),
        nineBallPattern102C("nineBallPattern102C", SingleScoreDrill.class),
        nineBallPattern102D("nineBallPattern102D", SingleScoreDrill.class),
        nineBallPattern103A("nineBallPattern103A", SingleScoreDrill.class),
        nineBallPattern103B("nineBallPattern103B", SingleScoreDrill.class),
        nineBallPattern103C("nineBallPattern103C", SingleScoreDrill.class),
        nineBallPattern103D("nineBallPattern103D", SingleScoreDrill.class),
        eightBallPattern81A("eightBallPattern81A", SingleScoreDrill.class),
        eightBallPattern81B("eightBallPattern81B", SingleScoreDrill.class),
        eightBallPattern81C("eightBallPattern81C", SingleScoreDrill.class),
        eightBallPattern81D("eightBallPattern81D", SingleScoreDrill.class),
        eightBallPattern82A("eightBallPattern82A", SingleScoreDrill.class),
        eightBallPattern82B("eightBallPattern82B", SingleScoreDrill.class),
        eightBallPattern82C("eightBallPattern82C", SingleScoreDrill.class),
        eightBallPattern82D("eightBallPattern82D", SingleScoreDrill.class),
        eightBallPattern83A("eightBallPattern83A", SingleScoreDrill.class),
        eightBallPattern83B("eightBallPattern83B", SingleScoreDrill.class),
        eightBallPattern83C("eightBallPattern83C", SingleScoreDrill.class),
        eightBallPattern83D("eightBallPattern83D", SingleScoreDrill.class),
        lateralHideSafety("lateralHideSafety", SingleScoreDrill.class),
        angledHideSafety("angledHideSafety", SingleScoreDrill.class),
        natAngleEqSeparationSafety("natAngleEqSeparationSafety", SingleScoreDrill.class),
        hideBehindTargetSafety("hideBehindTargetSafety", SingleScoreDrill.class),
        rollingCBOneRailKickToCorner("rollingCBOneRailKickToCorner", SingleScoreDrill.class),
        rollingCBOneRailKickLineOfBalls("rollingCBOneRailKickLineOfBalls", SingleScoreDrill.class),
        kickAngleRange("kickAngleRange", SingleScoreDrill.class),
        rollingCBShallowAngleKick("rollingCBShallowAngleKick", SingleScoreDrill.class),
        firmStunShallowAngleKick("firmStunShallowAngleKick", SingleScoreDrill.class),
        lineOfBallsMirrorKick("lineOfBallsMirrorKick", SingleScoreDrill.class),
        twoCushionKickToCorner("twoCushionKickToCorner", SingleScoreDrill.class),
        twoCushionKickToRail("twoCushionKickToRail", SingleScoreDrill.class),
        tracksToCorner("tracksToCorner", SingleScoreDrill.class),
        thirdRailTargetsFromCorner("thirdRailTargetsFromCorner", SingleScoreDrill.class),
        thirdRailTargetFromDifferentPositions("thirdRailTargetFromDifferentPositions", SingleScoreDrill.class),
        repeatedKickToCorner("repeatedKickToCorner", SingleScoreDrill.class),
        perpetualKick("perpetualKick", SingleScoreDrill.class),
        lineOfBallsBank("lineOfBallsBank", SingleScoreDrill.class),
        crossSideBankCutAngle("crossSideBankCutAngle", SingleScoreDrill.class),
        crossCornerBankCutAngle("crossCornerBankCutAngle", SingleScoreDrill.class),
        bankLineOfBallsPosition("bankLineOfBallsPosition", SingleScoreDrill.class),
        bankToAllPockets("bankToAllPockets", SingleScoreDrill.class),
        jackedUpOverObsticle("jackedUpOverObsticle", SingleScoreDrill.class),
        elevatedDrawAtRail("elevatedDrawAtRail", SingleScoreDrill.class),
        progressiveGapJump("progressiveGapJump", SingleScoreDrill.class),
        progressiveHeightJump("progressiveHeightJump", SingleScoreDrill.class),
        progressiveDistanceJump("progressiveDistanceJump", SingleScoreDrill.class),
        progressiveMasse("progressiveMasse", SingleScoreDrill.class),
        softMasseSpeedControl("softMasseSpeedControl", SingleScoreDrill.class),
        playingTheGhost("playingTheGhost", SingleScoreDrill.class),
        nineBallPointsRating("nineBallPointsRating", SingleScoreDrill.class),
        nineBallBreak("nineBallBreak", SingleScoreDrill.class),
        hopkinsQSkill("hopkinsQSkill", SingleScoreDrill.class),
        fargo("fargo", SingleScoreDrill.class),
        equalOffense("equalOffense", SingleScoreDrill.class),
        safety("safety", SingleScoreDrill.class),
        loopCarom("loopCarom", SingleScoreDrill.class),
        combinationCarom("combinationCarom", SingleScoreDrill.class),
        thinHitRailCutEnglish("thinHitRailCutEnglish", SingleScoreDrill.class),
        smallAngleRailCutStun("smallAngleRailCutStun", SingleScoreDrill.class),
        runOutPracticeRedos("runOutPracticeRedos", SingleScoreDrill.class),
        bowlliards("bowlliards", Bowlliards.class),
        threeball("threeball", ThreeBall.class),
        bacaball("bacaball", BacaBall.class);

        String        displayName;
        Class<IDrill> drillClass;

        @SuppressWarnings("unchecked")
        private DrillName(final String name, final Class<?> dClass)
        {
            displayName = name;
            drillClass = (Class<IDrill>) dClass;
        }

        public String displayName()
        {
            return displayName;
        }

        public Class<IDrill> instanceClass()
        {
            return drillClass;
        }
    }

    static public enum PackageName
    {
        VEPP_01("vepp_01"),
        VEPP_02("vepp_02"),
        VEPP_03("vepp_03"),
        VEPP_04("vepp_04"),
        VEPP_05("vepp_05"),
        SOLOGAMES("sologames");

        String displayName;

        private PackageName(final String name)
        {
            displayName = name;
        }

        public String displayName()
        {
            return displayName;
        }
    }
}
