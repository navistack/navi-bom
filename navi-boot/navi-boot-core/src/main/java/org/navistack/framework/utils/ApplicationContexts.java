package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

@UtilityClass
public class ApplicationContexts {
    public Object getBean(ApplicationContext context, String name) throws BeansException {
        return containsBeanDefinition(context, name) ? context.getBean(name) : null;
    }

    public <T> T getBean(ApplicationContext context, String name, Class<T> requiredType) throws BeansException {
        return containsBeanDefinition(context, name) ? context.getBean(name, requiredType) : null;
    }

    public Object getBean(ApplicationContext context, String name, Object... args) throws BeansException {
        return containsBeanDefinition(context, name) ? context.getBean(name, args) : null;
    }

    public <T> T getBean(ApplicationContext context, Class<T> requiredType) throws BeansException {
        return containsBeanDefinition(context, requiredType) ? context.getBean(requiredType) : null;
    }

    public <T> T getBean(ApplicationContext context, Class<T> requiredType, Object... args) throws BeansException {
        return containsBeanDefinition(context, requiredType) ? context.getBean(requiredType, args) : null;
    }

    public boolean containsBeanDefinition(ApplicationContext context, String beanName) {
        return context.containsBeanDefinition(beanName);
    }

    public <T> boolean containsBeanDefinition(ApplicationContext context, Class<T> type) {
        String[] beanNames = context.getBeanNamesForType(type);
        return beanNames.length > 0;
    }
}
