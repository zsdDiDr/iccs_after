package com.example.iccs.service;

import com.example.iccs.bean.Server;

import java.util.List;

/**
 * @author zsd
 * @date 2022/1/20.
 */
public interface IServerService {

    /**
     * 查询服务器
     *
     * @return
     */
    List<Server> findServer(Server server);

    /**
     * 新增服务器
     *
     * @param server
     */
    void addServer(Server server);

    /**
     * 更新服务器
     *
     * @param server
     */
    void updateServer(Server server);

    /**
     * 计算设备数
     *
     * @param server
     * @return
     */
    int countDevice(Server server);

}
