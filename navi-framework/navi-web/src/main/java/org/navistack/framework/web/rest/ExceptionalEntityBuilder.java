package org.navistack.framework.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor(staticName = "of")
public class ExceptionalEntityBuilder {
    private final Throwable throwable;

    private final Set<Throwable> dejaVu = new HashSet<>();

    public ExceptionalEntity build() {
        return buildEntity(throwable);
    }

    private ExceptionalEntity buildEntity(Throwable throwable) {
        if (throwable == null || dejaVu.contains(throwable)) {
            return null;
        }
        dejaVu.add(throwable);

        String exception = buildException(throwable);

        StackTraceElement[] stackTrace = throwable.getStackTrace();
        Collection<String> trace = buildTrace(stackTrace);
        for (Throwable suppressed : throwable.getSuppressed()) {
            trace.addAll(buildEnclosedTrace(suppressed.getStackTrace(), stackTrace));
        }

        ExceptionalEntity cause = buildCause(throwable.getCause(), stackTrace);

        ExceptionalEntity entity = new ExceptionalEntity();
        entity.setException(exception);
        entity.setTrace(trace);
        entity.setCause(cause);
        return entity;
    }

    private ExceptionalEntity buildCause(Throwable throwable, StackTraceElement[] enclosingTrace) {
        if (throwable == null || dejaVu.contains(throwable)) {
            return null;
        }
        dejaVu.add(throwable);

        String exception = buildException(throwable);

        StackTraceElement[] stackTrace = throwable.getStackTrace();
        Collection<String> trace = buildEnclosedTrace(stackTrace, enclosingTrace);

        ExceptionalEntity cause = buildCause(throwable.getCause(), stackTrace);

        ExceptionalEntity entity = new ExceptionalEntity();
        entity.setException(exception);
        entity.setTrace(trace);
        entity.setCause(cause);
        return entity;
    }

    private String buildException(Throwable throwable) {
        return throwable.getClass().getName()
                + ":"
                + " "
                + throwable.getMessage()
                ;
    }

    private Collection<String> buildTrace(StackTraceElement[] trace) {
        Collection<String> traceInfo = new ArrayList<>();

        for (StackTraceElement frame : trace) {
            StringBuilder frameInfoBuilder = new StringBuilder();
            String classLoaderName = frame.getClassLoaderName();
            if (classLoaderName != null && !classLoaderName.isEmpty()) {
                frameInfoBuilder.append(classLoaderName).append("/");
            }
            String moduleName = frame.getModuleName();
            if (moduleName != null && !moduleName.isEmpty()) {
                frameInfoBuilder.append(moduleName);
                String moduleVersion = frame.getModuleVersion();
                if (moduleVersion != null && !moduleVersion.isEmpty()) {
                    frameInfoBuilder.append("@").append(moduleVersion);
                }
            }
            if (frameInfoBuilder.length() > 0) {
                frameInfoBuilder.append("/");
            }
            frameInfoBuilder.append(frame.getClassName());
            frameInfoBuilder.append(".").append(frame.getMethodName());
            frameInfoBuilder.append("(");
            if (frame.isNativeMethod()) {
                frameInfoBuilder.append("Native Method");
            } else {
                String fileName = frame.getFileName();
                int lineNumber = frame.getLineNumber();
                if (fileName != null && lineNumber > 0) {
                    frameInfoBuilder.append(fileName).append(":").append(lineNumber);
                } else {
                    frameInfoBuilder.append("Unknown Source");
                }
            }
            frameInfoBuilder.append(")");

            String frameInfo = frameInfoBuilder.toString();
            traceInfo.add(frameInfo);
        }
        return traceInfo;
    }

    private Collection<String> buildEnclosedTrace(StackTraceElement[] trace, StackTraceElement[] enclosingTrace) {
        Collection<String> traceInfo = new ArrayList<>();

        int m = trace.length - 1;
        int n = enclosingTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(enclosingTrace[n])) {
            --m;
            --n;
        }
        int frameInCommon = trace.length - 1 - m;

        for (int i = 0; i <= m; ++i) {
            StackTraceElement frame = trace[i];
            String className = frame.getClassName();
            String methodName = frame.getMethodName();
            String fileName = frame.getFileName();
            int lineNumber = frame.getLineNumber();
            String frameInfo = className
                    + "."
                    + methodName
                    + "("
                    + fileName
                    + ":"
                    + lineNumber
                    + ")";
            traceInfo.add(frameInfo);
        }
        if (frameInCommon > 0) {
            String frameInfo = "..."
                    + " "
                    + frameInCommon
                    + " "
                    + "more";
            traceInfo.add(frameInfo);
        }

        return traceInfo;
    }
}
