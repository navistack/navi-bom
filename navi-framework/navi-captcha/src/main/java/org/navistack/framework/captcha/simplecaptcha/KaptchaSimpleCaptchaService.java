package org.navistack.framework.captcha.simplecaptcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.navistack.framework.cache.KvCacheService;

import java.awt.image.RenderedImage;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class KaptchaSimpleCaptchaService implements SimpleCaptchaService {
    private final String CACHE_KEY_PREFIX = "NAVI.SIMPLE_CAPTCHA.";
    private final String CHALLENGE_CACHE_KEY_PREFIX = CACHE_KEY_PREFIX + "CHALLENGE.";
    private final String TICKET_CACHE_KEY_PREFIX = CACHE_KEY_PREFIX + "TICKET.";
    private static final int CHALLENGE_VALIDITY = 10 * 60 * 1000;
    private static final int TICKET_VALIDITY = 10 * 60 * 1000;

    private final KvCacheService kvCacheService;

    private final Producer kaptcha;

    {
        Properties properties = new Properties();
        Config config = new Config(properties);

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        kaptcha = defaultKaptcha;
    }

    public KaptchaSimpleCaptchaService(KvCacheService kvCacheService) {
        this.kvCacheService = kvCacheService;
    }

    @Override
    public String challenge() {
        String challenge = UUID.randomUUID().toString();

        String expected = kaptcha.createText();
        kvCacheService.set(buildChallengeCacheKey(challenge), expected, CHALLENGE_VALIDITY, TimeUnit.MILLISECONDS);

        return challenge;
    }

    @Override
    public UserAttemptResult answer(String challenge, String answer) {
        String ticket = UUID.randomUUID().toString();

        boolean passed = Optional.ofNullable(
                        kvCacheService.getAndDelete(buildChallengeCacheKey(challenge), String.class)
                ).map(expectedAnswer -> expectedAnswer.equals(answer))
                .orElse(false);

        UserAttempt userAttempt = new UserAttempt();
        userAttempt.setAnswer(answer);
        userAttempt.setValidated(passed);

        kvCacheService.set(buildTicketCacheKey(ticket), userAttempt, TICKET_VALIDITY, TimeUnit.MILLISECONDS);

        UserAttemptResult result = new UserAttemptResult();
        result.setValidated(passed);
        result.setTicket(ticket);

        return result;
    }

    @Override
    public boolean validate(String ticket) {
        return Optional.ofNullable(kvCacheService.getAndDelete(
                        buildTicketCacheKey(ticket),
                        UserAttempt.class
                ))
                .map(UserAttempt::isValidated)
                .orElse(false);
    }

    @Override
    public RenderedImage draw(String challenge) {
        String response = Optional.ofNullable(
                        kvCacheService.get(buildChallengeCacheKey(challenge), String.class)
                )
                .orElse("");
        return kaptcha.createImage(response);
    }

    protected String buildChallengeCacheKey(String challenge) {
        return CHALLENGE_CACHE_KEY_PREFIX + challenge;
    }

    protected String buildTicketCacheKey(String ticket) {
        return TICKET_CACHE_KEY_PREFIX + ticket;
    }
}
