package org.navistack.framework.mybatisplusplus;

import java.io.Serializable;

public interface CrudService<T, ID extends Serializable, Q>
        extends ManipulationService<T, ID>,
        QueryService<T, ID, Q>{
}
