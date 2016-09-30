package com.obdobion.billiardsFX.model.demos;

import com.obdobion.billiardsFX.model.drills.IDemonstration;

/**
 * <p>
 * YoutubeVideo class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class YoutubeVideo implements IDemonstration
{
    final IDemonstration fallbackDemo;
    final String         videoID;

    /**
     * <p>
     * Constructor for YoutubeVideo.
     * </p>
     *
     * @param p_videoID is the youtube videoID. When calling this constructor,
     *            use
     *            {@link com.obdobion.billiardsFX.model.DrillMaster#resolveResourceReference(String)}
     *            to get a language specific video ID.
     * @param fallbackDemoWhenYoutubeIsNotAvailable a
     *            {@link com.obdobion.billiardsFX.model.drills.IDemonstration} object.
     */
    public YoutubeVideo(final String p_videoID, final IDemonstration fallbackDemoWhenYoutubeIsNotAvailable)
    {
        videoID = p_videoID;
        fallbackDemo = fallbackDemoWhenYoutubeIsNotAvailable;
    }
}
