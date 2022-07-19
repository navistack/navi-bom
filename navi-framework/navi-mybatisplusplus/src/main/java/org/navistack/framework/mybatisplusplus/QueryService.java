package org.navistack.framework.mybatisplusplus;

import org.navistack.framework.data.Page;
import org.navistack.framework.data.Pageable;

import java.io.Serializable;
import java.util.List;

public interface QueryService<T, ID extends Serializable, Q> {
    List<T> list(Q query);

    Page<T> paginate(Q query, Pageable pageable);

    T queryById(ID id);
}
