package org.navistack.framework.http;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.navistack.framework.utils.Asserts;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpMethod {
    public static final HttpMethod GET = new HttpMethod("GET");
    public static final HttpMethod HEAD = new HttpMethod("HEAD");
    public static final HttpMethod POST = new HttpMethod("POST");
    public static final HttpMethod PUT = new HttpMethod("PUT");
    public static final HttpMethod PATCH = new HttpMethod("PATCH");
    public static final HttpMethod DELETE = new HttpMethod("DELETE");
    public static final HttpMethod OPTIONS = new HttpMethod("OPTIONS");
    public static final HttpMethod TRACE = new HttpMethod("TRACE");

    private static final Map<String, HttpMethod> methodMap = new HashMap<>();

    static {
        methodMap.put("GET", GET);
        methodMap.put("HEAD", HEAD);
        methodMap.put("POST", POST);
        methodMap.put("PUT", PUT);
        methodMap.put("PATCH", PATCH);
        methodMap.put("DELETE", DELETE);
        methodMap.put("OPTIONS", OPTIONS);
        methodMap.put("TRACE", TRACE);
    }

    private final String name;

    public static HttpMethod valueOf(String name) {
        Asserts.notNull(name, "name must not be null");
        HttpMethod httpMethod = methodMap.get(name);
        if (httpMethod != null) {
            return httpMethod;
        }
        return new HttpMethod(name);
    }
}
