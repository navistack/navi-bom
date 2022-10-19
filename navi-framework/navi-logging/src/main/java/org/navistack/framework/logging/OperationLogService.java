package org.navistack.framework.logging;

public interface OperationLogService {
    <T> void trace(Class<T> clazz, String message, Object... args);

    <T> void trace(String name, String message, Object... args);

    <T> void debug(Class<T> clazz, String message, Object... args);

    <T> void debug(String name, String message, Object... args);

    <T> void info(Class<T> clazz, String message, Object... args);

    <T> void info(String name, String message, Object... args);

    <T> void warn(Class<T> clazz, String message, Object... args);

    <T> void warn(String name, String message, Object... args);

    <T> void error(Class<T> clazz, String message, Object... args);

    <T> void error(String name, String message, Object... args);
}
