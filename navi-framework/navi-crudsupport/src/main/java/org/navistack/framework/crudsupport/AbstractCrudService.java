package org.navistack.framework.crudsupport;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.navistack.framework.core.utils.StaticModelMapper;
import org.navistack.framework.crudsupport.utils.GenericTypeUtils;
import org.navistack.framework.crudsupport.utils.MyBatisPlusUtils;
import org.navistack.framework.data.Page;
import org.navistack.framework.data.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<ID extends Serializable, ENTITY, DTO, QUERY_PARAM, DAO extends BaseMapper<ENTITY>>
        implements CrudService<ID, ENTITY, DTO, QUERY_PARAM> {
    protected final DAO dao;

    @SuppressWarnings("unchecked")
    private final Class<ENTITY> entityClass = (Class<ENTITY>) GenericTypeUtils.resolveTypeArgumentsOf(getClass(), AbstractCrudService.class, 1);

    @SuppressWarnings("unchecked")
    private final Class<DTO> dtoClass = (Class<DTO>) GenericTypeUtils.resolveTypeArgumentsOf(getClass(), AbstractCrudService.class, 2);

    private final Collection<Consumer<DTO>> preCreateActions = new ArrayList<>();

    private final Collection<Consumer<DTO>> preModifyActions = new ArrayList<>();

    public DAO getDao() {
        return dao;
    }

    protected AbstractCrudService(DAO dao) {
        this.dao = dao;
    }

    protected void registerPreCreateAction(Consumer<DTO> action) {
        preCreateActions.add(action);
    }

    protected void registerPreCreateActions(List<Consumer<DTO>> actions) {
        preCreateActions.addAll(actions);
    }

    protected void registerPreModifyAction(Consumer<DTO> action) {
        preModifyActions.add(action);
    }

    protected void registerPreModifyActions(List<Consumer<DTO>> actions) {
        preModifyActions.addAll(actions);
    }

    protected void preCreate(DTO dto) {
        for (Consumer<DTO> preCreateAction : preCreateActions) {
            preCreateAction.accept(dto);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(DTO dto) {
        preCreate(dto);

        ENTITY entity = StaticModelMapper.map(dto, entityClass);

        dao.insert(entity);

        StaticModelMapper.map(entity, dto);
    }

    protected void preModify(DTO dto) {
        for (Consumer<DTO> preModifyAction : preModifyActions) {
            preModifyAction.accept(dto);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modify(DTO dto) {
        preModify(dto);

        ENTITY entity = StaticModelMapper.map(dto, entityClass);

        dao.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(ID id) {
        dao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(Collection<ID> ids) {
        dao.deleteBatchIds(ids);
    }

    protected abstract Wrapper<ENTITY> buildWrapper(QUERY_PARAM queryParams);

    @Override
    public List<DTO> list(QUERY_PARAM queryParams) {
        Wrapper<ENTITY> wrapper = buildWrapper(queryParams);

        return dao.selectList(wrapper).stream()
                .map(e -> StaticModelMapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

    @Override
    public Page<DTO> paginate(QUERY_PARAM queryParams, Pageable pageable) {
        Wrapper<ENTITY> wrapper = buildWrapper(queryParams);

        return MyBatisPlusUtils.PageUtils.toPage(
                dao.selectPage(
                        MyBatisPlusUtils.PageUtils.fromPageable(pageable),
                        wrapper
                ),
                e -> StaticModelMapper.map(e, dtoClass)
        );
    }

    @Override
    public DTO queryById(ID id) {
        return StaticModelMapper.map(dao.selectById(id), dtoClass);
    }
}
