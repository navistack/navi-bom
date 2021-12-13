package org.navistack.framework.captcha.simplecaptcha;

import org.navistack.framework.web.rest.RestResult;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public abstract class AbstractSimpleCaptchaController {
    private final SimpleCaptchaService simpleCaptchaService;

    public AbstractSimpleCaptchaController(SimpleCaptchaService simpleCaptchaService) {
        this.simpleCaptchaService = simpleCaptchaService;
    }

    @PostMapping("/challenge")
    public RestResult<String, ?> challenge() {
        return RestResult.ok(simpleCaptchaService.challenge());
    }

    @PostMapping("/answer")
    public RestResult<UserAttemptResult, ?> answer(String challengeId, String answer) {
        return RestResult.ok(simpleCaptchaService.answer(challengeId, answer));
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> image(String challenge) throws Exception {
        RenderedImage image = simpleCaptchaService.draw(challenge);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(byteArrayInputStream));
    }
}
