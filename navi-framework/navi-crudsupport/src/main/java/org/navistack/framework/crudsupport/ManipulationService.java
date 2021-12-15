package org.navistack.framework.crudsupport;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public interface ManipulationService<ID extends Serializable, ENTITY, DTO> {
    void create(DTO dto);

    void modify(DTO dto);

    default void remove(ID id) {
        remove(Collections.singleton(id));
    }

    void remove(Collection<ID> ids);
}
