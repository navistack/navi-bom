package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.Credential;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class AliyunAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AliyunAutoConfiguration.class));

    @Test
    void testDisabled() {
        contextRunner
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(IAcsClient.class);
                });
    }

    @Test
    void testProperties() {
        contextRunner
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".region=REGION")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".credential.accessKeyId=ACCESS-KEY-ID")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".credential.accessKeySecret=ACCESS-KEY-SECRET")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".endpoints[0].region-id=REGION-0")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".endpoints[0].product=PRODUCT-0")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".endpoints[0].endpoint=ENDPOINT-0")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".endpoints[1].region-id=REGION-1")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".endpoints[1].product=PRODUCT-1")
                .withPropertyValues(AliyunClientProperties.PROPERTY_PREFIX + ".endpoints[1].endpoint=ENDPOINT-1")
                .run(context -> {
                    assertThat(context).hasSingleBean(IAcsClient.class);
                    assertThat(context).hasSingleBean(DefaultAcsClient.class);
                    DefaultAcsClient client = context.getBean(DefaultAcsClient.class);
                    DefaultProfile profile = client.getProfile();
                    assertThat(profile).extracting(DefaultProfile::getRegionId).isEqualTo("REGION");
                    Credential credential = profile.getCredential();
                    assertThat(profile).extracting(DefaultProfile::getCredential).isNotNull();
                    assertThat(credential).extracting(Credential::getAccessKeyId).isEqualTo("ACCESS-KEY-ID");
                    assertThat(credential).extracting(Credential::getAccessSecret).isEqualTo("ACCESS-KEY-SECRET");
                });
    }
}
