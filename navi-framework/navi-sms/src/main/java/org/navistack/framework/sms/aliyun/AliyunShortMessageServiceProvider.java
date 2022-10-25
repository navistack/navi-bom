package org.navistack.framework.sms.aliyun;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.sms.ShortMessage;
import org.navistack.framework.sms.ShortMessageException;
import org.navistack.framework.sms.ShortMessageServiceProvider;

import java.util.Map;

@Slf4j
public class AliyunShortMessageServiceProvider implements ShortMessageServiceProvider {
    private final IAcsClient acsClient;

    public AliyunShortMessageServiceProvider(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    @Override
    public void send(ShortMessage message) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(message.getPhoneNumber());
        request.setSignName(message.getSignature());
        request.setTemplateCode(message.getTemplateCode());
        request.setTemplateParam(buildTemplateParam(message.getArguments()));

        Map<String, Object> attributes = message.getAttributes();
        if (attributes != null && !attributes.isEmpty()) {
            Object outId = attributes.get("outId");
            if (outId != null) {
                request.setOutId(outId.toString());
            }

            Object smsUpExtendCode = attributes.get("smsUpExtendCode");
            if (smsUpExtendCode != null) {
                request.setSmsUpExtendCode(smsUpExtendCode.toString());
            }
        }

        try {
            SendSmsResponse response = acsClient.getAcsResponse(request);
            log.debug(
                    "Short Message Sending Response:"
                            + " code={}"
                            + " message={}"
                            + " requestId={}"
                            + " bizId={}",
                    response.getCode(),
                    response.getMessage(),
                    response.getRequestId(),
                    response.getBizId()
            );
            if (!"OK".equalsIgnoreCase(response.getCode())) {
                throw new ShortMessageException(String.format(
                        "failed sending short message: %s(%s)",
                        response.getMessage(),
                        response.getCode()
                ));
            }
        } catch (ClientException e) {
            throw new ShortMessageException(e);
        }
    }

    protected static String buildTemplateParam(Map<String, Object> templateParam) {
        if (templateParam == null || templateParam.isEmpty()) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : templateParam.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                stringBuilder
                        .append('"').append(entry.getKey()).append('"')
                        .append(':')
                        .append("null");
            } else {
                stringBuilder
                        .append('"').append(entry.getKey()).append('"')
                        .append(':')
                        .append('"').append(value.toString()).append('"');
            }
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
