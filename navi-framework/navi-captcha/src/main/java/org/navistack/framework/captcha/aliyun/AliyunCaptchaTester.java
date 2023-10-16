package org.navistack.framework.captcha.aliyun;

import com.aliyun.captcha20230305.Client;
import com.aliyun.captcha20230305.models.VerifyCaptchaRequest;
import com.aliyun.captcha20230305.models.VerifyCaptchaResponse;
import com.aliyun.captcha20230305.models.VerifyCaptchaResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.captcha.CaptchaTester;

@Slf4j
public class AliyunCaptchaTester implements CaptchaTester {
    private final Client client;

    public AliyunCaptchaTester(Client client) {
        this.client = client;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        try {
            VerifyCaptchaRequest req = new VerifyCaptchaRequest();
            req.setCaptchaVerifyParam(request.getParameter("captchaVerifyParam"));

            VerifyCaptchaResponse response = client.verifyCaptcha(req);
            VerifyCaptchaResponseBody responseBody = response.getBody();
            if (Boolean.TRUE.equals(responseBody.getSuccess())) {
                return true;
            } else {
                log.warn("Failed verifying captcha: Response: {}", response);
                return false;
            }
        } catch (Exception e) {
            log.error("Failed calling Alibaba Cloud Captcha", e);
        }
        return false;
    }
}
