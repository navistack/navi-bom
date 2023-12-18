package org.navistack.framework.captcha.simplecaptcha;

import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class FilteringTextRender implements TextRender {
    @Getter
    private final Collection<ImageFilter> imageFilters = new ArrayList<>(4);

    @Override
    public BufferedImage render(String text) {
        BufferedImage image = doRender(text);
        return applyImageFilters(image);
    }

    public abstract BufferedImage doRender(String text);

    public void addImageFilter(ImageFilter imageFilter) {
        this.imageFilters.add(imageFilter);
    }

    public void addImageFilter(ImageFilter... imageFilters) {
        if (imageFilters != null) {
            Collections.addAll(this.imageFilters, imageFilters);
        }
    }

    public void addImageFilters(Collection<? extends ImageFilter> imageFilters) {
        this.imageFilters.addAll(imageFilters);
    }

    public BufferedImage applyImageFilters(BufferedImage image) {
        if (imageFilters.isEmpty()) {
            return image;
        }

        BufferedImage result = image;
        for (ImageFilter imageFilter : imageFilters) {
            result = imageFilter.apply(result);
        }
        return result;
    }
}
