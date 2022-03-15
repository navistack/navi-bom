package org.navistack.framework.mybatisplusplus;

import java.io.Serializable;

public interface CrudService<ID extends Serializable, ENTITY, DTO, QUERY_PARAM>
        extends ManipulationService<ID, ENTITY, DTO>,
        QueryService<ID, ENTITY, DTO, QUERY_PARAM>{
}
