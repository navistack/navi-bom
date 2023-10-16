package org.navistack.framework.captcha.tencentcloud;

import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultRequest;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultResponse;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.captcha.CaptchaTester;

@Slf4j
public class TencentCloudCaptchaTester implements CaptchaTester {
    private final Long captchaAppId;
    private final String appSecretKey;
    private final CaptchaClient captchaClient;

    public TencentCloudCaptchaTester(Long captchaAppId, String appSecretKey, CaptchaClient captchaClient) {
        this.captchaAppId = captchaAppId;
        this.appSecretKey = appSecretKey;
        this.captchaClient = captchaClient;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        try {
            DescribeCaptchaResultRequest req = new DescribeCaptchaResultRequest();
            req.setCaptchaAppId(captchaAppId);
            req.setAppSecretKey(appSecretKey);
            req.setRandstr(request.getParameter("randstr"));
            req.setTicket(request.getParameter("ticket"));
            DescribeCaptchaResultResponse resp = captchaClient.DescribeCaptchaResult(req);
            if (TencentCloudCaptchaCodes.OK.equals(resp.getCaptchaCode())) {
                return true;
            } else {
                log.warn("Failed validating ticket: Response: {}", resp);
                return false;
            }
        } catch (TencentCloudSDKException e) {
            log.error("Failed calling Tencent Cloud Captcha Service", e);
        }
        return false;
    }
}
