package org.navistack.framework.captcha.simplecaptcha.imagefilters;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.captcha.simplecaptcha.ImageFilter;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Adds a gradient background to an image.
 * The gradient color is diagonal and made of Color From (top left) and Color To (bottom right).
 *
 * <p>Originally authored by <a href="https://code.google.com/archive/p/kaptcha/">kaptcha</a>
 */
public class GradientBackgroundImageFilter implements ImageFilter {
    @Getter
    @Setter
    @NonNull
    private Color color1 = Color.LIGHT_GRAY;

    @Getter
    @Setter
    @NonNull
    private Color color2 = Color.WHITE;

    @Override
    public BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // create an opaque image
        BufferedImage imageWithBackground = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        hints.add(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY));
        hints.add(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));

        hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));

        Graphics2D graph = (Graphics2D) imageWithBackground.getGraphics();

        graph.setRenderingHints(hints);

        GradientPaint paint = new GradientPaint(0, 0, color1, width, height,
                color2);
        graph.setPaint(paint);
        graph.fill(new Rectangle2D.Double(0, 0, width, height));

        // draw the transparent image over the background
        graph.drawImage(image, 0, 0, null);

        return imageWithBackground;
    }
}
