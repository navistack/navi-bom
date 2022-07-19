package org.navistack.framework.mybatisplusplus;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public interface ManipulationService<T, ID extends Serializable> {
    void create(T entity);

    void modify(T entity);

    default void remove(ID id) {
        remove(Collections.singleton(id));
    }

    void remove(Collection<ID> ids);
}
