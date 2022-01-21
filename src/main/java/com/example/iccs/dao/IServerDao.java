package com.example.iccs.dao;

import com.example.iccs.bean.Server;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zsd
 * @date 2022/1/20.
 */
@Mapper
@Repository
public interface IServerDao {

    /**
     * 获取服务器信息
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
     * @return
     */
    void updateServer(Server server);


    /**
     * 计算设备数量
     *
     * @param server
     * @return
     */
    int deviceCount(Server server);

}
