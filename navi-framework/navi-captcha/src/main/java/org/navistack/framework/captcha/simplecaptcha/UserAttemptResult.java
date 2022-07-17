package org.navistack.framework.captcha.simplecaptcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserAttemptResult {
    private boolean validated;
    private String ticket;

    public static UserAttemptResult ok(String ticket) {
        return of(true, ticket);
    }

    public static UserAttemptResult failed(String ticket) {
        return of(false, ticket);
    }
}
