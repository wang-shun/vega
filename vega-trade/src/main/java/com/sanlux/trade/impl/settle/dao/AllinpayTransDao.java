package com.sanlux.trade.impl.settle.dao;

import com.sanlux.trade.settle.model.AllinpayTrans;
import io.terminus.common.mysql.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

/**
 * Code generated by terminus code gen
 * Desc: Dao类
 * Date: 2016-10-20
 */
@Repository
public class AllinpayTransDao extends MyBatisDao<AllinpayTrans> {


    public AllinpayTrans findByTradeNo(String tradeNo){
        return getSqlSession().selectOne(sqlId("findByTradeNo"),tradeNo);

    }

}