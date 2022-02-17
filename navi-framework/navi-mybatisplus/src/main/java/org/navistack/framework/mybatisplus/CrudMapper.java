package org.navistack.framework.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

public interface CrudMapper<T> extends BaseMapper<T> {
    /**
     * Check if any row exists according to wrapper conditions
     *
     * @param queryWrapper nullable wrapper
     */
    boolean exists(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
