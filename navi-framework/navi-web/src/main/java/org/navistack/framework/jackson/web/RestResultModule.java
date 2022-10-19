package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.navistack.framework.web.rest.RestResult;

public class RestResultModule extends SimpleModule {
    protected static final Version VERSION = VersionUtil.parseVersion(
            "0.0.1-SNAPSHOT",
            "org.navistack.framework",
            "navi-core"
    );

    public RestResultModule() {
        super(VERSION);

        setMixInAnnotation(RestResult.class, RestResultMixIn.class);
        setMixInAnnotation(RestResult.ParameterizedError.class, ParameterizedErrorMixIn.class);
    }
}
