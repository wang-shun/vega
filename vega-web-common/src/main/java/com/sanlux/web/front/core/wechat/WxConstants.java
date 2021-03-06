/*
 *
 *  * Copyright (c) 2015 杭州端点网络科技有限公司
 *
 */

package com.sanlux.web.front.core.wechat;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: haolin
 * At: 1/9/15
 */
public final class WxConstants {

    public static final String WEIXIN_USER_AGENT = "MicroMessenger";

    public static final String OPEN_ID = "openid";

    public static final String CODE = "code";

    public static Boolean isWxClient(HttpServletRequest request){
        String header = request.getHeader("USER-AGENT");
        return header != null && header.contains(WEIXIN_USER_AGENT);
    }


}
