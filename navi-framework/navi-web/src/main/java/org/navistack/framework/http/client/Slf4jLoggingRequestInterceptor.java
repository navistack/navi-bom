package org.navistack.framework.http.client;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Slf4jLoggingRequestInterceptor implements ClientHttpRequestInterceptor {
    @Getter
    @Setter
    @NonNull
    private Charset defaultCharset = StandardCharsets.UTF_8;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("* Sending request");
        log.debug("> URI    : {} {}", request.getMethod(), request.getURI());
        log.debug("> Headers: {}", request.getHeaders());
        log.debug("> Body   : {}", new String(body, resolveCharset(request)));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        String responseBody = StreamUtils.copyToString(response.getBody(), resolveCharset(response));
        log.debug("* Receiving response");
        log.debug("< Status  : {} {}", response.getStatusCode(), response.getStatusText());
        log.debug("< Headers : {}", response.getHeaders());
        log.debug("< Body    : {}", responseBody);
    }

    private Charset resolveCharset(HttpMessage message) {
        HttpHeaders headers = message.getHeaders();
        MediaType contentType = headers.getContentType();
        if (contentType != null) {
            Charset charset = contentType.getCharset();
            if (charset != null) {
                return charset;
            }
        }
        return defaultCharset;
    }
}
