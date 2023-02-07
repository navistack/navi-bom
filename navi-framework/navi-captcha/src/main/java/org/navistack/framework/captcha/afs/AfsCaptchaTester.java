package org.navistack.framework.captcha.afs;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.exceptions.ClientException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.captcha.CaptchaTester;

@Slf4j
public class AfsCaptchaTester implements CaptchaTester {
    private final String appKey;
    private final String scene;
    private final IAcsClient acsClient;

    public AfsCaptchaTester(String appKey, String scene, IAcsClient acsClient) {
        this.appKey = appKey;
        this.scene = scene;
        this.acsClient = acsClient;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        try {
            AuthenticateSigRequest req = new AuthenticateSigRequest();
            req.setToken(request.getParameter("ncToken"));
            req.setSessionId(request.getParameter("sessionId"));
            req.setSig(request.getParameter("sig"));
            req.setScene(scene);
            req.setAppKey(appKey);
            req.setRemoteIp(request.getRemoteAddr());

            AuthenticateSigResponse resp = acsClient.getAcsResponse(req);
            if(AfsResponseCodes.VALIDATION_PASSED == resp.getCode()) {
                return true;
            } else {
                log.warn("Failed validating ticket: Response: {}", resp);
                return false;
            }
        } catch (ClientException e) {
            log.error("Failed calling Ali Cloud Captcha Service", e);
        }
        return false;
    }
}
