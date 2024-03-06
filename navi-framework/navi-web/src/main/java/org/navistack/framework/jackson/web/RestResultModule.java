package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestOkResult;

public class RestResultModule extends SimpleModule {
    protected static final Version VERSION = VersionUtil.parseVersion(
            "0.0.1-SNAPSHOT",
            "org.navistack.framework",
            "navi-web"
    );

    public RestResultModule() {
        super(VERSION);

        setMixInAnnotation(RestOkResult.class, RestOkResultMixIn.class);
        setMixInAnnotation(RestErrResult.class, RestErrResultMixIn.class);
    }
}
