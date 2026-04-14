package org.navistack.framework.jackson.web;

import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestOkResult;
import tools.jackson.core.Version;
import tools.jackson.core.util.VersionUtil;
import tools.jackson.databind.module.SimpleModule;

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
