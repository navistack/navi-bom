package org.navistack.framework.web.rest.exceptionhanders;

public class InvalidParam {
    private String name;
    private String reason;

    public InvalidParam(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static InvalidParam of(String name, String reason) {
        return new InvalidParam(name, reason);
    }
}
