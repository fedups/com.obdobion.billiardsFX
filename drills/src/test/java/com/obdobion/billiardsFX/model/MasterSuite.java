package com.obdobion.billiardsFX.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * MasterSuite class.
 * </p>
 *
 * @since 0.0.1
 * @author Chris DeGreef fedupforone@gmail.com
 */
@RunWith(Suite.class)
@SuiteClasses({
        TestBowlliardsAnalysis.class,
        TestBowlliards.class,
        TestThreeBallAnalysis.class,
        TestThreeBall.class,
        TestBacaBall.class,
        TestBacaBallAnalysis.class,
        TestProgressivePractice.class,
        TestProgressivePracticeAnalysis.class,
        TestUndo.class,
        TestXAxisLabels.class,
        TestSelectionList.class,
        TestAllVEPPDisks.class,
        TestNotes.class,
        TestTags.class
})
public class MasterSuite
{
    //
}
