package com.sanlux.trade.impl.dao;

import com.sanlux.trade.model.OrderDispatchRelation;
import io.terminus.common.model.Paging;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


/**
 * Code generated by terminus code gen
 * Desc: 订单派送关联表Dao 测试类
 * Date: 2016-08-16
 */
public class OrderDispatchRelationDaoTest extends BaseDaoTest {



    @Autowired
    private OrderDispatchRelationDao orderDispatchRelationDao;

    private OrderDispatchRelation orderDispatchRelation;

    @Before
    public void init() {
        orderDispatchRelation = make();

        orderDispatchRelationDao.create(orderDispatchRelation);
        assertNotNull(orderDispatchRelation.getId());
    }

    @Test
    public void findById() {
        OrderDispatchRelation orderDispatchRelationExist = orderDispatchRelationDao.findById(orderDispatchRelation.getId());

        assertNotNull(orderDispatchRelationExist);
    }

    @Test
    public void update() {
        // todo
        orderDispatchRelation.setUpdatedAt(new Date());
        orderDispatchRelationDao.update(orderDispatchRelation);

        OrderDispatchRelation  updated = orderDispatchRelationDao.findById(orderDispatchRelation.getId());
        // todo
        //assertEquals(updated.getHasDisplay(), Boolean.TRUE);
    }

    @Test
    public void delete() {
        orderDispatchRelationDao.delete(orderDispatchRelation.getId());

        OrderDispatchRelation deleted = orderDispatchRelationDao.findById(orderDispatchRelation.getId());
        assertNull(deleted);
    }

    @Test
    public void paging() {
        Map<String, Object> params = new HashMap<>();
        //todo
        //params.put("userId", orderDispatchRelation.getUserId());
        Paging<OrderDispatchRelation > orderDispatchRelationPaging = orderDispatchRelationDao.paging(0, 20, params);

        assertThat(orderDispatchRelationPaging.getTotal(), is(1L));
        assertEquals(orderDispatchRelationPaging.getData().get(0).getId(), orderDispatchRelation.getId());
    }

    private OrderDispatchRelation make() {
        OrderDispatchRelation orderDispatchRelation = new OrderDispatchRelation();

        
        orderDispatchRelation.setOperateId(1L);
        
        orderDispatchRelation.setOperateName("test");
        
        orderDispatchRelation.setOrderId(1L);
        
        orderDispatchRelation.setReceiveShopId(1L);
        
        orderDispatchRelation.setDispatchShopId(1L);
        
        orderDispatchRelation.setCreatedAt(new Date());
        
        orderDispatchRelation.setUpdatedAt(new Date());
        

        return orderDispatchRelation;
    }

}