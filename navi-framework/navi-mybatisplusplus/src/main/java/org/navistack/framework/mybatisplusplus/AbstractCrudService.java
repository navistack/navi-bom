package org.navistack.framework.mybatisplusplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import org.navistack.framework.data.Page;
import org.navistack.framework.data.PageImpl;
import org.navistack.framework.data.Pageable;
import org.navistack.framework.data.Sort;
import org.navistack.framework.utils.Arrays;
import org.navistack.framework.utils.ModelMappers;
import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<T, ID extends Serializable, DTO, Q, DAO extends CrudMapper<T>>
        implements CrudService<DTO, ID, Q> {
    protected final DAO dao;

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) Arrays.get(GenericTypeResolver.resolveTypeArguments(getClass(), AbstractCrudService.class), 1);

    @SuppressWarnings("unchecked")
    private final Class<DTO> dtoClass = (Class<DTO>) Arrays.get(GenericTypeResolver.resolveTypeArguments(getClass(), AbstractCrudService.class), 2);

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

        T entity = buildEntity(dto);

        dao.insert(entity);

        ModelMappers.map(entity, dto);
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

        T entity = buildEntity(dto);

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

    protected abstract Wrapper<T> buildWrapper(Q queryParams);

    @Override
    public List<DTO> list(Q queryParams) {
        Wrapper<T> wrapper = buildWrapper(queryParams);

        return dao.selectList(wrapper).stream()
                .map(this::buildDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DTO> paginate(Q queryParams, Pageable pageable) {
        Wrapper<T> wrapper = buildWrapper(queryParams);

        return buildPage(dao.selectPage(buildMyBatisPlusPage(pageable), wrapper), this::buildDto);
    }

    @Override
    public DTO queryById(ID id) {
        return ModelMappers.map(dao.selectById(id), dtoClass);
    }

    protected DTO buildDto(T entity) {
        return ModelMappers.map(entity, dtoClass);
    }

    protected T buildEntity(DTO dto) {
        return ModelMappers.map(dto, entityClass);
    }

    protected static <U> IPage<U> buildMyBatisPlusPage(Pageable pageable) {
        int current = pageable.getPageNumber();
        int size = pageable.getPageSize();
        List<OrderItem> orders = pageable.getSort().getOrders().stream()
                .filter(order -> order.getProperty() != null && order.getDirection() != null)
                .map(order -> new OrderItem(
                        order.getProperty(),
                        Sort.Direction.ASC.equals(order.getDirection()))
                )
                .collect(Collectors.toList());

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<U> target =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                        current,
                        size
                );
        target.addOrder(orders);

        return target;
    }

    protected static <U, V> Page<V> buildPage(IPage<U> page, Function<U, V> converter) {
        List<V> listToReturn = page.getRecords().stream()
                .map(converter)
                .collect(Collectors.toList());

        int pageNumber = (int) page.getCurrent();
        int pageSize = (int) page.getSize();
        long totalRecords = page.getTotal();

        return new PageImpl<>(listToReturn, pageNumber, pageSize, totalRecords);
    }
}
