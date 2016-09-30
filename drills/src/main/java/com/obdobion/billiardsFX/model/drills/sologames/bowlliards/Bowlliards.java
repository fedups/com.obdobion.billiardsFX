package com.obdobion.billiardsFX.model.drills.sologames.bowlliards;

import java.text.ParseException;

import com.obdobion.billiardsFX.model.DrillMaster;
import com.obdobion.billiardsFX.model.drills.AbstractDrill;

/**
 * <h1>How to Play Bowlliards</h1>
 *
 * <h2>Rules for BOWLLIARDS</h2>
 *
 * Except where clearly contradicted by these additional rules, these apply.
 *
 * <h3>TYPE OF GAME</h3>
 *
 * Bowlliards is a game that applies the scoring concepts of bowling to pocket
 * billiards. It is one of the few games that can be quite useful as a training
 * exercise since, like bowling, there is a perfect game score to strive toward,
 * and a player can measure his/her improvement quite easily over the course of
 * time playing Bowlliards.
 *
 * <h3>OBJECT OF THE GAME</h3>
 *
 * To score a perfect score of 300 points in 10 frames in solitary play. In
 * competition, to score a higher point total in 10 frames than your
 * opponent(s).
 *
 * <h3>SCORING</h3>
 *
 * The score keeping is very easy to follow. If you have ever bowled, you know
 * how to keep score in Bowlliards. Each legally pocketed ball is scored as one
 * point, regardless of ball number. Balls do not have to be pocketed in any
 * particular order. The points scored as per the "Rules of Play" below are
 * treated exactly as is the pinfall in bowling.
 *
 * <h3>PLAYERS</h3>
 *
 * Any number of players (for reasonable time constraints figure 4 to 6
 * players).
 *
 * <h3>BALLS USED</h3>
 *
 * Any 10 object balls, plus cue ball.
 *
 * <h3>THE RACK</h3>
 *
 * Balls are racked in a triangle, standard rack position, head ball on foot
 * spot.
 *
 * <h3>OPENING BREAK</h3>
 *
 * At the start of each of a player�s frames, he has a free break (no special
 * balls-to-cushion or other requirements once break stroke play commences, and
 * a jumped or scratched cue ball is without penalty). Any balls pocketed or
 * jumped on the break are spotted, and the player then follows his break by
 * beginning scoring play with object balls in position and cue ball in hand.
 * The opening break takes place at the start of every frame.
 *
 * <h4>HINT:</h4> Unlike most games, pocketing balls on the break can be
 * detrimental due to the fact that those balls get spotted and may cause
 * congestion near the foot spot.
 *
 * <h3>RULES OF PLAY</h3>
 *
 * A legally pocketed ball entitles the shooter to continue at the table until
 * he fails to pocket a called ball on a shot, or until he has scored the
 * maximum total per frame possible (10). Player may shoot any ball he chooses,
 * but before he shoots, must designate a single ball that he will pocket and
 * the pocket into which the ball will score; he need not indicate banks,
 * kisses, caroms, combinations or cushions (none of which are illegal). Player
 * has two innings to pocket the 10 possible balls for each frame. If player
 * legally pockets 10 consecutive balls on his first inning of a frame, that
 * frame is completed and player scores the frame exactly as a strike in
 * bowling. If player fails to pocket 10 consecutive balls on his first inning,
 * he takes his second inning immediately from where the cue ball lies. If he
 * succeeds in legally pocketing the remaining balls on the table in his second
 * inning, the frame is completed and player scores exactly as a spare in
 * bowling. If player fails to legally pocket all 10 balls in 2 innings, the
 * frame is then completed and is scored as an open frame in bowling. All
 * succeeding frames are likewise scored just as in bowling; a "strike" in the
 * 10th frame earns 2 extra innings, a "spare" earns 1 extra inning. If players
 * tie for high game total in competition, additional frames are played
 * alternately by the tied players, with the first player posting a superior
 * score to that of his opponent(s) being the winner of that match ("sudden
 * death").
 *
 * <h3>ILLEGALLY POCKETED BALLS</h3>
 *
 * On the break, all pocketed balls are spotted prior to the player beginning
 * his scoring play (first inning of frame). During scoring play, incidentally
 * pocketed balls are spotted.
 *
 * <h3>JUMPED OBJECT BALLS</h3>
 *
 * All spotted; no penalty.
 *
 * <h3>PENALTY AFTER FOUL</h3>
 *
 * No points are deducted (note that balls pocketed on a foul stroke do not
 * count and must be spotted). If the foul ends player�s first inning of a
 * frame, he has cue ball in hand to begin his second inning of the frame. If
 * the foul ends his second inning then his frame is finished.
 *
 * <h3>HAVE FUN!!!</h3>
 *
 * Bowlliards.com wants the same sense of equality for bowlliards players and
 * teams as The United States Bowling Congress (USBC), the sanctioning body for
 * bowling, does for their bowlers and bowling teams. Therefore we have decided
 * to use the exact same handicapping system as the means of placing bowlliards
 * players and teams with varying degrees of skill on as equitable a basis as
 * possible for competition against each other. The object of a league is to
 * enjoy yourself and have some fun with your teammates and with others in your
 * league community. Handicap leagues usually bring out goodwill in most of the
 * players and an atmosphere of friendly competition emerges. Playing in
 * handicap tournaments can be rewarding both by remaining competitive because
 * of the handicap system and because of the enjoyment brought forth by the
 * spirit of competition.
 *
 * @author degreefc
 */
public class Bowlliards extends AbstractDrill
{

    public int          currentFrame  = 0;
    public int          currentInning = 0;
    private final Frame frame[];
    public int          potsOnBreak;
    public boolean      scratchOnBreak;

    /**
     * <p>
     * Constructor for Bowlliards.
     * </p>
     */
    public Bowlliards()
    {
        super();
        frame = new Frame[12];
        for (int f = 0; f < 12; f++)
            frame[f] = new Frame(f);
        DrillMaster.notifyDrillCreatedObservers(getContext(), this);
    }

    /**
     * <p>
     * frame.
     * </p>
     *
     * @param f a int.
     * @return a
     *         {@link com.obdobion.billiardsFX.model.drills.sologames.bowlliards.Frame}
     *         object.
     */
    public Frame frame(final int f)
    {
        return frame[f];
    }

    /**
     * <p>
     * frameMissCount.
     * </p>
     *
     * @param f a int.
     * @return a int.
     */
    public int frameMissCount(final int f)
    {
        return (frame(f).inning(0).isMiss()
                ? 1
                : 0) + (frame(f).inning(1).isMiss()
                        ? 1
                        : 0);
    }

    /**
     * <p>
     * frameScore.
     * </p>
     *
     * @param f a int.
     * @return a int.
     */
    public int frameScore(final int f)
    {
        return frame(f).inning(0).getPots() + frame(f).inning(1).getPots();
    }

    /**
     * <p>
     * frameScratchCount.
     * </p>
     *
     * @param f a int.
     * @return a int.
     */
    public int frameScratchCount(final int f)
    {
        return (frame(f).inning(0).isScratch()
                ? 1
                : 0) + (frame(f).inning(1).isScratch()
                        ? 1
                        : 0);
    }

    /** {@inheritDoc} */
    @Override
    public String gameData()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(potsOnBreak);
        sb.append(",");
        sb.append(externalize(scratchOnBreak));
        sb.append(",");
        sb.append(currentFrame);
        sb.append(",");
        sb.append(currentInning);
        for (int f = 0; f < 12; f++)
        {
            sb.append(",");
            sb.append(f);
            sb.append(",");
            sb.append(frame(f).inning(0).getPots());
            sb.append(",");
            sb.append(externalize(frame(f).inning(0).isMiss()));
            sb.append(",");
            sb.append(externalize(frame(f).inning(0).isScratch()));
            sb.append(",");
            sb.append(externalize(frame(f).inning(0).isComplete()));
            sb.append(",");
            sb.append(frame(f).inning(1).getPots());
            sb.append(",");
            sb.append(externalize(frame(f).inning(1).isMiss()));
            sb.append(",");
            sb.append(externalize(frame(f).inning(1).isScratch()));
            sb.append(",");
            sb.append(externalize(frame(f).inning(1).isComplete()));
        }
        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void gameData(final String[] fields, final int startIndex) throws ParseException
    {
        int fieldIndex = startIndex;
        try
        {
            potsOnBreak = Integer.parseInt(fields[fieldIndex++]);
        } catch (final NumberFormatException e)
        {
            throw new ParseException("invalid pots on break " + e.getMessage(), 2);
        }
        scratchOnBreak = internalizeBoolean(fields[fieldIndex++]);
        try
        {
            currentFrame = Integer.parseInt(fields[fieldIndex++]);
        } catch (final NumberFormatException e)
        {
            throw new ParseException("invalid current frame " + e.getMessage(), 4);
        }
        try
        {
            currentInning = Integer.parseInt(fields[fieldIndex++]);
        } catch (final NumberFormatException e)
        {
            throw new ParseException("invalid current inning " + e.getMessage(), 5);
        }

        final int start = fieldIndex - 1;
        for (int f = 0; f < 12; f++)
        {
            final int offset = f * 9;
            try
            {
                final int fNumber = Integer.parseInt(fields[start + offset + 1]);
                if (fNumber != f)
                    throw new ParseException("out of sequence frame: " + fNumber, start + offset + 1);
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid frame number " + e.getMessage(), start + offset + 1);
            }
            try
            {
                frame(f).inning(0).setPots(Integer.parseInt(fields[start + offset + 2]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid pots " + e.getMessage(), start + offset + 2);
            }
            frame(f).inning(0).setMiss(internalizeBoolean(fields[start + offset + 3]));
            frame(f).inning(0).setScratch(internalizeBoolean(fields[start + offset + 4]));
            frame(f).inning(0).setComplete(internalizeBoolean(fields[start + offset + 5]));
            try
            {
                frame(f).inning(1).setPots(Integer.parseInt(fields[start + offset + 6]));
            } catch (final NumberFormatException e)
            {
                throw new ParseException("invalid pots " + e.getMessage(), start + offset + 6);
            }
            frame(f).inning(1).setMiss(internalizeBoolean(fields[start + offset + 7]));
            frame(f).inning(1).setScratch(internalizeBoolean(fields[start + offset + 8]));
            frame(f).inning(1).setComplete(internalizeBoolean(fields[start + offset + 9]));
        }
    }

    /** {@inheritDoc} */
    @Override
    public int gameDataVersion()
    {
        return 1;
    }

    /**
     * <p>
     * highestFrameInGame.
     * </p>
     *
     * @return a int.
     */
    public int highestFrameInGame()
    {
        if (frame(9).isStrike() && frame(10).isStrike())
            return 11;
        if (frame(9).isStrike() || frame(9).isSpare())
            return 10;
        return 9;
    }

    /**
     * <p>
     * inningScore.
     * </p>
     *
     * @param f a int.
     * @param i a int.
     * @return a int.
     */
    public int inningScore(final int f, final int i)
    {
        return frame(f).inning(i).getPots();
    }

    /**
     * <p>
     * isComplete.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isComplete()
    {
        if (frame(9).isSpare())
            return currentFrame == highestFrameInGame() && currentInning == 1;
        if (frame(9).isStrike())
            return currentFrame > highestFrameInGame() && currentInning == 0;
        return currentFrame > 9;
    }

    /** {@inheritDoc} */
    @Override
    public String[] readFrom(final String externalRepresentation) throws ParseException
    {
        final String fields[] = super.readFrom(externalRepresentation);
        if (fields.length != 115)
            throw new ParseException("115 fields required, found " + fields.length, fields.length);
        if (!(getDrillId().equals(fields[0])))
            throw new ParseException("wrong drill: " + fields[0], 0);
        if (!("1".equals(fields[1])))
            throw new ParseException("unsupported format version: " + fields[1], 1);

        gameData(fields, 2);
        noteFromDiskFormat(fields[114]);
        return fields;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * score.
     * </p>
     */
    @Override
    public int score()
    {
        return score(currentFrame);
    }

    /**
     * <p>
     * score.
     * </p>
     *
     * @param throughFrameNumber a int.
     * @return a int.
     */
    public int score(final int throughFrameNumber)
    {
        int score = 0;
        int frameScore = 0;
        for (int f = 0; f <= throughFrameNumber && f <= 9; f++)
        {
            frameScore = frame(f).inning(0).getPots() + frame(f).inning(1).getPots();
            score += frameScore;
            if (frame(f).isStrike())
            {
                final int firstInningScore = frame(f + 1).inning(0).getPots();
                score += firstInningScore;
                if (frame(f + 1).isStrike())
                {
                    score += frame(f + 2).inning(0).getPots();
                } else
                    score += frame(f + 1).inning(1).getPots();
            } else if (frame(f).isSpare())
                score += frame(f + 1).inning(0).getPots();
        }
        return score;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" currentFrame(").append(currentFrame).append(")");
        sb.append(" currentInning(").append(currentInning).append(")");
        sb.append(" score(").append(score()).append(")");
        return sb.toString();
    }
}
