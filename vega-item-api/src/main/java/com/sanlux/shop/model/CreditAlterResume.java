package com.sanlux.shop.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.terminus.common.utils.JsonMapper;
import io.terminus.parana.common.constants.JacksonType;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Code generated by terminus code gen
 * Desc: 信用额度变更履历Model类
 * Date: 2016-08-11
 */
@Data
public class CreditAlterResume implements Serializable {

    private static final long serialVersionUID = -8323272663215521770L;

    private static final ObjectMapper objectMapper = JsonMapper.nonEmptyMapper().getMapper();

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;
    
    /**
     * 经销商ID
     */
    private Long shopId;
    
    /**
     * 经销商名称
     */
    private String shopName;
    
    /**
     * 操作人ID
     */
    private Long operateId;
    
    /**
     * 操作人名称
     */
    private String operateName;

    /**
     * 交易请求流水号
     */
    private String tradeNo;

    /**
     * 退款请求流水号
     */
    private String refundNo;

    /**
     * 第三方支付流水号
     */
    private String paymentCode;

    /**
     * 第三方退款流水号
     */
    private String refundCode;

    /**
     * 应还款日期
     */
    private Date shouldRepaymentDate;

    /**
     * 实际还款日期
     */
    private Date actualRepaymentDate;
    
    /**
     * 上一次信用额度
     */
    private Long lastCredit;
    
    /**
     * 最新信用额度
     */
    private Long newestCredit;

    /**
     * 总额度
     */
    private Long totalCredit;

    /**
     * 可用额度
     */
    private Long availableCredit;

    /**
     * 变更额度
     */
    private Long alterValue;

    /**
     * 变更类型
     * @see com.sanlux.shop.enums.CreditAlterType
     */
    private Integer alterType;

    /**
     * 状态
     * @see com.sanlux.shop.enums.CreditAlterStatus
     */
    private Integer alterStatus;

    /**
     * 已还款金额
     */
    private Long alreadyPayment;

    /**
     * 剩余未还款金额
     */
    private Long remainPayment;

    /**
     * 罚息
     */
    private Long fineAmount;

    /**
     * 是否还款完成
     */
    private Boolean isPaymentComplete;
    
    /**
     * 扩展信息字段
     */
    private String extraJson;

    /**
     * 扩展信息
     */
    private Map<String, String> extra;
    
    /**
     * 创建时间
     */
    private Date createdAt;
    
    /**
     * 更新时间
     */
    private Date updatedAt;

    public void setExtraJson(String extraJson) throws Exception{
        this.extraJson = extraJson;
        if(Strings.isNullOrEmpty(extraJson)){
            this.extra= Collections.emptyMap();
        } else{
            this.extra = objectMapper.readValue(extraJson, JacksonType.MAP_OF_STRING);
        }
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
        if(extra ==null ||extra.isEmpty()){
            this.extraJson = null;
        }else{
            try {
                this.extraJson = objectMapper.writeValueAsString(extra);
            } catch (Exception e) {
                //ignore this exception
            }
        }
    }
}
