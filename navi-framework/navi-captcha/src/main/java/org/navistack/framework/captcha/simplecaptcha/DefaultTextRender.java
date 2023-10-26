package org.navistack.framework.captcha.simplecaptcha;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Creates an image with text rendered on it.
 *
 * <p>Originally authored by <a href="https://code.google.com/archive/p/kaptcha/">kaptcha</a>
 */
public class DefaultTextRender extends FilteringTextRender implements TextRender {
    @Getter
    @Setter
    private int fontSize = 40;

    @Getter
    @Setter
    @NonNull
    private Font[] fonts = new Font[]{
            new Font("Arial", Font.BOLD, fontSize),
            new Font("Courier", Font.BOLD, fontSize)
    };

    @Getter
    @Setter
    @NonNull
    private Color color = Color.BLACK;

    @Getter
    @Setter
    private int charSpace = 2;

    /**
     * The width of the image to be created.
     */
    @Getter
    @Setter
    private int width = 200;

    /**
     * The height of the image to be created.
     */
    @Getter
    @Setter
    private int height = 50;

    @Getter
    @Setter
    @NonNull
    private RandomGenerator random = new Random();

    /**
     * Renders a text to an image.
     *
     * @param text The text to be rendered.
     * @return The BufferedImage created from the text.
     */
    @Override
    public BufferedImage doRender(String text) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = image.createGraphics();
        g2D.setColor(color);

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        g2D.setRenderingHints(hints);

        FontRenderContext frc = g2D.getFontRenderContext();


        int startPosY = (height - fontSize) / 5 + fontSize;

        char[] wordChars = text.toCharArray();
        Font[] chosenFonts = new Font[wordChars.length];
        int[] charWidths = new int[wordChars.length];
        int widthNeeded = 0;
        for (int i = 0; i < wordChars.length; i++) {
            chosenFonts[i] = fonts[random.nextInt(fonts.length)];

            char[] charToDraw = new char[]{
                    wordChars[i]
            };
            GlyphVector gv = chosenFonts[i].createGlyphVector(frc, charToDraw);
            charWidths[i] = (int) gv.getVisualBounds().getWidth();
            if (i > 0) {
                widthNeeded = widthNeeded + 2;
            }
            widthNeeded = widthNeeded + charWidths[i];
        }

        int startPosX = (width - widthNeeded) / 2;
        for (int i = 0; i < wordChars.length; i++) {
            g2D.setFont(chosenFonts[i]);
            char[] charToDraw = new char[]{
                    wordChars[i]
            };
            g2D.drawChars(charToDraw, 0, charToDraw.length, startPosX, startPosY);
            startPosX = startPosX + (int) charWidths[i] + charSpace;
        }

        return image;
    }
}
