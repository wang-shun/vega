package com.sanlux.category.impl.service;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.sanlux.category.dto.VegaCategoryDiscountDto;
import com.sanlux.category.impl.dao.CategoryAutheDao;
import com.sanlux.category.model.CategoryAuthe;
import com.sanlux.category.service.CategoryAutheWriteService;
import io.terminus.boot.rpc.common.annotation.RpcProvider;
import io.terminus.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code generated by terminus code gen
 * Desc: 经销商授权类目写服务实现类
 * Date: 2016-08-04
 */
@Slf4j
@Service
@RpcProvider
public class CategoryAutheWriteServiceImpl implements CategoryAutheWriteService {

    private final CategoryAutheDao categoryAutheDao;

    @Autowired
    public CategoryAutheWriteServiceImpl(CategoryAutheDao categoryAutheDao) {
        this.categoryAutheDao = categoryAutheDao;
    }

    @Override
    public Response<Long> createOrUpdateCategoryAuthe(CategoryAuthe categoryAuthe) {
        try {

            if (categoryAuthe.getId() == null) {
                categoryAutheDao.create(categoryAuthe);

            } else {
                categoryAutheDao.update(categoryAuthe);
            }

            return Response.ok(categoryAuthe.getId());
        } catch (Exception e) {
            log.error("create or update categoryAuthe failed, categoryAuthe:{}, cause:{}", categoryAuthe, Throwables.getStackTraceAsString(e));
            return Response.fail("category.authe.create.fail");
        }
    }

    @Override
    public Response<Boolean> updateCategoryAuthe(CategoryAuthe categoryAuthe) {
        try {
            return Response.ok(categoryAutheDao.update(categoryAuthe));
        } catch (Exception e) {
            log.error("update categoryAuthe failed, categoryAuthe:{}, cause:{}", categoryAuthe, Throwables.getStackTraceAsString(e));
            return Response.fail("category.authe.update.fail");
        }
    }

    @Override
    public Response<Boolean> updateCategoryDiscount(Long shopId, VegaCategoryDiscountDto vegaCategoryDiscountDto) {
        try {

            CategoryAuthe categoryAuthe = categoryAutheDao.findByShopId(shopId).orNull();
            if (categoryAuthe == null || Strings.isNullOrEmpty(categoryAuthe.getCategoryDiscountList())) {
                log.error("category auth not exist or null, shopId: {}", shopId);
                return Response.fail("category.auth.not.exist");
            }

            //找到类目,插入会员折扣
            List<VegaCategoryDiscountDto> originDtos = categoryAuthe.getDiscountList();
            Boolean findFlag = Boolean.FALSE;
            for (VegaCategoryDiscountDto discountDto : originDtos) {
                if (discountDto.getCategoryId().equals(vegaCategoryDiscountDto.getCategoryId())) {
                    discountDto.setCategoryMemberDiscount(vegaCategoryDiscountDto.getCategoryMemberDiscount());
                    findFlag = Boolean.TRUE;
                    break;
                }
            }

            if (!findFlag) {
                log.error("category not auth, categoryId", vegaCategoryDiscountDto.getCategoryId());
                return Response.fail("category.not.auth");
            }

            CategoryAuthe toUpdate = new CategoryAuthe();
            toUpdate.setId(categoryAuthe.getId());
            toUpdate.setDiscountList(originDtos);
            return Response.ok(categoryAutheDao.update(toUpdate));
        } catch (Exception e) {
            log.error("update categoryAuthe discount failed, shopId:{}, cause:{}", shopId, Throwables.getStackTraceAsString(e));
            return Response.fail("category.authe.discount.update.fail");
        }
    }

    @Override
    public Response<Boolean> deleteCategoryAutheById(Long categoryAutheId) {
        try {
            return Response.ok(categoryAutheDao.delete(categoryAutheId));
        } catch (Exception e) {
            log.error("delete categoryAuthe failed, categoryAutheId:{}, cause:{}", categoryAutheId, Throwables.getStackTraceAsString(e));
            return Response.fail("category.authe.delete.fail");
        }
    }
}
