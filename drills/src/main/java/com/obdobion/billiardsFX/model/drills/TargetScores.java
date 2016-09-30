package com.obdobion.billiardsFX.model.drills;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * TargetScores class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TargetScores implements Map<SkillLevel, Integer>
{
    final private SkillLevel               maximumSkillLevel;
    final private SkillLevel               minimumSkillLevel;
    final private ScoreType                scoreType;
    final private Map<SkillLevel, Integer> skillScore = new HashMap<>();

    /**
     * <p>
     * Constructor for TargetScores.
     * </p>
     *
     * @param min a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     * @param max a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     */
    public TargetScores(final SkillLevel min, final SkillLevel max)
    {
        scoreType = ScoreType.ASC;
        put(min, 0);
        minimumSkillLevel = min;
        maximumSkillLevel = max;
    }

    /**
     * <p>
     * Constructor for TargetScores.
     * </p>
     *
     * @param min a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     * @param max a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     * @param p_scoreType a {@link com.obdobion.billiardsFX.model.drills.ScoreType}
     *            object.
     */
    public TargetScores(final SkillLevel min, final SkillLevel max, final ScoreType p_scoreType)
    {
        scoreType = p_scoreType;
        put(min, 0);
        minimumSkillLevel = min;
        maximumSkillLevel = max;
    }

    /**
     * <p>
     * add.
     * </p>
     *
     * @param skill a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     * @param score a {@link java.lang.Integer} object.
     * @return a {@link com.obdobion.billiardsFX.model.drills.TargetScores} object.
     */
    public TargetScores add(final SkillLevel skill, final Integer score)
    {
        skillScore.put(skill, score);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * clear.
     * </p>
     */
    @Override
    public void clear()
    {
        skillScore.clear();
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsKey(final Object key)
    {
        return skillScore.containsKey(key);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsValue(final Object value)
    {
        return skillScore.containsValue(value);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * entrySet.
     * </p>
     */
    @Override
    public Set<java.util.Map.Entry<SkillLevel, Integer>> entrySet()
    {
        return skillScore.entrySet();
    }

    /** {@inheritDoc} */
    @Override
    public Integer get(final Object key)
    {
        return skillScore.get(key);
    }

    /**
     * <p>
     * Getter for the field <code>maximumSkillLevel</code>.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     */
    public SkillLevel getMaximumSkillLevel()
    {
        return maximumSkillLevel;
    }

    /**
     * <p>
     * Getter for the field <code>minimumSkillLevel</code>.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     */
    public SkillLevel getMinimumSkillLevel()
    {
        return minimumSkillLevel;
    }

    /**
     * <p>
     * Getter for the field <code>scoreType</code>.
     * </p>
     *
     * @return a {@link com.obdobion.billiardsFX.model.drills.ScoreType} object.
     */
    public ScoreType getScoreType()
    {
        return scoreType;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * isEmpty.
     * </p>
     */
    @Override
    public boolean isEmpty()
    {
        return skillScore.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * keySet.
     * </p>
     */
    @Override
    public Set<SkillLevel> keySet()
    {
        return skillScore.keySet();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * put.
     * </p>
     */
    @Override
    public Integer put(final SkillLevel key, final Integer value)
    {
        return skillScore.put(key, value);
    }

    /** {@inheritDoc} */
    @Override
    public void putAll(final Map<? extends SkillLevel, ? extends Integer> m)
    {
        skillScore.putAll(m);
    }

    /** {@inheritDoc} */
    @Override
    public Integer remove(final Object key)
    {
        return skillScore.remove(key);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * size.
     * </p>
     */
    @Override
    public int size()
    {
        return skillScore.size();
    }

    /**
     * <p>
     * skillLevelForScore.
     * </p>
     *
     * @param averageScore a int.
     * @return a {@link com.obdobion.billiardsFX.model.drills.SkillLevel} object.
     */
    public SkillLevel skillLevelForScore(final int averageScore)
    {
        SkillLevel maxAttainedLevelSoFar = SkillLevel.BEGINNER;
        for (final SkillLevel level : SkillLevel.values())
        {
            final Integer minScoreForLevel = get(level);
            if (minScoreForLevel == null)
                continue;
            if (averageScore < minScoreForLevel)
                break;
            maxAttainedLevelSoFar = level;
        }
        return maxAttainedLevelSoFar;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * values.
     * </p>
     */
    @Override
    public Collection<Integer> values()
    {
        return skillScore.values();
    }
}
