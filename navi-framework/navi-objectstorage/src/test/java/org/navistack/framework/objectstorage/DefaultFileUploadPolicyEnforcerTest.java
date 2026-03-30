package org.navistack.framework.objectstorage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;

class DefaultFileUploadPolicyEnforcerTest {
    @Test
    void setDefaultUploadPolicy() {
        FileUploadPolicy policy = new FileUploadPolicy();
        policy.setContentSizeLimit(1048576 /* bytes */);
        policy.setContentTypeLimit("image/bmp", "image/jpeg");
        DefaultFileUploadPolicyEnforcer enforcer = new DefaultFileUploadPolicyEnforcer();
        enforcer.setDefaultUploadPolicy(policy);
        Assertions.assertThatThrownBy(() -> enforcer.setDefaultUploadPolicy(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void enforce() {
        FileUploadPolicy defaultPolicy = new FileUploadPolicy();
        defaultPolicy.setContentSizeLimit(0 /* bytes */);
        defaultPolicy.setContentTypeLimit("image/bmp", "image/jpeg");

        DefaultFileUploadPolicyEnforcer enforcer = new DefaultFileUploadPolicyEnforcer(defaultPolicy);
        Assertions.assertThatThrownBy(() -> {
            enforcer.enforce(
                    FileSystems.getDefault().getPath("src/test/resources/Null.txt"),
                    "text/plain",
                    null
            );
        }).isInstanceOf(InvalidContentTypeException.class);

        FileUploadPolicy anotherPolicy = new FileUploadPolicy();
        anotherPolicy.setContentSizeLimit(106 /* bytes */);
        Assertions.assertThatThrownBy(() -> {
            enforcer.enforce(
                    FileSystems.getDefault().getPath("src/test/resources/Two Hard Things.txt"),
                    "text/plain",
                    anotherPolicy
            );
        }).isInstanceOf(FileSizeLimitExceededException.class);

        FileUploadPolicy yetAnotherPolicy = new FileUploadPolicy();
        yetAnotherPolicy.setContentTypeLimit("text/plain");
        enforcer.enforce(
                FileSystems.getDefault().getPath("src/test/resources/Null.txt"),
                "text/plain",
                yetAnotherPolicy
        );
    }

    @Test
    void getDefaultUploadPolicy() {
        FileUploadPolicy policy = new FileUploadPolicy();
        policy.setContentSizeLimit(1048576 /* bytes */);
        policy.setContentTypeLimit("image/bmp", "image/jpeg");
        DefaultFileUploadPolicyEnforcer enforcer = new DefaultFileUploadPolicyEnforcer();
        enforcer.setDefaultUploadPolicy(policy);
        Assertions.assertThat(enforcer.getDefaultUploadPolicy()).isSameAs(policy);

        FileUploadPolicy anotherPolicy = new FileUploadPolicy();
        Assertions.assertThat(enforcer.getDefaultUploadPolicy()).isNotSameAs(anotherPolicy);
    }
}
