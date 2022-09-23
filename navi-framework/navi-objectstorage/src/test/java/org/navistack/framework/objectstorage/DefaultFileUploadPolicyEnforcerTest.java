package org.navistack.framework.objectstorage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.navistack.framework.http.MediaTypes;

import java.nio.file.FileSystems;

class DefaultFileUploadPolicyEnforcerTest {
    @Test
    void setDefaultUploadPolicy() {
        FileUploadPolicy policy = new FileUploadPolicy();
        policy.setContentSizeLimit(1048576 /* bytes */);
        policy.setContentTypeLimit(MediaTypes.IMAGE_BMP.getFullType(), MediaTypes.IMAGE_JPEG.getFullType());
        DefaultFileUploadPolicyEnforcer enforcer = new DefaultFileUploadPolicyEnforcer();
        enforcer.setDefaultUploadPolicy(policy);
        Assertions.assertThatThrownBy(() -> enforcer.setDefaultUploadPolicy(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void enforce() {
        FileUploadPolicy defaultPolicy = new FileUploadPolicy();
        defaultPolicy.setContentSizeLimit(0 /* bytes */);
        defaultPolicy.setContentTypeLimit(MediaTypes.IMAGE_BMP.getFullType(), MediaTypes.IMAGE_JPEG.getFullType());

        DefaultFileUploadPolicyEnforcer enforcer = new DefaultFileUploadPolicyEnforcer(defaultPolicy);
        Assertions.assertThatThrownBy(() -> {
            enforcer.enforce(
                    FileSystems.getDefault().getPath("src/test/resources/Null.txt"),
                    MediaTypes.TEXT_PLAIN.getFullType(),
                    null
            );
        }).isInstanceOf(InvalidContentTypeException.class);

        FileUploadPolicy anotherPolicy = new FileUploadPolicy();
        anotherPolicy.setContentSizeLimit(106 /* bytes */);
        Assertions.assertThatThrownBy(() -> {
            enforcer.enforce(
                    FileSystems.getDefault().getPath("src/test/resources/Two Hard Things.txt"),
                    MediaTypes.TEXT_PLAIN.getFullType(),
                    anotherPolicy
            );
        }).isInstanceOf(FileSizeLimitExceededException.class);

        FileUploadPolicy yetAnotherPolicy = new FileUploadPolicy();
        yetAnotherPolicy.setContentTypeLimit(MediaTypes.TEXT_PLAIN.getFullType());
        enforcer.enforce(
                FileSystems.getDefault().getPath("src/test/resources/Null.txt"),
                MediaTypes.TEXT_PLAIN.getFullType(),
                yetAnotherPolicy
        );
    }

    @Test
    void getDefaultUploadPolicy() {
        FileUploadPolicy policy = new FileUploadPolicy();
        policy.setContentSizeLimit(1048576 /* bytes */);
        policy.setContentTypeLimit(MediaTypes.IMAGE_BMP.getFullType(), MediaTypes.IMAGE_JPEG.getFullType());
        DefaultFileUploadPolicyEnforcer enforcer = new DefaultFileUploadPolicyEnforcer();
        enforcer.setDefaultUploadPolicy(policy);
        Assertions.assertThat(enforcer.getDefaultUploadPolicy()).isSameAs(policy);

        FileUploadPolicy anotherPolicy = new FileUploadPolicy();
        Assertions.assertThat(enforcer.getDefaultUploadPolicy()).isNotSameAs(anotherPolicy);
    }
}
