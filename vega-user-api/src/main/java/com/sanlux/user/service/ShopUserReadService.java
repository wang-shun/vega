package com.sanlux.user.service;

import com.google.common.base.Optional;
import com.sanlux.user.dto.UserDetailPageDto;
import com.sanlux.user.dto.criteria.ShopUserCriteria;
import com.sanlux.user.model.ShopUser;
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;
import io.terminus.parana.user.model.User;

import java.util.Date;
import java.util.Map;

/**
 * Code generated by terminus code gen
 * Desc: 经销商设定用户指定折扣表读服务
 * Date: 2016-08-03
 */

public interface ShopUserReadService {

    /**
     * 根据id查询经销商设定用户指定折扣表
     *
     * @param shopUserDiscountId 主键id
     * @return 经销商设定用户指定折扣表
     */
    Response<Optional<ShopUser>> findById(Long shopUserDiscountId);


    /**
     * 根据条件获取公司会员折扣分页
     *
     * @param criteria 分页条件
     * @return 会员折扣分页 Response<ShopUser>
     */
    Response<Paging<ShopUser>> paging(ShopUserCriteria criteria);

    /**
     * 根据手机获取经销商用户信息
     *
     * @param mobile 手机号
     * @param shopId 店铺id
     * @return Response<ShopUser>
     */
    Response<ShopUser> findByMobileAndShopId(String mobile, Long shopId);

    /**
     * 详细经销商用户分页
     *
     * @param shopUserCriteria 查询条件
     * @param companNowDiscount 当前折扣
     * @return 封装的用户详细信息
     */

    Response<UserDetailPageDto> pagingShopUser(ShopUserCriteria shopUserCriteria, Integer companNowDiscount);

    /**
     * 根据用户ID获取ShopUser
     * @param userId 用户ID
     * @return ShopUser
     */
    Response<Optional<ShopUser>> findShopUserByUserId(Long userId);

    /**
     * 通过手机号获得经销商用户
     * @param mobile 手机号
     * @return 经销商用户
     */
    Response<ShopUser> findByMobile(String mobile);
}
