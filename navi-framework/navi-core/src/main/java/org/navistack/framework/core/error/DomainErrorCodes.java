package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainErrorCodes {
    public final int GENERAL_ERROR = ErrorCodeBuilder.domainError(0x00);

    public final int ENTITY_DUPLICATION = ErrorCodeBuilder.domainError(0x001);

    public final int NO_SUCH_ENTITY = ErrorCodeBuilder.domainError(0x002);

    public final int CONSTRAINT_VIOLATION = ErrorCodeBuilder.domainError(0x003);
}
