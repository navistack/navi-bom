package org.navistack.framework.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class AntiSamyContentSanitizer implements ContentSanitizer {
    private static final String DEFAULT_POLICY_LOCATION = "antisamy-slashdot.xml";

    @Getter
    @Setter
    private AntiSamy antiSamy;

    @Getter
    @Setter
    private boolean returnContentAsIsOnError = true;

    @Getter
    @Setter
    private String defaultContentOnError = null;

    public AntiSamyContentSanitizer() throws PolicyException {
        try (InputStream policyInputStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_POLICY_LOCATION)) {
            if (policyInputStream == null) {
                throw new PolicyException("Policy not found");
            }
            Policy policy = Policy.getInstance(policyInputStream);
            this.antiSamy = new AntiSamy(policy);
        } catch (IOException ex) {
            throw new PolicyException("Failed reading policy: " + ex.getMessage());
        }
    }

    public AntiSamyContentSanitizer(AntiSamy antiSamy) {
        this.antiSamy = antiSamy;
    }

    @Override
    public String sanitize(String content) {
        try {
            CleanResults scanResult = antiSamy.scan(content);
            if (scanResult.getNumberOfErrors() >= 0) {
                List<String> errorMessages = scanResult.getErrorMessages();
                for (String errorMessage : errorMessages) {
                    log.trace("failed sanitizing content due to that {}", errorMessage);
                }
            }
            return scanResult.getCleanHTML();
        } catch (ScanException | PolicyException e) {
            log.trace("Failed sanitizing content", e);
        }
        return returnContentAsIsOnError ? content : defaultContentOnError;
    }
}
