package org.navistack.framework.captcha.simplecaptcha;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.cache.CacheScope;
import org.navistack.framework.cache.CacheStore;
import org.navistack.framework.cache.ScopedCacheStoreBuilder;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.BorderImageFilter;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.GradientBackgroundImageFilter;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.NoiseImageFilter;
import org.navistack.framework.captcha.simplecaptcha.imagefilters.WaterRippleImageFilter;
import org.navistack.framework.captcha.simplecaptcha.textgenerators.AlphanumericTextGenerator;

import java.awt.image.RenderedImage;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

public class DefaultSimpleCaptchaService implements SimpleCaptchaService {
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

    private final CacheStore cacheStore;

    public DefaultSimpleCaptchaService(ScopedCacheStoreBuilder cacheServiceBuilder) {
        this.cacheStore = cacheServiceBuilder.build(CacheScope.of("NAVI").scope("SIMPLE_CAPTCHA"));
    }

    @Override
    public String challenge() {
        String challenge = UUID.randomUUID().toString();

        String expected = textGenerator.generate();
        cacheStore.set("CHALLENGE_" + challenge, expected, Duration.of(challengeValidity, ChronoUnit.MILLIS));

        return challenge;
    }

    @Override
    public UserAttemptResult answer(String challenge, String answer) {
        String ticket = UUID.randomUUID().toString();

        boolean passed = Optional.ofNullable(
                        cacheStore.getAndDelete("CHALLENGE_" + challenge, String.class)
                ).map(expectedAnswer -> expectedAnswer.equals(answer))
                .orElse(false);

        UserAttempt userAttempt = new UserAttempt();
        userAttempt.setAnswer(answer);
        userAttempt.setValidated(passed);

        cacheStore.set("TICKET_" + ticket, userAttempt, Duration.of(ticketValidity, ChronoUnit.MILLIS));

        UserAttemptResult result = new UserAttemptResult();
        result.setValidated(passed);
        result.setTicket(ticket);

        return result;
    }

    @Override
    public boolean validate(String ticket) {
        return Optional.ofNullable(cacheStore.getAndDelete(
                        "TICKET_" + ticket,
                        UserAttempt.class
                ))
                .map(UserAttempt::isValidated)
                .orElse(false);
    }

    @Override
    public RenderedImage draw(String challenge) {
        String response = Optional.ofNullable(
                        cacheStore.get("CHALLENGE_" + challenge, String.class)
                )
                .orElse("");
        return textRender.render(response);
    }
}
