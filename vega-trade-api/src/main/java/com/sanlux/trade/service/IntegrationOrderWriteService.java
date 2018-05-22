package com.sanlux.trade.service;

import com.sanlux.trade.model.IntegrationOrder;
import io.terminus.common.model.Response;

/**
 * Code generated by terminus code gen
 * Desc: 积分商品订单写服务
 * Date: 2016-11-07
 */

public interface IntegrationOrderWriteService {

    /**
     * 创建IntegrationOrder
     * @param integrationOrder
     * @return 主键id
     */
    Response<Long> create(IntegrationOrder integrationOrder);

    /**
     * 更新IntegrationOrder
     * @param integrationOrder
     * @return 是否成功
     */
    Response<Boolean> update(IntegrationOrder integrationOrder);

    /**
     * 根据主键id删除IntegrationOrder
     * @param integrationOrderId
     * @return 是否成功
     */
    Response<Boolean> deleteById(Long integrationOrderId);

    /**
     * 运营发货
     * @param id 订单ID
     * @param deliveryCompany 快递公司
     * @param deliveryNo 快递单号
     * @return boolean
     */
    Response<Boolean> delivery(Long id, String deliveryCompany, String deliveryNo);
}