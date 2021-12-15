package org.navistack.framework.crudsupport;

import java.io.Serializable;

public interface CrudService<ID extends Serializable, ENTITY, DTO, QUERY_PARAM>
        extends ManipulationService<ID, ENTITY, DTO>,
        QueryService<ID, ENTITY, DTO, QUERY_PARAM>{
}
