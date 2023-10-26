package org.navistack.framework.captcha.simplecaptcha.imagefilters;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.captcha.simplecaptcha.ImageFilter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * Adds border to an image.
 *
 * <p>Originally authored by <a href="https://code.google.com/archive/p/kaptcha/">kaptcha</a>
 */
public class BorderImageFilter implements ImageFilter {
    @Getter
    @Setter
    @NonNull
    private Color borderColor = Color.BLACK;

    @Getter
    @Setter
    private int borderThickness = 1;

    @Override
    public BufferedImage apply(BufferedImage image) {
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(borderColor);

        if (borderThickness != 1) {
            BasicStroke stroke = new BasicStroke((float) borderThickness);
            graphics.setStroke(stroke);
        }


        int width = image.getWidth();
        int height = image.getHeight();

        Line2D line1 = new Line2D.Double(0, 0, 0, width);
        graphics.draw(line1);
        Line2D line2 = new Line2D.Double(0, 0, width, 0);
        graphics.draw(line2);
        line2 = new Line2D.Double(0, height - 1, width, height - 1);
        graphics.draw(line2);
        line2 = new Line2D.Double(width - 1, height - 1, width - 1, 0);
        graphics.draw(line2);

        return image;
    }
}
