package com.h.interfaces;

import com.h.beans.MethodInfo;
import com.h.beans.ServerInfo;

/**
 * @auther: hjy
 * @Date: 2021/6/30 14:58
 * @Description:  rest请求调用 handler
 */

public interface RestHandler {

    /**
     * 初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest请求，返回接口
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
