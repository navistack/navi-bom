package org.navistack.framework.captcha.simplecaptcha;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.cache.CacheKeyBuilder;
import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.PrefixedCacheKeyBuilder;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.BorderImageFilter;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.GradientBackgroundImageFilter;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.NoiseImageFilter;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.WaterRippleImageFilter;
import org.navistack.framework.captcha.simplecaptcha.textgenerators.AlphanumericTextGenerator;

import java.awt.image.RenderedImage;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DefaultSimpleCaptchaService implements SimpleCaptchaService {
    @Getter
    @Setter
    @NonNull
    private CacheKeyBuilder challengeKeyBuilder = new PrefixedCacheKeyBuilder(".", "NAVI", "SIMPLE_CAPTCHA", "CHALLENGE");

    @Getter
    @Setter
    @NonNull
    private CacheKeyBuilder ticketKeyBuilder = new PrefixedCacheKeyBuilder(".", "NAVI", "SIMPLE_CAPTCHA", "TICKET");

    @Getter
    @Setter
    @NonNull
    private TextGenerator textGenerator = new AlphanumericTextGenerator();

    @Getter
    @Setter
    @NonNull
    private TextRender textRender;

    {
        DefaultTextRender textRender = new DefaultTextRender();
        textRender.addImageFilter(new GradientBackgroundImageFilter());
        textRender.addImageFilter(new WaterRippleImageFilter());
        textRender.addImageFilter(new NoiseImageFilter());
        textRender.addImageFilter(new BorderImageFilter());
        this.textRender = textRender;
    }

    @Getter
    @Setter
    private int challengeValidity = 10 * 60 * 1000;

    @Getter
    @Setter
    private int ticketValidity = 10 * 60 * 1000;

    private final CacheService cacheService;

    public DefaultSimpleCaptchaService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public String challenge() {
        String challenge = UUID.randomUUID().toString();

        String expected = textGenerator.generate();
        cacheService.set(challengeKeyBuilder.build(challenge), expected, challengeValidity, TimeUnit.MILLISECONDS);

        return challenge;
    }

    @Override
    public UserAttemptResult answer(String challenge, String answer) {
        String ticket = UUID.randomUUID().toString();

        boolean passed = Optional.ofNullable(
                        cacheService.getAndDelete(challengeKeyBuilder.build(challenge), String.class)
                ).map(expectedAnswer -> expectedAnswer.equals(answer))
                .orElse(false);

        UserAttempt userAttempt = new UserAttempt();
        userAttempt.setAnswer(answer);
        userAttempt.setValidated(passed);

        cacheService.set(ticketKeyBuilder.build(ticket), userAttempt, ticketValidity, TimeUnit.MILLISECONDS);

        UserAttemptResult result = new UserAttemptResult();
        result.setValidated(passed);
        result.setTicket(ticket);

        return result;
    }

    @Override
    public boolean validate(String ticket) {
        return Optional.ofNullable(cacheService.getAndDelete(
                        ticketKeyBuilder.build(ticket),
                        UserAttempt.class
                ))
                .map(UserAttempt::isValidated)
                .orElse(false);
    }

    @Override
    public RenderedImage draw(String challenge) {
        String response = Optional.ofNullable(
                        cacheService.get(challengeKeyBuilder.build(challenge), String.class)
                )
                .orElse("");
        return textRender.render(response);
    }
}