package com.h.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther: hjy
 * @Date: 2021/6/30 14:51
 * @Description: 服务器信息类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    /**
     * 服务器url
     */
    private String url;



}
