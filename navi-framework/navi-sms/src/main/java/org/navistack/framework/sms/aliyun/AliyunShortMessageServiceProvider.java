package org.navistack.framework.sms.aliyun;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.sms.ShortMessage;
import org.navistack.framework.sms.ShortMessageException;
import org.navistack.framework.sms.ShortMessageServiceProvider;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class AliyunShortMessageServiceProvider implements ShortMessageServiceProvider {
    private final Client client;

    @Setter
    @Getter
    private ObjectMapper objectMapper;

    public AliyunShortMessageServiceProvider(Client client) {
        this.client = client;
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
            SendSmsResponse response = client.sendSms(request);
            SendSmsResponseBody responseBody = response.getBody();
            log.debug(
                    "Short Message Sending Response:"
                            + " code={}"
                            + " message={}"
                            + " requestId={}"
                            + " bizId={}",
                    responseBody.getCode(),
                    responseBody.getMessage(),
                    responseBody.getRequestId(),
                    responseBody.getBizId()
            );
            if (!"OK".equalsIgnoreCase(responseBody.getCode())) {
                throw new ShortMessageException(String.format(
                        "failed sending short message: %s(%s)",
                        responseBody.getMessage(),
                        responseBody.getCode()
                ));
            }
        } catch (Exception e) {
            throw new ShortMessageException(e);
        }
    }

    @SneakyThrows
    protected String buildTemplateParam(Map<String, Object> templateParam) {
        if (templateParam == null || templateParam.isEmpty()) {
            objectMapper.writeValueAsString(Collections.emptyMap());
        }

        return objectMapper.writeValueAsString(templateParam);
    }
}
