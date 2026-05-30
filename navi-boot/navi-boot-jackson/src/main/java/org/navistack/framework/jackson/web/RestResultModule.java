package org.navistack.framework.jackson.web;

import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.RestOkResult;
import tools.jackson.databind.module.SimpleModule;

public class RestResultModule extends SimpleModule {

    public RestResultModule() {
        setMixInAnnotation(RestOkResult.class, RestOkResultMixIn.class);
        setMixInAnnotation(RestErrResult.class, RestErrResultMixIn.class);
    }
}
