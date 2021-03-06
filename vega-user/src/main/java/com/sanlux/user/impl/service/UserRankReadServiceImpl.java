package com.sanlux.user.impl.service;

import com.google.common.base.Throwables;
import com.sanlux.user.dto.UserRank;
import com.sanlux.user.impl.dao.RankDao;
import com.sanlux.user.model.Rank;
import com.sanlux.user.service.UserRankReadService;
import io.terminus.boot.rpc.common.annotation.RpcProvider;
import io.terminus.common.model.Response;
import io.terminus.common.utils.JsonMapper;
import io.terminus.parana.user.impl.dao.UserDao;
import io.terminus.parana.user.impl.dao.UserProfileDao;
import io.terminus.parana.user.model.User;
import io.terminus.parana.user.model.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code generated by terminus code gen
 * Desc: 用户等级表读服务实现类
 * Date: 2016-08-03
 */
@Slf4j
@Service
@RpcProvider
public class UserRankReadServiceImpl implements UserRankReadService {


    private final UserDao userDao;
    private final UserProfileDao userProfileDao;
    private final RankDao rankDao;

    @Autowired
    public UserRankReadServiceImpl(UserDao userDao,
                                   UserProfileDao userProfileDao, RankDao rankDao) {
        this.userDao = userDao;
        this.userProfileDao = userProfileDao;
        this.rankDao = rankDao;
    }

    /**
     * 通过userId获得用户等级信息
     *
     * @param userId 用户ID
     * @return UserRank 用户等级信息
     */
    @Override
    public Response<UserRank> findUserRankByUserId(Long userId) {
        try {
            User user = userDao.findById(userId);
            UserProfile profile = userProfileDao.findByUserId(userId);
            if (user == null) {
                log.error("user.find.fail");
                return Response.fail("user.find.fail");
            }
            String userRankJson = user.getExtraJson();
            UserRank userRank = JsonMapper.JSON_NON_EMPTY_MAPPER.fromJson(userRankJson, UserRank.class);
            if (userRank != null) {
                if (profile != null) {
                    userRank.setAvatar(profile.getAvatar());
                }
                List<Rank> ranks = rankDao.findAll();
                Long nowGrowthValue = userRank.getGrowthValue();
                for (Rank rank : ranks) {
                    if (rank.getGrowthValueStart() <= nowGrowthValue && nowGrowthValue <= rank.getGrowthValueEnd()) {
                        if (userRank.getRankId() != rank.getId()) {
                            //用户当前的成长值不符合新的规则,进行更新
                            userRank.setRankId(rank.getId());
                            userRank.setRankName(rank.getName());
                            String newUserRankJson = JsonMapper.JSON_NON_EMPTY_MAPPER.toJson(userRank);
                            user.setExtraJson(newUserRankJson);
                            userDao.update(user);
                        }

                    }
                }
                return Response.ok(userRank);
            } else {
                return Response.fail("user.extra.json.null");
            }
        } catch (Exception e) {
            log.error("find userRank by id failed, userId:{}, cause:{}", userId, Throwables.getStackTraceAsString(e));
            return Response.fail("user.rank.find.fail");
        }
    }

    @Override
    public Response<Long> findIntegrationByUserId(Long userId) {

        try {
            User user = userDao.findById(userId);
            if (user == null) {
                log.error("user.find.fail");
                return Response.fail("user.find.fail");
            }
            String userRankJson = user.getExtraJson();
            UserRank userRank = JsonMapper.JSON_NON_EMPTY_MAPPER.fromJson(userRankJson, UserRank.class);
            if (userRank != null) {
                return Response.ok(userRank.getIntegration());
            } else {
                log.error("user rank find fail,userId:{}", userId);
                return Response.fail("user.rank.find.fail");
            }

        } catch (Exception e) {
            log.error("find integration by id failed, userId:{},cause:{}", userId, Throwables.getStackTraceAsString(e));
            return Response.fail("user.integration.find.fail");
        }


    }

    @Override
    public Response<Long> findIntegrationByMobile(String mobile) {  try {
        User user = userDao.findByMobile(mobile);
        if (user == null) {
            log.error("user.find.fail");
            return Response.fail("user.find.fail");
        }
        String userRankJson = user.getExtraJson();
        UserRank userRank = JsonMapper.JSON_NON_EMPTY_MAPPER.fromJson(userRankJson, UserRank.class);
        if (userRank != null) {
            return Response.ok(userRank.getIntegration());
        } else {
            log.error("user rank find fail,mobile:{}", mobile);
            return Response.fail("user.rank.find.fail");
        }

    } catch (Exception e) {
        log.error("find integration by mobile failed, userId:{},cause:{}", mobile, Throwables.getStackTraceAsString(e));
        return Response.fail("user.integration.find.fail");
    }
    }
}
