package com.sanlux.trade.settle.service;

import com.sanlux.trade.settle.criteria.VegaSellerTradeDailySummaryCriteria;
import com.sanlux.trade.settle.model.VegaSellerTradeDailySummary;
import io.terminus.common.model.Paging;
import io.terminus.common.model.Response;

import java.util.Date;
import java.util.List;

/**
 * Code generated by CodeGen
 * Desc: 三力士商家日汇总读服务
 * Date: 2016-12-15
 */
public interface VegaSellerTradeDailySummaryReadService {

    /**
     * 根据id查询三力士商家日汇总
     * @param id 主键id
     * @return 三力士商家日汇总
     */
    Response<VegaSellerTradeDailySummary> findSellerTradeDailySummaryById(Long id);

    /**
     * 查询所有三力士商家日汇总
     * @return 所有三力士商家日汇总
     */
    Response<List<VegaSellerTradeDailySummary>> findAllSellerTradeDailySummarys();

    /**
     * 分页查询三力士商家日汇总
     * @param criteria  查询条件
     * @return 三力士商家日汇总分页查询结果
     */
    Response<Paging<VegaSellerTradeDailySummary>> pagingSellerTradeDailySummarys(VegaSellerTradeDailySummaryCriteria criteria);

    Response<VegaSellerTradeDailySummary> findBySellerIdAndSumAtAndSummaryType(Long sellerId, Date sumAt, Integer summaryType);

    /**
     * 获取经销商待打款数量
     * @param Type  1:一级经销商 2:二级经销商
     * @return Long
     */
    Response<Long> countDealerPayment (Integer Type);

}
