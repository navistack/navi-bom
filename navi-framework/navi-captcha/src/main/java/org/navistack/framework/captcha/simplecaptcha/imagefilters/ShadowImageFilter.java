package org.navistack.framework.captcha.simplecaptcha.imagefilters;

import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.ShadowFilter;
import com.jhlabs.image.TransformFilter;
import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.captcha.simplecaptcha.ImageFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Adds shadow to the text on the image and two noises.
 * <p>
 * Originally authored by <a href="https://code.google.com/archive/p/kaptcha/">kaptcha</a>
 */
public class ShadowImageFilter implements ImageFilter {
    @Getter
    @Setter
    private Random rand = new Random();

    /**
     * Applies distortion by adding shadow to the text and also two noises.
     *
     * @param image the base image
     * @return the distorted image
     */
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage distortedImage = new BufferedImage(image.getWidth(),
                image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graph = (Graphics2D) distortedImage.getGraphics();

        ShadowFilter shadowFilter = new ShadowFilter();
        shadowFilter.setRadius(10);
        shadowFilter.setDistance(5);
        shadowFilter.setOpacity(1);

        RippleFilter rippleFilter = new RippleFilter();
        rippleFilter.setWaveType(RippleFilter.SINE);
        rippleFilter.setXAmplitude(7.6f);
        rippleFilter.setYAmplitude(rand.nextFloat() + 1.0f);
        rippleFilter.setXWavelength(rand.nextInt(7) + 8);
        rippleFilter.setYWavelength(rand.nextInt(3) + 2);
        rippleFilter.setEdgeAction(TransformFilter.BILINEAR);

        BufferedImage effectImage = rippleFilter.filter(image, null);
        effectImage = shadowFilter.filter(effectImage, null);

        graph.drawImage(effectImage, 0, 0, null, null);
        graph.dispose();

        return distortedImage;
    }
}
