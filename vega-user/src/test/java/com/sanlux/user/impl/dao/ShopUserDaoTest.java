package com.sanlux.user.impl.dao;

import com.sanlux.user.model.ShopUser;
import io.terminus.common.model.Paging;
import io.terminus.parana.common.enums.UserType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Code generated by terminus code gen
 * Desc: 经销商设定用户指定折扣表Dao 测试类
 * Date: 2016-08-03
 */
public class ShopUserDaoTest extends BaseDaoTest {


    @Autowired
    private ShopUserDao shopUserDao;


    private ShopUser shopUser;

    @Before
    public void init() {
        shopUser = make();
        shopUserDao.create(shopUser);
        assertNotNull(shopUser.getId());


    }

    @Test
    public void findById() {
        ShopUser shopUserExist = shopUserDao.findById(shopUser.getId());

        assertNotNull(shopUserExist);
    }

    @Test
    public void findShopUserByUserId() {
        ShopUser shopUser = shopUserDao.findByUserId( 23L);
        assertTrue(shopUser != null);
    }

    @Test
    public void update() {
        shopUser.setDiscount(21);

        shopUserDao.update(shopUser);

        ShopUser updated = shopUserDao.findById(shopUser.getId());
        assertEquals(updated.getDiscount(), Integer.valueOf(21));
    }

    @Test
    public void updateShopUserDiscount() {
        shopUserDao.updateShopUserDiscount(2l, shopUser.getUserId(), 20);
        assertThat(shopUserDao.findById(shopUser.getId()).getDiscount(), is(20));
    }

    //@Test
    public void delete() {
        shopUserDao.delete(shopUser.getId());

        ShopUser deleted = shopUserDao.findById(shopUser.getId());
        assertNull(deleted);
    }

    @Test
    public void paging() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", shopUser.getUserId());
        Paging<ShopUser> shopUserDiscountPaging = shopUserDao.paging(0, 20, params);

        assertThat(shopUserDiscountPaging.getTotal(), is(1L));
        assertEquals(shopUserDiscountPaging.getData().get(0).getId(), shopUser.getId());
    }

    @Test
    public void findByMobileAndShopId() {
        ShopUser shopUser = shopUserDao.findByMobileAndShopId("18705425321", 2l);
        assertNotNull(shopUser);

    }


    private ShopUser make() {
        ShopUser shopUser = new ShopUser();


        shopUser.setShopId(2l);

        shopUser.setShopName("name");

        shopUser.setUserId(23l);

        shopUser.setUserName("user name");

        shopUser.setDiscount(32);

//        shopUser.setExtraJson("3243");

        shopUser.setCreatedAt(new Date());

        shopUser.setUpdatedAt(new Date());
        shopUser.setMobile("18705425321");


        return shopUser;
    }


}
