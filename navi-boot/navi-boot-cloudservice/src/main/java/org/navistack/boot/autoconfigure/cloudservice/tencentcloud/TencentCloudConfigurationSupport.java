package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.Language;

public abstract class TencentCloudConfigurationSupport {
    protected static Credential bindCredential(TencentCloudCredentialProperties properties) {
        return new Credential(properties.getSecretId(), properties.getSecretKey());
    }

    protected static ClientProfile bindClientProfile(TencentCloudClientProfileProperties properties) {
        ClientProfile clientProfile = new ClientProfile();

        HttpProfile httpProfile = bindHttpProfile(properties.getHttpProfile());
        clientProfile.setHttpProfile(httpProfile);

        boolean debug = properties.isDebug();
        clientProfile.setDebug(debug);

        String language = properties.getLanguage();
        if (language != null) {
            clientProfile.setLanguage(Language.valueOf(language));
        }

        String signMethod = properties.getSignMethod();
        if (signMethod != null) {
            clientProfile.setSignMethod(signMethod);
        }

        boolean unsignedPayload = properties.isUnsignedPayload();
        clientProfile.setUnsignedPayload(unsignedPayload);

        return clientProfile;
    }

    protected static HttpProfile bindHttpProfile(TencentCloudHttpProfileProperties properties) {
        HttpProfile httpProfile = new HttpProfile();

        String reqMethod = properties.getReqMethod();
        if (reqMethod != null) {
            httpProfile.setReqMethod(reqMethod);
        }

        String endpoint = properties.getEndpoint();
        if (endpoint != null) {
            httpProfile.setEndpoint(endpoint);
        }

        Integer readTimeout = properties.getReadTimeout();
        if (readTimeout != null) {
            httpProfile.setReadTimeout(readTimeout);
        }

        Integer writeTimeout = properties.getWriteTimeout();
        if (writeTimeout != null) {
            httpProfile.setWriteTimeout(writeTimeout);
        }

        Integer connTimeout = properties.getConnTimeout();
        if (connTimeout != null) {
            httpProfile.setConnTimeout(connTimeout);
        }

        String protocol = properties.getProtocol();
        if (protocol != null) {
            httpProfile.setProtocol(protocol);
        }

        String proxyHost = properties.getProxyHost();
        if (proxyHost != null) {
            httpProfile.setProxyHost(proxyHost);
        }

        Integer proxyPort = properties.getProxyPort();
        if (proxyPort != null) {
            httpProfile.setProxyPort(proxyPort);
        }

        String proxyUsername = properties.getProxyUsername();
        if (proxyUsername != null) {
            httpProfile.setProxyUsername(proxyUsername);
        }

        String proxyPassword = properties.getProxyPassword();
        if (proxyPassword != null) {
            httpProfile.setProxyPassword(proxyPassword);
        }

        String rootDomain = properties.getRootDomain();
        if (rootDomain != null) {
            httpProfile.setRootDomain(rootDomain);
        }

        return httpProfile;
    }
}
