package org.navistack.framework.mybatisplusplus;

import org.navistack.framework.data.Page;
import org.navistack.framework.data.Pageable;

import java.io.Serializable;
import java.util.List;

public interface QueryService<ID extends Serializable, ENTITY, DTO, QUERY_PARAM> {
    List<DTO> list(QUERY_PARAM queryParams);

    Page<DTO> paginate(QUERY_PARAM queryParams, Pageable pageable);

    DTO queryById(ID id);
}
