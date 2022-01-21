package com.example.iccs.service.Impl;

import  com.example.iccs.bean.User;
import com.example.iccs.dao.IAdminDao;
import com.example.iccs.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务层
 *
 * @author zsd
 * @date 2022/1/13
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDao iAdminDao;

    @Override
    public List<User> login(User user) {
        return iAdminDao.findUser(user);
    }

    @Override
    public List<User> register(User user) {
        iAdminDao.insert(user);
        return iAdminDao.findUser(user);
    }

    @Override
    public void update(User user) {
        iAdminDao.update(user);
    }
}
