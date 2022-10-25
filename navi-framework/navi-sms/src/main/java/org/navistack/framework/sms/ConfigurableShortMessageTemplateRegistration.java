package org.navistack.framework.sms;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurableShortMessageTemplateRegistration implements ShortMessageTemplateRegistration {
    private final Map<String, ShortMessageTemplate> templateCache = new ConcurrentHashMap<>();

    public void addTemplate(String templateCode, String message) {
        if (templateCode == null) {
            throw new NullPointerException("templateCode must not be null");
        }
        if (message == null) {
            throw new NullPointerException("message must not be null");
        }
        this.templateCache.put(templateCode, SimpleShortMessageTemplate.of(templateCode, message));
    }

    public void addTemplate(ShortMessageTemplate template) {
        if (template == null) {
            throw new NullPointerException("template must not be null");
        }
        this.templateCache.put(template.getCode(), template);
    }

    public void addTemplates(Map<String, String> templates) {
        if (templates == null || templates.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> template : templates.entrySet()) {
            this.templateCache.put(
                    template.getKey(),
                    SimpleShortMessageTemplate.of(template.getKey(), template.getValue())
            );
        }
    }

    public void addTemplates(Collection<? extends ShortMessageTemplate> templates) {
        if (templates == null || templates.isEmpty()) {
            return;
        }
        for (ShortMessageTemplate template : templates) {
            this.templateCache.put(template.getCode(), template);
        }
    }

    @Override
    public ShortMessageTemplate getTemplate(String templateCode) {
        return templateCache.get(templateCode);
    }
}
