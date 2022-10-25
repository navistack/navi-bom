package org.navistack.framework.sms;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class DefaultShortMessageService implements ShortMessageService {
    @Getter
    @Setter
    @NonNull
    private ShortMessageTemplateRegistration templateRegistration;

    @Getter
    @Setter
    @NonNull
    private ShortMessageServiceProvider serviceProvider;

    public DefaultShortMessageService(
            ShortMessageTemplateRegistration templateRegistration,
            ShortMessageServiceProvider serviceProvider
    ) {
        this.templateRegistration = templateRegistration;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void send(ShortMessage message) {
        if (message == null) {
            throw new NullPointerException("message must not be null");
        }
        String templateCode = message.getTemplateCode();
        if (templateCode == null || templateCode.isEmpty()) {
            throw new NullPointerException("message.templateCode must not be empty");
        }
        ShortMessageTemplate messageTemplate = templateRegistration.getTemplate(templateCode);
        if (messageTemplate == null) {
            throw new ShortMessageException(String.format("Template(code=%s) was not registered", templateCode));
        }

        serviceProvider.send(
                new ShortMessageBuilder()
                        .phoneNumber(message.getPhoneNumber())
                        .signature(message.getSignature())
                        .templateCode(message.getTemplateCode())
                        .message(messageTemplate.getMessage())
                        .arguments(message.getArguments())
                        .attributes(message.getAttributes())
                        .build()
        );
    }
}
