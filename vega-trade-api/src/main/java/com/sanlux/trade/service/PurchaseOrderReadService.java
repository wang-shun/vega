package com.sanlux.trade.service;

import com.google.common.base.Optional;
import com.sanlux.trade.dto.PurchaseOrderCriteria;
import com.sanlux.trade.model.PurchaseOrder;
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;

import java.util.List;

/**
 * Code generated by terminus code gen
 * Desc: 读服务
 * Date: 2016-08-09
 */

public interface PurchaseOrderReadService {

    /**
     * 根据id查询
     * @param purchaseOrderId 主键id
     * @return 
     */
    Response<Optional<PurchaseOrder>> findPurchaseOrderById(Long purchaseOrderId);

    /**
     * 根据用户id和采购单名称查询采购单是否存在
     * @param buyerId 买家id
     * @param name 采购单名称
     * @return 采购单
     */
    Response<Optional<PurchaseOrder>> findByBuyerIdAndName(Long buyerId,String name);


    /**
     * 根据条件获取采购单分页
     * @param criteria 分页条件
     * @return 采购单分页
     */
    Response<Paging<PurchaseOrder>> paging(PurchaseOrderCriteria criteria);

    /**
     * 根据当前登录用户id 查询改用户下的所有采购单
     * @param buyerId 当前登录用户id
     * @return 采购单列表
     */
    Response<List<PurchaseOrder>> findByBuyerId(Long buyerId);

    /**
     * 根据当前登录用户id 查询改用户下的所有非临时采购单
     * @param buyerId 当前登录用户id
     * @param skuQuantity  采购单标志:0(集乘网正常采购单) 1:友云采专属采购单
     * @return 采购单列表
     */
    Response<List<PurchaseOrder>> findByBuyerIdNotTemp(Long buyerId, Integer skuQuantity);





}
