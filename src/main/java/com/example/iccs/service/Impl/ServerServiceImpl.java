package com.example.iccs.service.Impl;

import com.example.iccs.bean.Server;
import com.example.iccs.dao.IServerDao;
import com.example.iccs.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务器服务层
 *
 * @author zsd
 * @date 2022/1/20.
 */
@Service
public class ServerServiceImpl implements IServerService {

    @Autowired
    private IServerDao serverDao;


    @Override
    public List<Server> findServer(Server server) {
        return serverDao.findServer(server);
    }

    @Override
    public void addServer(Server server) {
        serverDao.addServer(server);
    }

    @Override
    public void updateServer(Server server) {
        serverDao.updateServer(server);
    }

    @Override
    public int countDevice(Server server) {
        return serverDao.deviceCount(server);
    }
}
