package com.sanlux.trade.impl.service;

import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.sanlux.trade.dto.OrderDispatchCriteria;
import com.sanlux.trade.impl.dao.OrderDispatchRelationDao;
import com.sanlux.trade.model.OrderDispatchRelation;
import com.sanlux.trade.service.OrderDispatchRelationReadService;
import io.terminus.boot.rpc.common.annotation.RpcProvider;
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Code generated by terminus code gen
 * Desc: 订单派送关联表读服务实现类
 * Date: 2016-08-16
 */
@Slf4j
@Service
@RpcProvider
public class OrderDispatchRelationReadServiceImpl implements OrderDispatchRelationReadService {

    private final OrderDispatchRelationDao orderDispatchRelationDao;

    @Autowired
    public OrderDispatchRelationReadServiceImpl(OrderDispatchRelationDao orderDispatchRelationDao) {
        this.orderDispatchRelationDao = orderDispatchRelationDao;
    }

    @Override
    public Response<Optional<OrderDispatchRelation>> findOrderDispatchRelationById(Long orderDispatchRelationId) {
        try {
            return Response.ok(Optional.fromNullable(orderDispatchRelationDao.findById(orderDispatchRelationId)));
        } catch (Exception e) {
            log.error("find orderDispatchRelation by id failed, orderDispatchRelationId:{}, cause:{}", orderDispatchRelationId,
                    Throwables.getStackTraceAsString(e));
            return Response.fail("order.dispatch.relation.find.fail");
        }
    }

    @Override
    public Response<Optional<OrderDispatchRelation>> findByOrderIdAndDispatchShopId(Long orderId, Long dispatchShopId) {
        try {

            return Response.ok(Optional.fromNullable(orderDispatchRelationDao.findByOrderIdAndDispatchShopId(orderId,dispatchShopId)));
        }catch (Exception e){
            log.error("find orderDispatchRelation by order id:{} dispatch shop id:{} failed, cause:{}", orderId,dispatchShopId,
                    Throwables.getStackTraceAsString(e));
            return Response.fail("order.dispatch.relation.find.fail");
        }
    }

    @Override
    public Response<Paging<OrderDispatchRelation>> paging(OrderDispatchCriteria orderDispatchCriteria) {
        try {
            return Response.ok(orderDispatchRelationDao.paging(orderDispatchCriteria.toMap()));
        } catch (Exception e) {
            log.error("paging orderDispatchRelation failed, orderDispatchCriteria:{}, cause:{}", orderDispatchCriteria,
                    Throwables.getStackTraceAsString(e));
            return Response.fail("order.dispatch.relation.paging.fail");
        }
    }




}
