package org.navistack.framework.logging;

import org.slf4j.LoggerFactory;

public class Slf4jOperationLogService implements OperationLogService {
    @Override
    public <T> void trace(Class<T> clazz, String message, Object... args) {
        LoggerFactory.getLogger(clazz).trace(message, args);
    }

    @Override
    public <T> void trace(String name, String message, Object... args) {
        LoggerFactory.getLogger(name).trace(message, args);
    }

    @Override
    public <T> void debug(Class<T> clazz, String message, Object... args) {
        LoggerFactory.getLogger(clazz).debug(message, args);
    }

    @Override
    public <T> void debug(String name, String message, Object... args) {
        LoggerFactory.getLogger(name).debug(message, args);
    }

    @Override
    public <T> void info(Class<T> clazz, String message, Object... args) {
        LoggerFactory.getLogger(clazz).info(message, args);
    }

    @Override
    public <T> void info(String name, String message, Object... args) {
        LoggerFactory.getLogger(name).info(message, args);
    }

    @Override
    public <T> void warn(Class<T> clazz, String message, Object... args) {
        LoggerFactory.getLogger(clazz).warn(message, args);
    }

    @Override
    public <T> void warn(String name, String message, Object... args) {
        LoggerFactory.getLogger(name).warn(message, args);
    }

    @Override
    public <T> void error(Class<T> clazz, String message, Object... args) {
        LoggerFactory.getLogger(clazz).error(message, args);
    }

    @Override
    public <T> void error(String name, String message, Object... args) {
        LoggerFactory.getLogger(name).error(message, args);
    }

}
