package org.navistack.framework.sms.tencentcloud;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.sms.ShortMessage;
import org.navistack.framework.sms.ShortMessageException;
import org.navistack.framework.sms.ShortMessageService;

import java.util.Map;

@Slf4j
public class TencentCloudShortMessageService implements ShortMessageService {
    @Getter
    private final String sdkAppId;

    @Getter
    private final String appKey;

    @Getter
    private final SmsClient smsClient;

    public TencentCloudShortMessageService(String sdkAppId, String appKey, SmsClient smsClient) {
        this.sdkAppId = sdkAppId;
        this.appKey = appKey;
        this.smsClient = smsClient;
    }

    @Override
    public void send(ShortMessage message) {
        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setSmsSdkAppId(sdkAppId);
            request.setPhoneNumberSet(new String[]{message.getPhoneNumber()});
            request.setSignName(message.getSignature());
            request.setTemplateId(message.getMessage());

            String[] templateParamSet1 = prepareTemplateParamSet(message.getArguments());
            if (templateParamSet1 != null && templateParamSet1.length > 0) {
                request.setTemplateParamSet(templateParamSet1);
            }

            Map<String, Object> attributes = message.getAttributes();
            if (attributes != null && !attributes.isEmpty()) {
                Object extendCode = attributes.get("extendCode");
                if (extendCode != null) {
                    request.setExtendCode(extendCode.toString());
                }

                Object sessionContext = attributes.get("sessionContext");
                if (sessionContext != null) {
                    request.setSessionContext(sessionContext.toString());
                }

                Object senderId = attributes.get("senderId");
                if (senderId != null) {
                    request.setSenderId(senderId.toString());
                }
            }

            SendSmsResponse response = smsClient.SendSms(request);

            SendStatus[] sendStatusSet = response.getSendStatusSet();
            for (SendStatus sendStatus : sendStatusSet) {
                log.debug(
                        "Short message sending status ({}):" +
                                " SerialNo={};" +
                                " PhoneNumber={};" +
                                " Fee={};" +
                                " SessionContext={};" +
                                " Code={};" +
                                " Message={};" +
                                " IsoCode={};",
                        response.getRequestId(),
                        sendStatus.getSerialNo(),
                        sendStatus.getPhoneNumber(),
                        sendStatus.getFee(),
                        sendStatus.getSessionContext(),
                        sendStatus.getCode(),
                        sendStatus.getMessage(),
                        sendStatus.getIsoCode()
                );
                if (!"Ok".equalsIgnoreCase(sendStatus.getCode())) {
                    throw new ShortMessageException(String.format(
                            "failed sending short message: %s(%s)",
                            sendStatus.getMessage(),
                            sendStatus.getCode()
                    ));
                }
            }
        } catch (TencentCloudSDKException e) {
            throw new ShortMessageException(e);
        }
    }

    private static String[] prepareTemplateParamSet(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }

        if (paramMap.isEmpty()) {
            return new String[]{};
        }

        int argc = paramMap.size();
        String[] paramSet = new String[argc];
        for (int i = 1; i <= argc; ++i) {
            Object arg = paramMap.get(Integer.toString(i));
            if (arg == null) {
                break;
            }
            paramSet[i - 1] = arg.toString();
        }
        return paramSet;
    }
}
