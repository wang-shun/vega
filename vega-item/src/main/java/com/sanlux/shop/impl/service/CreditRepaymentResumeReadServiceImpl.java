package com.sanlux.shop.impl.service;

import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.sanlux.shop.dto.CreditRepaymentDetail;
import com.sanlux.shop.impl.dao.CreditAlterResumeDao;
import com.sanlux.shop.impl.dao.CreditRepaymentResumeDao;
import com.sanlux.shop.model.CreditAlterResume;
import com.sanlux.shop.model.CreditRepaymentResume;
import com.sanlux.shop.service.CreditRepaymentResumeReadService;
import io.terminus.boot.rpc.common.annotation.RpcProvider;
import io.terminus.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code generated by terminus code gen
 * Desc: 还款履历表读服务实现类
 * Date: 2016-08-29
 */
@Slf4j
@Service
@RpcProvider
public class CreditRepaymentResumeReadServiceImpl implements CreditRepaymentResumeReadService {

    private final CreditRepaymentResumeDao creditRepaymentResumeDao;
    private final CreditAlterResumeDao creditAlterResumeDao;

    @Autowired
    public CreditRepaymentResumeReadServiceImpl(CreditRepaymentResumeDao creditRepaymentResumeDao,
                                                CreditAlterResumeDao creditAlterResumeDao) {
        this.creditRepaymentResumeDao = creditRepaymentResumeDao;
        this.creditAlterResumeDao = creditAlterResumeDao;
    }

    @Override
    public Response<CreditRepaymentResume> findById(Long id) {
        try {
            return Response.ok(creditRepaymentResumeDao.findById(id));
        } catch (Exception e) {
            log.error("find creditRepaymentResume by id :{} failed,  cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("credit.repayment.resume.find.fail");
        }
    }

    @Override
    public Response<CreditRepaymentDetail> findByAlterResumeId(Long alterResumeId) {
        try {
            CreditAlterResume alterResume = creditAlterResumeDao.findById(alterResumeId);
            if (alterResume == null) {
                log.error("failed to find credit repayment detail by alterResumeId = ({}), " +
                        "cause : alter resume is not exists.", alterResumeId);
                return Response.fail("alter.resume.is.not.exists");
            }

            List<CreditRepaymentResume> repaymentResumeList =
                    creditRepaymentResumeDao.findByAlterResumeId(alterResumeId);
            CreditRepaymentDetail repaymentDetail = new CreditRepaymentDetail();
            repaymentDetail.setAlterResume(alterResume);
            repaymentDetail.setRepaymentResumeList(repaymentResumeList);

            return Response.ok(repaymentDetail);
        }catch (Exception e) {
            log.error("failed to find credit repayment detail by alterResumeId = ({}), cause : {}",
                    alterResumeId, Throwables.getStackTraceAsString(e));
            return Response.fail("find.repayment.detail.failed");
        }
    }

    @Override
    public Response<Optional<CreditRepaymentResume>> findLastRepaymentByAlterResumeId(Long alterResumeId) {
        try {
            CreditRepaymentResume repaymentResume =
                    creditRepaymentResumeDao.findLastRepaymentByAlterResumeId(alterResumeId);
            return Response.ok(Optional.fromNullable(repaymentResume));
        }catch (Exception e) {
            log.error("failed to find credit repayment detail by alterResumeId = ({}), " +
                    "cause : alter resume is not exists and {}", alterResumeId, Throwables.getStackTraceAsString(e));
            return Response.fail("alter.resume.is.not.exists");
        }
    }
}
