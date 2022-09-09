package org.navistack.framework.captcha.simplecaptcha.imagefilters;

import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.TransformFilter;
import com.jhlabs.image.WaterFilter;
import org.navistack.framework.captcha.simplecaptcha.ImageFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Adds water ripple effect to an image.
 * <p>
 * Originally authored by <a href="https://code.google.com/archive/p/kaptcha/">kaptcha</a>
 */
public class WaterRippleImageFilter implements ImageFilter {
    /**
     * Applies distortion by adding water ripple effect.
     *
     * @param image the base image
     * @return the distorted image
     */
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage distortedImage = new BufferedImage(image.getWidth(),
                image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = (Graphics2D) distortedImage.getGraphics();

        RippleFilter rippleFilter = new RippleFilter();
        rippleFilter.setWaveType(RippleFilter.SINE);
        rippleFilter.setXAmplitude(2.6f);
        rippleFilter.setYAmplitude(1.7f);
        rippleFilter.setXWavelength(15);
        rippleFilter.setYWavelength(5);
        rippleFilter.setEdgeAction(TransformFilter.NEAREST_NEIGHBOUR);

        WaterFilter waterFilter = new WaterFilter();
        waterFilter.setAmplitude(1.5f);
        waterFilter.setPhase(10);
        waterFilter.setWavelength(2);

        BufferedImage effectImage = waterFilter.filter(image, null);
        effectImage = rippleFilter.filter(effectImage, null);

        graphics.drawImage(effectImage, 0, 0, null, null);

        graphics.dispose();

        return distortedImage;
    }
}
